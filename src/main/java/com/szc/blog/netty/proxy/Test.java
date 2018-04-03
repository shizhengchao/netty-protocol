package com.szc.blog.netty.proxy;

import com.szc.blog.service.ProductService;
import com.szc.blog.service.ProductServiceImpl;

/**
 * @author shizhengchao
 * @version
 */
public class Test {

    public static void main(String[] args) {
	//ProductService ps = new ProductServiceImpl();
	RPCProxy proxy = new RPCProxy(ProductServiceImpl.class);
	ProductService service = (ProductServiceImpl) proxy.getProxy();
	// 这里service经过绑定，就会进入invoke方法里面了。
	service.getProductName();
    }
}
