package com.cniao5.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 推送通道
 * 
 * @author sony
 * 
 */
@Entity
@Table(name = "tb_push_channel")
public class PushChannel {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// 会员id
	@Column(name = "memberid")
	private Long memberId;
	// 推送userid
	@Column(name = "userid")
	private String userId;
	// 推送通道id
	@Column(name = "channelid")
	private String channelId;
	// 修改时间
	@Column(name = "modifytime")
	private Date modifyTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}
