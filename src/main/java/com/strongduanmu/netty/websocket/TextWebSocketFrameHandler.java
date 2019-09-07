package com.strongduanmu.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

/**
 * Desc: 文本类型帧处理Handler
 * Date: 2019-08-31
 *
 * @author duanzhengqiang
 */
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //TextWebSocketFrame用于处理WebSocket文本帧，继承自WebSocketFrame类
        System.out.println("服务端收到消息：" + msg.text());
        //返回客户端需要返回TextWebSocketFrame对象（根据协议传输不同的对象）
        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务端时间：" + LocalDateTime.now()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //全局唯一的channel id
        System.out.println("handlerAdded：" + ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved：" + ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常发生");
        //异常发生关闭连接
        ctx.close();
    }
}
