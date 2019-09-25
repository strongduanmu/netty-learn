package com.strongduanmu.netty.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Desc: Product PB客户端处理器
 * Date: 2019/9/23
 *
 * @author duanzhengqiang
 */
public class ProductProtobufClientHandler extends SimpleChannelInboundHandler<Product.ProductBasicInfo> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Product.ProductBasicInfo msg) throws Exception {
        System.out.println("收到服务端端消息：" + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Product.ProductBasicInfo productBasicInfo = Product.ProductBasicInfo.newBuilder().setName("客户端Netty实战").setValidate(11).setUnit("本").build();
        ctx.channel().writeAndFlush(productBasicInfo);
    }
}
