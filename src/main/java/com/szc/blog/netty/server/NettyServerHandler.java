package com.szc.blog.netty.server;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.lang.reflect.Method;

import com.szc.blog.netty.message.InvokeMessage;
import com.szc.blog.netty.message.ResultMessage;

/**
 * @author shizhengchao
 * @version
 */
public class NettyServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	InvokeMessage message = (InvokeMessage) msg;
	if (message == null) {
	    throw new Exception("msg is null");
	}
	Object o = Class.forName(message.getClassName()).newInstance();
	Method method = o.getClass().getMethod(message.getMethod(), message.getTypes());    
        Object result = method.invoke(o, message.getArgs());
        ResultMessage resp = new ResultMessage();
        resp.setCode(0);
        resp.setResult(result);
	ctx.writeAndFlush(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
	cause.printStackTrace();
	ctx.close();
    }

}
