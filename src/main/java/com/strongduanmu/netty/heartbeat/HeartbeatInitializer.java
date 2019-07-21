package com.strongduanmu.netty.heartbeat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * Desc: 心跳示例Initializer
 * Date: 2019-07-21
 *
 * @author duanzhengqiang
 */
public class HeartbeatInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //空闲检测Handler
        //Triggers an IdleStateEvent when a Channel has not performed read, write, or both operation for a while.
        //readerIdleTime 5秒钟没有请求进来inbound触发IdleStateEvent事件
        //writerIdleTime 10秒没有请求出去outbound触发IdleStateEvent事件
        //allIdleTime 15秒没有进来或出去触发IdleStateEvent事件
        pipeline.addLast("idleStateHandler", new IdleStateHandler(5, 10, 15, TimeUnit.SECONDS));
        pipeline.addLast("heartbeatHandler", new HeartbeatHandler());
    }
}
