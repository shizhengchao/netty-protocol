package com.szc.blog.netty.message;

/**
 * @author shizhengchao
 * @version
 */
public class ResultMessage {

    private int code;

    private String requestId;

    private Object result;

    public int getCode() {
	return code;
    }

    public String getRequestId() {
	return requestId;
    }

    public Object getResult() {
	return result;
    }

    public void setCode(int code) {
	this.code = code;
    }

    public void setRequestId(String requestId) {
	this.requestId = requestId;
    }

    public void setResult(Object result) {
	this.result = result;
    }

}
