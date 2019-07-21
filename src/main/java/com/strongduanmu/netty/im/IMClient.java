package com.strongduanmu.netty.im;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Desc: 聊天程序客户端
 * Date: 2019-07-20
 *
 * @author duanzhengqiang
 */
public class IMClient {

    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new IMClientInitializer());

            ChannelFuture channelFuture = bootstrap.connect("localhost", 8888).sync();
            //读取键盘输入
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            for (; ; ) {
                //将输入字符按行写入到channel中传递给服务端
                channelFuture.channel().writeAndFlush(bufferedReader.readLine() + "\r\n");
            }
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
