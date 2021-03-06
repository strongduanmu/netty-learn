package com.strongduanmu.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Desc: 服务初始化器
 * Date: 2018/10/5
 *
 * @author duanzhengqiang
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * Channel一旦建立，就会调用initChannel方法，类似于一个回调方法
     *
     * @param socketChannel
     */
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        //通过SocketChannel获取ChannelPipeline（包含多个ChannelHandler，ChannelHandler相当于拦截器）
        ChannelPipeline pipeline = socketChannel.pipeline();
        //添加对http请求的处理器
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        //添加自定义http处理器，不指定名称，netty会创建一个name
        pipeline.addLast("httpServerHandler", new HttpServerHandler());
    }
}
