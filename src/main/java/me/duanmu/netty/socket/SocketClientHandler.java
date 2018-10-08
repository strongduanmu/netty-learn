package me.duanmu.netty.socket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * Desc: Socket客户端处理器
 * Date: 2018/10/7
 *
 * @author duanzhengqiang
 */
public class SocketClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //打印来自服务端的消息，并向服务端发送消息
        System.out.println("server ip: " + ctx.channel().remoteAddress() + ", server msg: " + msg);
        Thread.sleep(2000);
        ctx.channel().writeAndFlush("from client " + LocalDateTime.now());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //异常关闭连接
        ctx.close();
    }
}
