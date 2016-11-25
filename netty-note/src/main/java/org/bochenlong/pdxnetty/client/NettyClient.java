package org.bochenlong.pdxnetty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bochenlong.pdxnetty.client.handlers.ClientInHandler;
import org.bochenlong.pdxnetty.codec.MessageDecoder;
import org.bochenlong.pdxnetty.codec.MessageEncoder;
import org.bochenlong.pdxnetty.config.NettyConfig;

/**
 * Created by bochenlong on 16-11-3.
 */
public class NettyClient {
    private static Logger logger = LogManager.getLogger(NettyClient.class);

    private Channel channel;

    public NettyClient(String host) {
        connect(host, NettyConfig.DEFAULT_PORT);
    }

    public NettyClient(String host, int port) {
        connect(host, port);
    }

    private EventLoopGroup workGroup;

    private void connect(String host, int port) {
        try {
            workGroup = new NioEventLoopGroup();

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)// 保活
                    .option(ChannelOption.TCP_NODELAY, false)// 有数据就发
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, NettyConfig.CONNECT_TIME_OUT) // 连接超时时间
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new MessageDecoder(NettyConfig.MSG_MAX_LEN
                                    , NettyConfig.MSG_LEN_OFFSET, NettyConfig.MSG_LEN_FIELD));
                            ch.pipeline().addLast(new MessageEncoder());
                            ch.pipeline().addLast(new ClientInHandler());
                        }
                    });

            ChannelFuture future = bootstrap.connect(host, port).sync();
            this.channel = future.channel();
            logger.info("NettyClient connect ok {} - {}", host, port);
            this.channel.closeFuture().addListener(a -> {
                logger.info("NettyClient connect close {} - {}", host, port);
                // 关闭资源
                this.close();
            });
        } catch (Exception e) {
            logger.error("NettyClient connect exception : {} - {} / {}", host, port, e.getMessage());
            // 关闭资源
            this.close();
            e.printStackTrace();
        }
    }

    public void close() {
        workGroup.shutdownGracefully();
        logger.info("NettyClient close over");
    }

    public Channel channel() {
        return this.channel;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.channel.id().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NettyClient)) {
            return false;
        }
        NettyClient p = (NettyClient) obj;
        return p.channel.equals(this.channel);
    }

}
