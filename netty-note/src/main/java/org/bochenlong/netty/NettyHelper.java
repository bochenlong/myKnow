package org.bochenlong.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import org.bochenlong.netty.client.NettyClient;
import org.bochenlong.netty.common.Constant;
import org.bochenlong.netty.common.exception.RemoteException;
import org.bochenlong.netty.message.MsgHelper;
import org.bochenlong.netty.message.MsgManager;
import org.bochenlong.netty.message.bean.NettyMsg;
import org.bochenlong.netty.resp.NettyFuture;
import org.bochenlong.netty.server.NettyServer;
import org.bochenlong.netty.server.authpolicy.AuthManager;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Created by bochenlong on 16-11-4.
 */
public class NettyHelper {
    
    public static String getIp(SocketAddress socketAddress) {
        String address = socketAddress.toString();
        address = address.substring(address.indexOf("/") + 1, address.indexOf(":"));
        return address;
    }
    
    public static String getLocalIP() {
        String localIP = null;// 如果没有找到外网IP，则返回本机IP
        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                    ip = address.nextElement();
                    if (!ip.isLoopbackAddress()
                            && ip.getHostAddress().indexOf(":") == -1) {
                        if (!ip.isSiteLocalAddress()) {
                            // 如果找到外网IP，第一时间返回
                            return ip.getHostAddress();
                        } else {
                            // 否则保留找到的本机IP
                            localIP = ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return localIP;
    }
    
    public static void send(String host, NettyMsg msg) {
        connect(host).writeAndFlush(msg);
    }
    
    public static Future<NettyMsg> sendAndAccept(String host, NettyMsg msg) throws RemoteException {
        Channel channel = connect(host);
        try {
            ChannelFuture future = channel.writeAndFlush(msg);
            // 等待发送成功
            if (msg.getHeader().getBack() == Constant.YES) {
                future.await(NettyManager.SEND_TIME_OUT);
            }
            Throwable cause = future.cause();
            if (cause != null) {
                throw cause;
            }
        } catch (Throwable throwable) {
            throw new RemoteException("fail send message to " + getIp(channel.remoteAddress()) + " , cause " + throwable.getMessage());
        }
        // 封装等待Future
        return NettyChannel.futures.computeIfAbsent(msg.getHeader().getSessionId(), a -> new NettyFuture<>());
    }
    
    
    public static void setFutureResult(NettyMsg msg) throws ExecutionException, InterruptedException {
        NettyFuture<NettyMsg> f = NettyChannel.futures.get(msg.getHeader().getSessionId());
        if (f == null) return;
        f.set(msg);
        NettyChannel.futures.remove(msg.getHeader().getSessionId());
    }
    
    
    public static void remove(String host, Channel channel) {
        channel.close();
        NettyChannel.channels.remove(host, channel);
    }
    
    //
    public static Channel connect(String host) {
        Channel channel = new NettyClient(host).channel();
        /*P2pClient的new过程可能会非常长，如果put的时候，已经有连接，那么断开此连接，使用已有的通道*/
        if (NettyChannel.channels.putIfAbsent(host, channel) != null) {
            /*因为server会先初始化map，假如两端server同时已初始化，那么client会发生同时关闭*/
            /*因此比较host来决定关闭谁*/
            if (NettyHelper.getLocalIP().compareTo(host) >= 1) {
                channel.close();
            }
        }
        return NettyChannel.channels.get(host);
    }
    
    public static void startServer() {
        /*授权方式*/
        AuthManager.setSINGLE_CON();
        /*消息队列*/
        MsgManager.setDefault();
        /*消息类型*/
        MsgHelper.addMsgTypes(Arrays.asList(BizMsgType.values()).stream().map(BizMsgType::getType).collect(Collectors.toSet()));
        new NettyServer().start();
    }
}
