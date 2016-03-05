package com.cniao5.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
/**
 * 好友
 * @author sony
 *
 */
@Entity
@Table(name = "tb_friend_ship")
public class FriendShip {

	public static final String MEMBER_ID = "memberId";
	public static final String FRIEND_ID = "friendid";

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	// 会员id
	@Column(name = "memberid")
	private Long memberId;
	// 好友id
	@Column(name = "friendid")
	private Long friendid;
	// 创建时间
	@Column(name = "createtime")
	private Date creatTime;

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

	public Long getFriendid() {
		return friendid;
	}

	public void setFriendid(Long friendid) {
		this.friendid = friendid;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
}
