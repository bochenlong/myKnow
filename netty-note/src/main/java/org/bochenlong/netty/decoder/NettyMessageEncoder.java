package org.bochenlong.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.bochenlong.netty.Invocation;
import org.bochenlong.netty.NettyMessage;
import org.bochenlong.netty.codec.ICodec;
import org.bochenlong.netty.codec.ProtostuffCodec;
import org.bochenlong.netty.ut.ByteBufUt;

import java.util.List;
import java.util.Map;

/**
 * Created by bcl on 2016/8/30.
 * NettyMessage解码器
 */
public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {
    private ProtostuffCodec protostuffCodec;

    public NettyMessageEncoder() {
        this.protostuffCodec = new ProtostuffCodec();
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, NettyMessage nettyMessage, List<Object> list) throws Exception {
        if (nettyMessage == null || nettyMessage.getHeader() == null) {
            throw new Exception("NettyMessage can't be null");
        }

        // 处理header部分
        ByteBuf sendBuf = Unpooled.buffer();
        sendBuf.writeInt(nettyMessage.getHeader().getCrcCode());
        sendBuf.writeInt(nettyMessage.getHeader().getLength());
        sendBuf.writeLong(nettyMessage.getHeader().getSessionId());
        sendBuf.writeByte(nettyMessage.getHeader().getType());
        sendBuf.writeByte(nettyMessage.getHeader().getPriority());
        sendBuf.writeInt(nettyMessage.getHeader().getAttacment().size());// 附件大小
        for (Map.Entry<String, Object> entrys : nettyMessage.getHeader().getAttacment().entrySet()) {
            ByteBufUt.writeBytes(sendBuf, entrys.getKey().getBytes("UTF-8"));
            ByteBufUt.writeBytes(sendBuf, protostuffCodec.toBytes(entrys.getValue()));
        }

        // 处理body部分
        Invocation invocation = nettyMessage.getBody();
        ByteBufUt.writeBytes(sendBuf, protostuffCodec.toBytes(invocation));

        sendBuf.setInt(4, sendBuf.readableBytes());// 消息长度设置为实际的长度

        list.add(sendBuf);
    }
}
