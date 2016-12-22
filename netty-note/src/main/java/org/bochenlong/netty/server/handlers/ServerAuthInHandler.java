package org.bochenlong.netty.server.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bochenlong.netty.message.MessageType;
import org.bochenlong.netty.message.NettyMessage;
import org.bochenlong.netty.server.auth.AuthHelper;

/**
 * Created by bochenlong on 16-11-4.
 */
public class ServerAuthInHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LogManager.getLogger(ServerAuthInHandler.class);
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.debug("read {}", ctx);
        NettyMessage message = (NettyMessage) msg;
        if (message.getHeader().getType() == MessageType.AUTH.getType()) {
            if (AuthHelper.auth(ctx)) {
                logger.info("Server auth success {}", ctx);
            } else {
                ctx.close();
                logger.info("Server auth fail {}", ctx);
                
            }
            ReferenceCountUtil.release(msg);
        } else {
            ctx.fireChannelRead(msg);
        }
    }
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.debug("read complete {}", ctx);
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("{} exception {}", ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.debug("inactive {}", ctx);
        AuthHelper.removeAuth(ctx);
        logger.info("Server auth remove {}", ctx);
    
    }
}
