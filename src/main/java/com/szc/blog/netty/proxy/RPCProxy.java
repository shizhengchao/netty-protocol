package com.szc.blog.netty.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author shizhengchao
 * @version
 */
public class RPCProxy implements InvocationHandler {

    private Object target;
    
    public RPCProxy(Object target) {
	this.target = target;
    }

    /**
     * 绑定委托对象并返回一个【代理占位】
     * 
     * @param target 真实对象
     * @return 代理对象【占位】
     */
    public Object getProxy() {
	// 取得代理对象
	return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	Object result = null;
	// 反射方法前调用
	System.out.println("start proxy: " + method.getName() + "(" + args + ")");
	// 反射执行方法 相当于调用target.sayHelllo;
	result = method.invoke(target, args);
	System.out.println("end proxy, result is: " + result);
	return result;
    }
}
