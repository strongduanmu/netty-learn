package com.strongduanmu.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Desc: 响应Http请求，返回HelloWorld
 * Date: 2018/10/5
 *
 * @author duanzhengqiang
 */
public class HttpServer {

    public static void main(String[] args) throws InterruptedException {
        //创建线程组，NioEventLoopGroup理解成死循环，不停地提供服务
        //bossGroup负责接收客户端连接，workerGroup负责实际业务处理，由bossGroup分配
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //用于服务端启动
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //bossGroup也叫acceptor，workerGroup也叫client，属于NIO中的概念
            //使用NioServerSocketChannel通道，通过反射创建该实例
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    //添加服务初始化处理，在初始化处理中，向pipeline中添加不同的handler
                    .childHandler(new HttpServerInitializer());

            //服务启动绑定端口
            ChannelFuture channelFuture = serverBootstrap.bind(9999).sync();
            //关闭服务同步更新
            channelFuture.channel().closeFuture().sync();
        } finally {
            //优雅关闭线程组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
