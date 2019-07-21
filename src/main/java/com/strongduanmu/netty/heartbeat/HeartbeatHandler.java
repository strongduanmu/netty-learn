package com.strongduanmu.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Desc: 心跳检测处理器
 * Date: 2019-07-21
 *
 * @author duanzhengqiang
 */
public class HeartbeatHandler extends ChannelInboundHandlerAdapter {

    /**
     * ChannelInboundHandlerAdapter中的userEventTriggered会调用ChannelHandlerContext.fireUserEventTriggered方法
     * 将触发的用户事件转发到ChannelPipeline中的下一个ChannelInboundHandler中
     * Calls ChannelHandlerContext.fireUserEventTriggered(Object) to forward to the next ChannelInboundHandler in the ChannelPipeline. Sub-classes may override this method to change behavior.
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            //三种空闲状态：读空闲-READER_IDLE、写空闲-WRITER_IDLE、读写空闲-ALL_IDLE
            System.out.println(ctx.channel().remoteAddress() + "超时事件：" + idleStateEvent.state());
            //关闭超时channel连接
            ctx.channel().close();
        }
    }
}
