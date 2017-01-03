package org.bochenlong.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bochenlong.netty.message.bean.NettyMsg;

public class ServerBizHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LogManager.getLogger(ServerBizHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("read {}", ctx);
        NettyMsg message = (NettyMsg) msg;


        // 释放
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.debug("read complete {}", ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warn("{} exception {}", ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }

}