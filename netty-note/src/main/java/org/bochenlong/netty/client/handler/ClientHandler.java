package org.bochenlong.netty.client.handler;

import io.netty.channel.*;
import org.bochenlong.netty.Header;
import org.bochenlong.netty.Invocation;
import org.bochenlong.netty.NettyMessage;
import org.bochenlong.netty.codec.ProtostuffCodec;

/**
 * Created by bcl on 2016/9/7.
 */
public class ClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Header header = new Header();
        header.setType((byte) 1);
        header.setPriority((byte) 1);
        Invocation invocation = new Invocation();
        invocation.setMethodName("equals");
        invocation.setClassName("String");
        invocation.setArgs(new Object[]{"abc", "Hello"});
        invocation.setParameterTypes(new Class[]{String.class, String.class});
        for (int i = 0; i < 100; i++) {
            header.setSessionId(i);
             ctx.writeAndFlush(new NettyMessage(header, invocation));
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
    
}

