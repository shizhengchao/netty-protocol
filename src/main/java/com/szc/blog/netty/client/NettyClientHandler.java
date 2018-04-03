package com.szc.blog.netty.client;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import com.szc.blog.netty.message.InvokeMessage;
import com.szc.blog.netty.message.ResultMessage;

/**
 * @author shizhengchao
 * @version
 */
public class NettyClientHandler extends ChannelHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
	cause.printStackTrace();
	ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
	InvokeMessage message = new InvokeMessage();
	message.setClassName("com.szc.blog.service.ProductService");
	message.setMethod("getProductName");
//	message.setClazz(clazz);
	ctx.writeAndFlush(message);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	if (msg == null) {
	    ctx.close();
	    return;
	}
	ResultMessage resp = (ResultMessage) msg;
	System.out.println("Login is ok : ");
	ctx.fireChannelRead(msg);
    }
}
