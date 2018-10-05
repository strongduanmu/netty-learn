package me.duanmu.netty.helloworld;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/**
 * Desc: 自定义Http处理器
 * Date: 2018/10/5
 *
 * @author duanzhengqiang
 */
public class HelloWorldHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * 读取客户端请求，处理请求，然后返回响应给客户端
     * 启动后，执行：curl 'http://localhost:9999'，出现如下异常：
     * 十月 05, 2018 9:54:23 下午 io.netty.channel.DefaultChannelPipeline onUnhandledInboundException
     * 警告: An exceptionCaught() event was fired, and it reached at the tail of the pipeline. It usually means the last handler in the pipeline did not handle the exception.
     * java.io.IOException: Connection reset by peer
     * 需要添加一个判断，HttpObject msg必须是HttpRequest的实例
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest) {
            //创建响应内容，存储在ByteBuf中
            ByteBuf content = Unpooled.copiedBuffer("Hello World Netty", CharsetUtil.UTF_8);
            //创建响应对象，需要制定HTTP版本，状态码及内容
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            //设置响应头
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            //返回内容，如果不flush，数据会存储在缓冲区，而不会返回客户端
            ctx.writeAndFlush(response);
        }
    }
}