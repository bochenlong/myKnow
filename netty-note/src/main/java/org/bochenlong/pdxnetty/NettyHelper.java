package org.bochenlong.pdxnetty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import org.bochenlong.pdxnetty.config.NettyConfig;
import org.bochenlong.pdxnetty.exception.RemoteException;
import org.bochenlong.pdxnetty.message.NettyMessage;
import org.bochenlong.pdxnetty.resp.NettyFuture;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static org.bochenlong.pdxnetty.ChannelManager.futureMap;

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

    public static void write(String host, NettyMessage message) {
        connect(host).writeAndFlush(message);
    }

    public static Future<NettyMessage> sendAndAccept(String host, NettyMessage message) throws RemoteException {
        Channel channel = connect(host);
        try {
            ChannelFuture future = channel.writeAndFlush(message);
            // 等待发送成功
            if (message.getHeader().getBack() == BackType.BACK.getType()) {
                future.await(NettyConfig.SEND_TIME_OUT);
            }
            Throwable cause = future.cause();
            if (cause != null) {
                throw cause;
            }
        } catch (Throwable throwable) {
            throw new RemoteException("fail send message to " + getIp(channel.remoteAddress()) + " , casuse " + throwable.getMessage());
        }
        // 封装等待Future
        return futureMap.computeIfAbsent(message.getId(), a -> new NettyFuture<>());
    }

    public static void setFutureResult(NettyMessage message) throws ExecutionException, InterruptedException {
        NettyFuture<NettyMessage> f = futureMap.get(message.getId());
        if (f == null) return;
        f.set(message);
        futureMap.remove(message.getId());
    }

    public static void remove(String host, Channel channel) {
        channelMap.remove(host, channel);
    }

    public static Channel connect(String host) {
        Channel channel = new P2pClient(host).channel();
        /*P2pClient的new过程可能会非常长，如果put的时候，已经有连接，那么断开此连接，使用已有的通道*/
        if (channelMap.putIfAbsent(host, channel) != null) {
            /*因为server会先初始化map，假如两端server同时已初始化，那么client会发生同时关闭*/
            /*因此比较host来决定关闭谁*/
            if (NettyHelper.getLocalIP().compareTo(host) >= 1) {
                channel.close();
            }
        }
        return channelMap.get(host);
    }

    public static List<Channel> connect(List<String> hosts) {
        List<Channel> channels = hosts.stream()
                .map(NettyHelper::connect)
                .collect(Collectors.toList());
        return channels;
    }

    public static void startServer() {
        new P2pServer().start();
    }
}
