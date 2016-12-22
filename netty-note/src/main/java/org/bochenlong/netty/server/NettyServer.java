package org.bochenlong.netty.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bochenlong.netty.codec.MessageDecoder;
import org.bochenlong.netty.codec.MessageEncoder;
import org.bochenlong.netty.config.NettyConfig;
import org.bochenlong.netty.server.handler.ServerAuthInHandler;
import org.bochenlong.netty.server.handler.ServerP2pInHandler;

/**
 * Created by bochenlong on 16-11-3.
 */
public class NettyServer {
    private static Logger logger = LogManager.getLogger(NettyServer.class);
    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;

    private void bind(int port) {
        try {
            bossGroup = new NioEventLoopGroup();
            workGroup = new NioEventLoopGroup();

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    // for bossGroup
                    .option(ChannelOption.SO_BACKLOG, NettyConfig.BACKLOG_SIZE)
                    .option(ChannelOption.SO_KEEPALIVE, true)// 保活
                    // for workGroup
                    .childOption(ChannelOption.TCP_NODELAY, false)// 有数据就发，默认false
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(
                                    new MessageDecoder(
                                            NettyConfig.MSG_MAX_LEN,
                                            NettyConfig.MSG_LEN_OFFSET,
                                            NettyConfig.MSG_LEN_FIELD));
                            ch.pipeline().addLast(new MessageEncoder());
                            ch.pipeline().addLast(new ServerAuthInHandler());
                            ch.pipeline().addLast(new ServerP2pInHandler());
                        }
                    });

            // 绑定端口，等待启动成功
            ChannelFuture future = bootstrap.bind(NettyConfig.DEFAULT_HOST, port).sync();
            logger.info("P2pServer start ok : {} - {}", NettyConfig.DEFAULT_HOST, port);
            // 监听关闭事件
            future.channel().closeFuture().addListener(a -> {
                if (a.isDone()) {
                    logger.info("P2pServer stop : {} - {}", NettyConfig.DEFAULT_HOST, port);
                    // 关闭资源
                    this.stop();
                }
            });
        } catch (Exception e) {
            logger.error("P2pServer start exception : {} - {} / {}", e.getMessage());
            e.printStackTrace();
            // 关闭资源
            this.stop();
        }
    }

    public void start(int port) {
        bind(port);
    }

    public void start() {
        start(NettyConfig.DEFAULT_PORT);
    }

    protected void stop() {
        workGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
        logger.error("P2pServer stop over");
    }

}
