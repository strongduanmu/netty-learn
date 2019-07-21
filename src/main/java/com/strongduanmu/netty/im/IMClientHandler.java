package com.strongduanmu.netty.im;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Desc: 聊天客户端处理器
 * Date: 2019-07-20
 *
 * @author duanzhengqiang
 */
public class IMClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }
}
