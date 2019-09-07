package com.strongduanmu.netty.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Desc: WebSocket示例Initializer
 * Date: 2019-08-31
 *
 * @author duanzhengqiang
 */
public class WebSocketInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //WebSocket基于HTTP协议，需要添加上HttpServerCodec
        pipeline.addLast(new HttpServerCodec());
        //分块写入handler
        pipeline.addLast(new ChunkedWriteHandler());
        //用于将分块数据聚合成一个完整的HTTP请求或响应
        //不使用聚合，会多次执行流程，处理分块数据，每次channelRead0方法只能读取到一块数据
        //A ChannelHandler that aggregates an HttpMessage and its following HttpContents into a single FullHttpRequest or FullHttpResponse (depending on if it used to handle requests or responses) with no following HttpContents.
        //It is useful when you don't want to take care of HTTP messages whose transfer encoding is 'chunked'.
        //Insert this handler after HttpResponseDecoder in the ChannelPipeline if being used to handle responses, or after HttpRequestDecoder and HttpResponseEncoder in the ChannelPipeline if being used to handle requests.
        pipeline.addLast(new HttpObjectAggregator(8192));
        //添加WebSocket协议处理器，WebSocket数据都是以frame帧的形式传输，有文本格式的帧，也有二进制格式的帧，/ws为WebSocket的URI地址
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        //WebSocket
        pipeline.addLast(new TextWebSocketFrameHandler());
    }
}
