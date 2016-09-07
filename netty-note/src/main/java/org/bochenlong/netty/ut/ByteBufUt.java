package org.bochenlong.netty.ut;

import io.netty.buffer.ByteBuf;

/**
 * Created by bcl on 2016/9/7.
 */
public class ByteBufUt {
    public static ByteBuf writeBytes(ByteBuf buf, byte[] bytes) {
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
        return buf;
    }

    public static byte[] readBytes(ByteBuf buf, int length) {
        byte[] bytes = new byte[length];
        buf.readBytes(bytes);
        return bytes;
    }

}
