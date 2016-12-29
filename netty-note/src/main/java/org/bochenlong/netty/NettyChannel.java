package org.bochenlong.netty;

import io.netty.channel.Channel;
import org.bochenlong.netty.message.bean.NettyMsg;
import org.bochenlong.netty.resp.NettyFuture;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by bochenlong on 16-12-10.
 */
public class NettyChannel {
    /*host - channel map*/
    public static ConcurrentHashMap<String, Channel> channels
            = new ConcurrentHashMap<>();

    /*id - netty future*/
    public static ConcurrentHashMap<String, NettyFuture<NettyMsg>> futures
            = new ConcurrentHashMap<>();

    public static void addChannel(String host, Channel channel) {
        if (channels.put(host, channel) != null) {
            channel.close();
        }
    }
}
