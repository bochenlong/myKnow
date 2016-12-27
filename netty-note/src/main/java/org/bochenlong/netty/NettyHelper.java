package org.bochenlong.netty;

import io.netty.channel.Channel;
import org.bochenlong.netty.client.NettyClient;
import org.bochenlong.netty.server.NettyServer;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Enumeration;

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
    
    //    public static void write(String host, NettyMessage message) {
//        connect(host).writeAndFlush(message);
//    }
//
//    public static Future<NettyMessage> sendAndAccept(String host, NettyMessage message) throws RemoteException {
//        Channel channel = connect(host);
//        try {
//            ChannelFuture future = channel.writeAndFlush(message);
//            // 等待发送成功
//            if (message.getHeader().getBack() == BackType.BACK.getType()) {
//                future.await(NettyManager.SEND_TIME_OUT);
//            }
//            Throwable cause = future.cause();
//            if (cause != null) {
//                throw cause;
//            }
//        } catch (Throwable throwable) {
//            throw new RemoteException("fail send message to " + getIp(channel.remoteAddress()) + " , casuse " + throwable.getMessage());
//        }
//        // 封装等待Future
//        return futureMap.computeIfAbsent(message.getId(), a -> new NettyFuture<>());
//    }
//
//    public static void setFutureResult(NettyMessage message) throws ExecutionException, InterruptedException {
//        NettyFuture<NettyMessage> f = futureMap.get(message.getId());
//        if (f == null) return;
//        f.set(message);
//        futureMap.remove(message.getId());
//    }
//
//    public static void remove(String host, Channel channel) {
//        channelMap.remove(host, channel);
//    }
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
        new NettyServer().start();
    }
}
