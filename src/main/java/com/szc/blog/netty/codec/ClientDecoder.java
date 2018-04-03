package com.szc.blog.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import org.msgpack.MessagePack;

import com.szc.blog.netty.message.ResultMessage;

/**
 * @author shizhengchao
 * @version
 */
public class ClientDecoder extends ByteToMessageDecoder {

    private MessagePack pack;

    public ClientDecoder() {
	pack = new MessagePack();
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
	if ("EmptyByteBufBE".equals(in.toString())) {
	    // 想加一个关闭连接的编解码
	    return;
	}
	byte[] bytes = new byte[in.readInt()];
	in.readBytes(bytes);
	ResultMessage message = pack.read(bytes, ResultMessage.class);
	out.add(message);
    }
}
