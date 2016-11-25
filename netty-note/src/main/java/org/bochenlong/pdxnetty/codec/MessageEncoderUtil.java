package org.bochenlong.pdxnetty.codec;

import biz.pdxtech.daap.p2p.pdxnetty.message.P2pMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Created by bochenlong on 16-11-4.
 */
public class MessageEncoderUtil {

    public static ByteBuf encode(P2pMessage p2pMessage) {
        ByteBuf byteBuf = Unpooled.buffer();
        byte[] bytes = MessageCodec.toBytes(p2pMessage);
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

}
