package com.strongduanmu.netty.protobuf.multi.message;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Desc: 商品多消息协议客户端
 * Date: 2019-10-04
 *
 * @author duanzhengqiang
 */
public class ProductProtocolClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(bossGroup).channel(NioSocketChannel.class).handler(new ProductProtocolClientInitializer());
            ChannelFuture channelFuture = bootstrap.connect("localhost", 9988).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
        }
    }
}
