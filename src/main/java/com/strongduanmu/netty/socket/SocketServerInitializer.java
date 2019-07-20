package com.strongduanmu.netty.socket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * Desc: Socket服务端初始化
 * Date: 2018/10/7
 *
 * @author duanzhengqiang
 */
public class SocketServerInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * 一旦客户端和服务端建立连接，该方法就会调用
     *
     * @param ch
     */
    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        //开发时最好指定名称
        //解码器，根据接收的ByteBufs字段值的长度进行自动切割
        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
        pipeline.addLast(new LengthFieldPrepender(4));
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        pipeline.addLast(new SocketServerHandler());
    }
}