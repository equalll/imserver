package com.cniao5.model;

import java.io.Serializable;
import java.util.Objects;
/**
 * 信息
 *
 * @author sony
 */
public class Msg implements Serializable {

	private static final long serialVersionUID = 5457054359636770299L;




	private Integer push_type;

	private String tag;

	private String device_type;

	private String messages;

	private String msg_keys;



	public Integer getPush_type() {
		return push_type;
	}

	public void setPush_type(Integer push_type) {
		this.push_type = push_type;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public String getMsg_keys() {
		return msg_keys;
	}

	public void setMsg_keys(String msg_keys) {
		this.msg_keys = msg_keys;
	}
	
	
	
	

}
