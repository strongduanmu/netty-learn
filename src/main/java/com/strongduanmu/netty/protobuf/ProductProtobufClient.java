package com.strongduanmu.netty.protobuf;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Desc: Protobuf传输服务端
 * Date: 2019/9/21
 *
 * @author duanzhengqiang
 */
public class ProductProtobufClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup).channel(NioSocketChannel.class).handler(new ProductProtobufClientInitializer());
            ChannelFuture channelFuture = bootstrap.connect("localhost", 7777).sync();
            Channel channel = channelFuture.channel();
            channel.closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}