package com.cniao5.model;

import java.io.Serializable;

import com.google.gson.Gson;

/**
 * 通知
 * 
 * @author sony
 * 
 */
public class Notification implements Serializable {

	private static final long serialVersionUID = 973941743055393002L;
	// 通知标题
	private String title;
	// 通知内容
	private String description;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toJson(Gson gson) {

		return gson.toJson(this);

	}

}
