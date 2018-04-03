package com.szc.blog.netty.message;

/**
 * @author shizhengchao
 * @version
 */
public class InvokeMessage {

    private String className;

    private String method;

    private Class<?> types;

    private Object[] args;

    private String requestId;

    private long timeout;

    public String getClassName() {
	return className;
    }

    public String getMethod() {
	return method;
    }

    public Object[] getArgs() {
	return args;
    }

    public String getRequestId() {
	return requestId;
    }

    public long getTimeout() {
	return timeout;
    }

    public void setClassName(String className) {
	this.className = className;
    }

    public void setMethod(String method) {
	this.method = method;
    }

    public void setArgs(Object[] args) {
	this.args = args;
    }

    public Class<?> getTypes() {
	return types;
    }

    public void setTypes(Class<?> types) {
	this.types = types;
    }

    public void setRequestId(String requestId) {
	this.requestId = requestId;
    }

    public void setTimeout(long timeout) {
	this.timeout = timeout;
    }
}
