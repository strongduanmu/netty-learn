package com.strongduanmu.netty.protobuf;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Desc: Protobuf传输服务端
 * Date: 2019/9/21
 *
 * @author duanzhengqiang
 */
public class ProductProtobufServer {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            ServerBootstrap serverBootstrap = bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ProductProtobufServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(7777).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}