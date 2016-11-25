package org.bochenlong.pdxnetty.server.handlers;

import biz.pdxtech.daap.p2p.P2pUtil;
import biz.pdxtech.daap.p2p.pdxnetty.P2pNettyUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by bochenlong on 16-11-5.
 * <p>
 * 负责链路处理
 * <p>
 * 目前链路都保持双向连接，即A -> B 但不排斥B -> A
 */
public class ServerConnectInHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LogManager.getLogger(ServerConnectInHandler.class);

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warn("{} exception {}", ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("active {}", ctx);
        String remoteAddress = P2pNettyUtil.getRemoteIp(ctx.channel().remoteAddress());
        // 如果已经有连接，则关闭
        if (P2pNettyUtil.channelMap.putIfAbsent(remoteAddress, ctx.channel()) != null) {
            ctx.close();
        }

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("inactive {}", ctx);
        // 删除本地连接
        P2pUtil.close(P2pNettyUtil.getRemoteIp(ctx.channel().remoteAddress()));

        // inactive 需要传递，因为auth删除对应连接认证
        ctx.fireChannelInactive();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        logger.info("registered {}", ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        logger.info("unregistered {}", ctx);
    }
}
