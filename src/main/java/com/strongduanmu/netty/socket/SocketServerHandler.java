package com.strongduanmu.netty.socket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * Desc: SocketServer处理器
 * Date: 2018/10/7
 *
 * @author duanzhengqiang
 */
public class SocketServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("client ip: " + ctx.channel().remoteAddress() + ", client msg: " + msg);
        //返回给客户端信息
        ctx.channel().writeAndFlush("from server " + LocalDateTime.now());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //出现异常关闭连接
        ctx.close();
    }
}
