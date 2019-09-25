package com.strongduanmu.netty.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Desc: Product PB服务端处理器
 * Date: 2019/9/23
 *
 * @author duanzhengqiang
 */
public class ProductProtobufServerHandler extends SimpleChannelInboundHandler<Product.ProductBasicInfo> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Product.ProductBasicInfo msg) throws Exception {
        System.out.println("收到客户端消息：" + msg);
        Product.ProductBasicInfo productBasicInfo = Product.ProductBasicInfo.newBuilder().setName("服务端Netty实战").setValidate(111).setUnit("个").build();
        ctx.channel().writeAndFlush(productBasicInfo);
    }
}
