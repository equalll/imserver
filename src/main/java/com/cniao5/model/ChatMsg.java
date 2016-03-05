package com.cniao5.model;

/**
 * 聊天信息
 * 
 * @author sony
 * 
 */
public class ChatMsg {

	public static final int MSG_TYPE_TEXT = 1;
	public static final int MSG_TYPE_VOICE = 2;
	public static final int MSG_TYPE_VIDEO = 3;

	// 信息流水号
	private Long msgId;
	// 信息发送人id
	private Long fromMemberId;
	// 目的信息人id
	private Long toMemberId;
	// 信息类型
	private int msgType;
	// 信息内容
	private String msg;
	// 发送时间
	private Long chatTime;

	public Long getChatTime() {
		return chatTime;
	}

	public void setChatTime(Long chatTime) {
		this.chatTime = chatTime;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public Long getFromMemberId() {
		return fromMemberId;
	}

	public void setFromMemberId(Long fromMemberId) {
		this.fromMemberId = fromMemberId;
	}

	public Long getToMemberId() {
		return toMemberId;
	}

	public void setToMemberId(Long toMemberId) {
		this.toMemberId = toMemberId;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
