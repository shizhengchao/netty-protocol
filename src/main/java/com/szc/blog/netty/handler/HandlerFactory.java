package com.szc.blog.netty.handler; 

import com.szc.blog.netty.message.NettyMessage;

/**
 * @author	shizhengchao
 * @version      
 */
public class HandlerFactory {
    
    private static HandlerFactory instance = new HandlerFactory();
    
    private HandlerFactory() {
	
    }
    
    public static HandlerFactory getInstance() {
	return instance;
    }
    
    /**
     * 构造handler，实际工作中Body可能会是一个json，然后根据json构造相应的handler
     * 这里只是演示
     * @param msg
     * @return
     *  
     * @author	hz17052992 
     * @date	2017年12月11日 下午2:54:18
     * @version
     */
    public AbstractHandler buildHandler(NettyMessage msg) {
	AbstractHandler handler = null;
	if("myhandler".equalsIgnoreCase((String)msg.getBody())) {
	    handler = new MyHandler();
	} else {
	    //do something
	}
	return handler;
    }
}
 