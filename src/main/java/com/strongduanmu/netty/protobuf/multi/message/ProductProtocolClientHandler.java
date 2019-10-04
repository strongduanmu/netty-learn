package com.strongduanmu.netty.protobuf.multi.message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * Desc: 商品多消息协议客户端处理器
 * Date: 2019-10-04
 *
 * @author duanzhengqiang
 */
public class ProductProtocolClientHandler extends SimpleChannelInboundHandler<ProductProtocol.ProductMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProductProtocol.ProductMessage msg) throws Exception {
        System.out.println("收到服务端发送的信息:" + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //和服务端建立连接时，向服务端发送消息，随机构建三种消息类型
        ProductProtocol.ProductMessage productMessage;
        int randomNum = new Random().nextInt(999);
        if (randomNum % 3 == 0) {
            productMessage = ProductProtocol.ProductMessage.newBuilder()
                    .setProductType(ProductProtocol.ProductMessage.Type.BASIC_TYPE)
                    .setBasicInfo(ProductProtocol.ProductBasicInfo.newBuilder().setName("Netty实战")
                            .setBrand("Java").setSellPrice(999).setStockNum(10).build()).build();
        } else if (randomNum % 3 == 1) {
            productMessage = ProductProtocol.ProductMessage.newBuilder()
                    .setProductType(ProductProtocol.ProductMessage.Type.PROMOTION_TYPE)
                    .setPromotionInfo(ProductProtocol.ProductPromotionInfo.newBuilder().setPromotionPrice(100)
                            .setType(1).setDescription("超级大促销，限时一天").build()).build();
        } else {
            productMessage = ProductProtocol.ProductMessage.newBuilder()
                    .setProductType(ProductProtocol.ProductMessage.Type.IMG_TYPE)
                    .setImgInfo(ProductProtocol.ProductImgInfo.newBuilder().setUrl("www.baidu.com")
                            .setDescription("图片描述").build()).build();
        }
        ctx.writeAndFlush(productMessage);
    }
}
