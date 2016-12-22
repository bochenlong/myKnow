package org.bochenlong.netty.server.auth;

import io.netty.channel.ChannelHandlerContext;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by bochenlong on 16-12-22.
 */
public class UserDefineAuth {
    private static class Holder {
        private static UserDefineAuth auth = new UserDefineAuth();
    }
    
    public static UserDefineAuth me() {
        return Holder.auth;
    }
    
    private Predicate<ChannelHandlerContext> predicate;
    private Consumer<ChannelHandlerContext> consumer;
    
    public void setPredicate(Predicate<ChannelHandlerContext> predicate) {
        me().predicate = predicate;
    }
    
    public Predicate<ChannelHandlerContext> getPredicate() {
        return me().predicate;
    }
    
    public Consumer<ChannelHandlerContext> getConsumer() {
        return consumer;
    }
    
    public void setConsumer(Consumer<ChannelHandlerContext> consumer) {
        me().consumer = consumer;
    }
}
