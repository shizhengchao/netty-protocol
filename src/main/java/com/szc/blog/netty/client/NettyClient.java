package com.szc.blog.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.szc.blog.netty.codec.ClientDecoder;
import com.szc.blog.netty.codec.ClientEncoder;

/**
 * @author shizhengchao
 * @date 2017年6月27日 下午4:45:24
 * @version
 */
public class NettyClient {

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    EventLoopGroup group = new NioEventLoopGroup();

    public void connect(final int port, final String host) throws Exception {

	// 配置客户端NIO线程组

	try {
	    Bootstrap b = new Bootstrap();
	    b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {

		@Override
		public void initChannel(SocketChannel ch) throws Exception {
		    ch.pipeline().addLast(new LoggingHandler(LogLevel.ERROR));
		    ch.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
		    ch.pipeline().addLast("MessageDecoder", new ClientDecoder());
		    ch.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));
		    ch.pipeline().addLast("MessageEncoder", new ClientEncoder());
		    ch.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(500));
		    ch.pipeline().addLast("handler", new NettyClientHandler());
		    //ch.pipeline().addLast("HeartBeatHandler", new HeartBeatReqHandler());
		}
	    });
	    // 发起异步连接操作,连接远程ip,并绑定本地ip
	    ChannelFuture future = b.connect(new InetSocketAddress(host, port), new InetSocketAddress("127.0.0.1", 12088)).sync();
	    future.channel().closeFuture().sync();
	} finally {
	    // 所有资源释放完成之后，清空资源，再次发起重连操作
	    executor.execute(new Runnable() {

		@Override
		public void run() {
		    try {
			TimeUnit.SECONDS.sleep(1);
			try {
			    connect(port, host);// 发起重连操作
			} catch (Exception e) {
			    e.printStackTrace();
			}
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
	    });
	}
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
	new NettyClient().connect(8080, "127.0.0.1");
    }

}
