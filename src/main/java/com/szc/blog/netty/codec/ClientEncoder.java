package com.szc.blog.netty.codec; 

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import org.msgpack.MessagePack;

import com.szc.blog.netty.message.InvokeMessage;


/**
 * 对nettymessage编码,转成byte
 * @author	shizhengchao
 * @version      
 */
public class ClientEncoder extends MessageToByteEncoder<InvokeMessage> {
    
    private MessagePack pack;
    
    public ClientEncoder() {
	pack = new MessagePack();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, InvokeMessage msg, ByteBuf out) throws Exception {
	if (msg == null || msg.getRequestId() == null) {
	    throw new Exception("The encode result message is null");
	}
	
	byte[] bytes = pack.write(msg);
	out.writeInt(bytes.length);
	out.writeBytes(bytes);
    }
}
 