package com.szc.blog.netty.codec; 

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import org.msgpack.MessagePack;

import com.szc.blog.netty.message.ResultMessage;


/**
 * 对nettymessage编码,转成byte
 * @author	shizhengchao
 * @version      
 */
public class ServerEncoder extends MessageToByteEncoder<ResultMessage> {
    
    private MessagePack pack;
    
    public ServerEncoder() {
	pack = new MessagePack();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ResultMessage result, ByteBuf out) throws Exception {
	if (result == null || result.getRequestId() == null || result.getRequestId().isEmpty()) {
	    throw new Exception("The encode result message is null");
	}
	
	byte[] resultByte = pack.write(result);
	out.writeInt(resultByte.length);
	out.writeBytes(resultByte);
    }
}
 