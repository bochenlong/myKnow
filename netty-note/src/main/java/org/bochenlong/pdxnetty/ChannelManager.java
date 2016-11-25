package org.bochenlong.pdxnetty;

import io.netty.channel.Channel;
import org.bochenlong.pdxnetty.message.NettyMessage;
import org.bochenlong.pdxnetty.resp.NettyFuture;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by bochenlong on 16-11-15.
 */
public class ChannelManager {
    /*host - channel map*/
    public static ConcurrentHashMap<String, Channel> channelMap
            = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String, NettyFuture<NettyMessage>> futureMap
            = new ConcurrentHashMap<>();
}
