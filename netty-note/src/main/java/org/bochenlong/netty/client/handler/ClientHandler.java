package org.bochenlong.netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.bochenlong.netty.Header;
import org.bochenlong.netty.Invocation;
import org.bochenlong.netty.NettyMessage;

/**
 * Created by bcl on 2016/9/7.
 */
public class ClientHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        Header header = new Header();
        header.setSessionId(0000000001);
        header.setType((byte) 1);
        header.setPriority((byte) 1);
        Invocation invocation = new Invocation();
        invocation.setMethodName("equals");
        invocation.setClassName("String");
        invocation.setArgs(new Object[]{});
        invocation.setParameterTypes(new Class[]{});
        ctx.writeAndFlush(new NettyMessage(header, invocation));
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

