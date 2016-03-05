package com.cniao5.model;

import java.io.Serializable;

/**
 * 响应成功或者失败的信息
 * @author sony
 *
 */
public class Response implements Serializable {

	private static final long serialVersionUID = -7407301879369336934L;

	public final static int SUCCESS = 1;
	public final static int ERROR = 0;

	private Integer code;

	private String msg;

	public Response(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
