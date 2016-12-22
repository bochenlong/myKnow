package org.bochenlong.netty.server.auth;

import io.netty.channel.ChannelHandlerContext;
import org.bochenlong.netty.config.AuthConfig;
import org.bochenlong.netty.server.auth.impl.SingleConAuth;
import org.bochenlong.netty.server.auth.impl.UserDefineAuth;
import org.bochenlong.netty.server.auth.impl.WhiteListAuth;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by bochenlong on 16-12-22.
 */
public class AuthHelper {
    public static boolean auth(ChannelHandlerContext ctx) {
        if (AuthConfig.authType() == AuthConfig.AuthType.WHITE_LIST) {
            return auth(ctx, WhiteListAuth.me().getPredicate());
        }
        
        if (AuthConfig.authType() == AuthConfig.AuthType.SINGLE_CON) {
            return auth(ctx, SingleConAuth.me().getPredicate());
        }
        
        if (AuthConfig.authType() == AuthConfig.AuthType.USER_DEFINE) {
            return auth(ctx, UserDefineAuth.me().getPredicate());
        }
        return false;
    }
    
    public static void removeAuth(ChannelHandlerContext ctx) {
        if (AuthConfig.authType() == AuthConfig.AuthType.WHITE_LIST) {
            removeAuth(ctx, WhiteListAuth.me().getConsumer());
        }
        
        if (AuthConfig.authType() == AuthConfig.AuthType.SINGLE_CON) {
            removeAuth(ctx, SingleConAuth.me().getConsumer());
        }
        
        if (AuthConfig.authType() == AuthConfig.AuthType.USER_DEFINE) {
            removeAuth(ctx, UserDefineAuth.me().getConsumer());
        }
    }
    
    private static boolean auth(ChannelHandlerContext ctx, Predicate predicate) {
        if (predicate.test(ctx)) return true;
        return false;
    }
    
    private static void removeAuth(ChannelHandlerContext ctx, Consumer consumer) {
        consumer.accept(ctx);
    }
}
