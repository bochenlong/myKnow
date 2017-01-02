package org.bochenlong.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.bochenlong.netty.message.bean.NettyMsg;

/**
 * Created by bochenlong on 16-11-4.
 */
public class MsgDecoder extends LengthFieldBasedFrameDecoder {
    
    // maxFrameLength 最大长度
    // lengthFieldOffset 偏移量
    // lengthFieldLength 表示长度的位置
    public MsgDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }
    
    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        
        if (frame == null) {
            return null;
        }
        
        int length = frame.readInt();
        
        byte[] bytes = new byte[length];
        frame.readBytes(bytes);
        
        
        NettyMsg message = MsgCodec.toObject(bytes, NettyMsg.class);
        message.getHeader().setLength(length);
        
        return message;
    }
}
