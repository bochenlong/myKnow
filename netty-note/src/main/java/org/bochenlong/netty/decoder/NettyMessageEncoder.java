package org.bochenlong.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.bochenlong.netty.NettyMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by bcl on 2016/8/30.
 * NettyMessage解码器
 */
public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, NettyMessage nettyMessage, List<Object> list) throws Exception {
        if (nettyMessage == null || nettyMessage.getHeader() == null) {
            throw new Exception("NettyMessage can't be null");
        }

        ByteBuf sendBuf = Unpooled.buffer();
        sendBuf.writeInt(nettyMessage.getHeader().getCrcCode());
        sendBuf.writeInt(nettyMessage.getHeader().getLength());
        sendBuf.writeLong(nettyMessage.getHeader().getSessionId());
        sendBuf.writeByte(nettyMessage.getHeader().getType());
        sendBuf.writeByte(nettyMessage.getHeader().getPriority());
        sendBuf.writeInt(nettyMessage.getHeader().getAttacment().size());// 附件大小
        String key ;
        Object value;
        byte[] bytes;
        for (Map.Entry<String, Object> entrys : nettyMessage.getHeader().getAttacment().entrySet()) {
            key = entrys.getKey();
            bytes = key.getBytes("UTF-8");
            sendBuf.writeInt(bytes.length);
            sendBuf.writeBytes(bytes);
            value = entrys.getValue();
        }

    }
}
