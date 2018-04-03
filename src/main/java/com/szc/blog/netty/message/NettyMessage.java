package com.szc.blog.netty.message;

/**
 * @author shizhengchao
 * @version
 */
public class NettyMessage {

    private Header header;

    private Object body;

    public Header getHeader() {
	return header;
    }

    public Object getBody() {
	return body;
    }

    public void setHeader(Header header) {
	this.header = header;
    }

    public void setBody(Object body) {
	this.body = body;
    }

    public String toString() {
	return "NettyMessage [Header: " + header + ", Body: " + body + " ]";
    }
}
