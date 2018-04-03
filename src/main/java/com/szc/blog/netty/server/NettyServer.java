package com.szc.blog.netty.server;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

import com.szc.blog.netty.codec.ServerDecoder;
import com.szc.blog.netty.codec.ServerEncoder;

/**
 * @author shizhengchao
 * @version
 */
public class NettyServer {

    public void bind(int port) throws InterruptedException {
	EventLoopGroup bossGroup = new NioEventLoopGroup();
	EventLoopGroup workGroup = new NioEventLoopGroup();
	try {
	    ServerBootstrap b = new ServerBootstrap();
	    b.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {

		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
		    /* 服务端 */
		    ch.pipeline().addLast(new LoggingHandler(LogLevel.ERROR));
		    ch.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
		    //server端是先收到消息,所以先解码
		    ch.pipeline().addLast("MessageDecoder", new ServerDecoder());
		    ch.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));
		    ch.pipeline().addLast("MessageEncoder", new ServerEncoder());
		    ch.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(500));
		    ch.pipeline().addLast(new NettyServerHandler());
		    // ch.pipeline().addLast("HeartBeatHandler", new
		    // HeartBeatRespHandler());
		}
	    });
	    ChannelFuture f = b.bind(new InetSocketAddress(port)).sync();
	    System.out.println("Netty server start ok : ");
	    f.channel().closeFuture().sync();
	} finally {
	    bossGroup.shutdownGracefully();
	    workGroup.shutdownGracefully();
	}
    }

    public static void main(String[] args) {
	try {
	    new NettyServer().bind(8080);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }
}
