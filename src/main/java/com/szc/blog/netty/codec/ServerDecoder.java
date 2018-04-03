package com.szc.blog.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import org.msgpack.MessagePack;

import com.szc.blog.netty.message.InvokeMessage;

/**
 * @author shizhengchao
 * @version
 */
public class ServerDecoder extends ByteToMessageDecoder {

    private MessagePack pack;

    public ServerDecoder() {
	pack = new MessagePack();
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
	if ("EmptyByteBufBE".equals(in.toString())) {
	    // 想加一个关闭连接的编解码
	    return;
	}
	byte[] messageByte = new byte[in.readInt()];
	in.readBytes(messageByte);
	InvokeMessage message = pack.read(messageByte, InvokeMessage.class);
	out.add(message);
    }
}
