package org.bochenlong.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.bochenlong.netty.decoder.NettyMessageDecoder;
import org.bochenlong.netty.decoder.NettyMessageEncoder;
import org.bochenlong.netty.server.handler.ServerHandler;

/**
 * Created by bcl on 2016/8/29.
 */
public class NettyServer {
    private int port = 2022;

    public void start(int port) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        sc.pipeline().addLast(new NettyMessageDecoder(1024*1024, 4, 4));
                        sc.pipeline().addLast(new NettyMessageEncoder());
                        sc.pipeline().addLast(new ServerHandler());
                    }
                });

        b.bind("localhost",port).sync();
        System.out.println("server has run");
    }

    public static void main(String[] args) throws InterruptedException {
        new NettyServer().start(9191);
    }

}
