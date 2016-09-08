package org.bochenlong.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.bochenlong.netty.Header;
import org.bochenlong.netty.Invocation;
import org.bochenlong.netty.NettyMessage;
import org.bochenlong.netty.codec.ProtostuffCodec;
import org.bochenlong.netty.ut.ByteBufUt;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bcl on 2016/9/7.
 */
public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {
    private ProtostuffCodec protostuffCodec;

    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
        protostuffCodec = new ProtostuffCodec();
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf byteBuf = (ByteBuf) super.decode(ctx, in);
        if (byteBuf == null)
            return null;

        Header header = new Header();
        header.setCrcCode(in.readInt());
        header.setLength(in.readInt());
        header.setSessionId(in.readLong());
        header.setType(in.readByte());
        header.setPriority(in.readByte());
        int size = in.readInt();
        if (size > 0) {
            Map<String, Object> attch = new HashMap<>(size);
            String key;
            Object object;
            for (int i = 0; i < size; i++) {
                key = new String(ByteBufUt.readBytes(in, in.readInt()), "UTF-8");
                object = protostuffCodec.toObject(ByteBufUt.readBytes(in, in.readInt()), Object.class);
                attch.put(key, object);
            }
        }

        Invocation invocation = (Invocation) protostuffCodec.toObject(ByteBufUt.readBytes(in, in.readInt()),Invocation.class);
        return new NettyMessage(header, invocation);
    }
}
