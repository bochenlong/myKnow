package org.bochenlong.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.bochenlong.netty.client.handler.ClientHandler;
import org.bochenlong.netty.decoder.NettyMessageDecoder;
import org.bochenlong.netty.decoder.NettyMessageEncoder;

import java.net.InetSocketAddress;

/**
 * Created by bcl on 2016/9/7.
 */
public class NettyClient {
    public void connet(String host, int port) {
        try {
            Bootstrap b = new Bootstrap();
            EventLoopGroup workGroup = new NioEventLoopGroup();
            b.group(workGroup).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            sc.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                            sc.pipeline().addLast(new NettyMessageEncoder());
                            sc.pipeline().addLast(new ClientHandler());
                        }
                    });
            ChannelFuture f = b.connect(new InetSocketAddress(host, port)).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            connet(host, port);
        }
    }

    public static void main(String[] args) {
        new NettyClient().connet("localhost",9191);
    }
}
