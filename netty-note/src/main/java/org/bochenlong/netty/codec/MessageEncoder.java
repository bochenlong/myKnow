package org.bochenlong.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.bochenlong.netty.message.NettyMessage;

import java.util.List;

/**
 * Created by bochenlong on 16-11-4.
 */
public class MessageEncoder extends MessageToMessageEncoder<NettyMessage> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, NettyMessage message, List<Object> list) throws Exception {
        if (message == null || message.getHeader() == null) {
            throw new Exception("The encode message is null");
        }

        ByteBuf byteBuf = MessageEncoderUtil.encode(message);

        list.add(byteBuf);
    }
}
