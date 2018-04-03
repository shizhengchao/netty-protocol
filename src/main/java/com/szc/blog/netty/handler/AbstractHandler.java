package com.szc.blog.netty.handler; 

import com.szc.blog.netty.message.NettyMessage;

/**
 * @author	shizhengchao
 * @version      
 */
public abstract class AbstractHandler {

    
    public void handler(NettyMessage message) {
	String str = someMethod();
	System.out.println(str);
	System.out.println(message);
    }
    
    public abstract String someMethod();
}
 