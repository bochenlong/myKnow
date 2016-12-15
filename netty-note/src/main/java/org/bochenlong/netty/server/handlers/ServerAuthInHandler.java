package org.bochenlong.netty.server.handlers;

import biz.pdxtech.daap.p2p.P2pUtil;
import biz.pdxtech.daap.p2p.pdxnetty.P2pNettyUtil;
import biz.pdxtech.daap.p2p.pdxnetty.message.MessageType;
import biz.pdxtech.daap.p2p.pdxnetty.message.P2pMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by bochenlong on 16-11-4.
 */
public class ServerAuthInHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LogManager.getLogger(ServerAuthInHandler.class);
    private static ConcurrentHashMap<String, Channel> authHosts
            = new ConcurrentHashMap<>();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("read {}", ctx);
        P2pMessage p2pMessage = (P2pMessage) msg;
        // 如果是认证信息
        if (p2pMessage.getHeader().getType() == MessageType.AUTH.getType()) {

            String address = P2pNettyUtil.getRemoteIp(ctx.channel().remoteAddress());
            if (authHosts.putIfAbsent(address, ctx.channel()) == null) { // 如果没有认证，则认证
                logger.info("P2p auth {}", address);
            } else { // 否则已经认证，关闭连接
                logger.info("P2p have authed {}", address);

                ctx.close();// 关闭
            }
            // 释放buf
            // 从InBound里读取的ByteBuf要手动释放
            ReferenceCountUtil.release(msg);
        } else {
            // 否则消息传递
            ctx.fireChannelRead(msg);
        }
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

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("inactive {}", ctx);
        String address = P2pNettyUtil.getRemoteIp(ctx.channel().remoteAddress());
        P2pUtil.removeAll(address);
        logger.info("P2p remove auth {}", address);
        authHosts.remove(address, ctx.channel());
    }
}
