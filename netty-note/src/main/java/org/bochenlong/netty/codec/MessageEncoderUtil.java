package org.bochenlong.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.bochenlong.netty.message.NettyMessage;

/**
 * Created by bochenlong on 16-11-4.
 */
public class MessageEncoderUtil {

    public static ByteBuf encode(NettyMessage p2pMessage) {
        ByteBuf byteBuf = Unpooled.buffer();
        byte[] bytes = MessageCodec.toBytes(p2pMessage);
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

}
