package com.strongduanmu.netty.im;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;


/**
 * Desc: 聊天客户端初始化
 * Date: 2019-07-20
 *
 * @author duanzhengqiang
 */
public class IMClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //根据分隔符进行解码
        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
        ch.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
        ch.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
        //自定义handler
        ch.pipeline().addLast(new IMClientHandler());
    }
}
