package me.duanmu.netty.im;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Desc: 描述
 * Date: 2018/10/9
 *
 * @author duanzhengqiang
 */
public class IMServerHandler extends SimpleChannelInboundHandler<String> {

    private ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (ch.id() != channel.id()) {
                ch.writeAndFlush(ch.remoteAddress() + "发送消息：" + msg + "\n");
            } else {
                channel.writeAndFlush("自己发送消息：" + msg + "\n");
            }
        });

    }

    /**
     * 客户端与服务端建立连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //获取连接对象
        Channel channel = ctx.channel();
        //建立连接之后，需要向其他已经连接的客户端发送通知
        //那么就需要在服务端保存所有连接的Channel对象
        //通知其他channel，writeAndFlush会调用channelGroup中所有channel的writeAndFlush方法
        channelGroup.writeAndFlush("[服务器]" + channel.remoteAddress() + "加入\n");
        channelGroup.add(channel);
    }

    /**
     * 客户端与服务端断开连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[服务器]" + channel.remoteAddress() + "离开\n");
        //netty在channel断开连接时，会自动地从channelGroup中删除该channel
        //channelGroup.remove(channel);
    }

    /**
     * 连接处于活动状态
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(channel.remoteAddress() + "上线\n");
    }

    /**
     * 连接处于非活动状态
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(channel.remoteAddress() + "下线\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
