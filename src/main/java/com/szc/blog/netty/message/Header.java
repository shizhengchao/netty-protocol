package com.szc.blog.netty.message;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shizhengchao
 * @version
 */
public class Header {

    /**
     * netty消息的较验码固定值,一个字节等于8位二进制数
     */
    private int crcCode = 0xabef0101;

    /**
     * 消息长度,包括消息头和消息体
     */
    private int length;

    /**
     * 会话id,集群节点内全局唯一
     */
    private long sessionID;

    private byte type;// 消息类型

    private byte priority;// 消息优先级

    private Map<String, String> attachment = new HashMap<String, String>(); // 附件

    public int getCrcCode() {
	return crcCode;
    }

    public int getLength() {
	return length;
    }

    public long getSessionID() {
	return sessionID;
    }

    public byte getType() {
	return type;
    }

    public byte getPriority() {
	return priority;
    }

    public Map<String, String> getAttachment() {
	return attachment;
    }

    public void setCrcCode(int crcCode) {
	this.crcCode = crcCode;
    }

    public void setLength(int length) {
	this.length = length;
    }

    public void setSessionID(long sessionID) {
	this.sessionID = sessionID;
    }

    public void setType(byte type) {
	this.type = type;
    }

    public void setPriority(byte priority) {
	this.priority = priority;
    }

    public void setAttachment(Map<String, String> attachment) {
	this.attachment = attachment;
    }

    @Override
    public String toString() {
	return "Header [crcCode=" + crcCode + ", length=" + length + ", sessionID=" + sessionID + ", type=" + type + ", priority=" + priority + ", attachment=" + attachment + "]";
    }
}
