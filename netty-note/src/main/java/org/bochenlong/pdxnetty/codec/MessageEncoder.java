package org.bochenlong.pdxnetty.codec;

import biz.pdxtech.daap.p2p.pdxnetty.message.P2pMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Created by bochenlong on 16-11-4.
 */
public class MessageEncoder extends MessageToMessageEncoder<P2pMessage> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, P2pMessage p2pMessage, List<Object> list) throws Exception {
        if (p2pMessage == null || p2pMessage.getHeader() == null) {
            throw new Exception("The encode message is null");
        }

        ByteBuf byteBuf = MessageEncoderUtil.encode(p2pMessage);

        list.add(byteBuf);
    }
}
