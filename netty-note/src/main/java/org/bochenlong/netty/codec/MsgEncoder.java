package org.bochenlong.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.bochenlong.netty.message.bean.NettyMsg;

import java.util.List;

/**
 * Created by bochenlong on 16-11-4.
 */
public class MsgEncoder extends MessageToMessageEncoder<NettyMsg> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, NettyMsg message, List<Object> list) throws Exception {
        if (message == null || message.getHeader() == null) {
            throw new Exception("The encode message is null");
        }

        ByteBuf byteBuf = MsgEncoderUtil.encode(message);

        list.add(byteBuf);
    }
}
