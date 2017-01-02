package org.bochenlong.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.bochenlong.netty.message.bean.NettyMsg;

/**
 * Created by bochenlong on 16-11-4.
 */
public class MsgEncoderUtil {

    public static ByteBuf encode(NettyMsg p2pMessage) {
        ByteBuf byteBuf = Unpooled.buffer();
        byte[] bytes = MsgCodec.toBytes(p2pMessage);
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

}
