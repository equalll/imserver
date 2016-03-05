package com.cniao5.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 好友请求以及请求消息
 *
 * @author sony
 */
@Entity
@Table(name = "tb_friend_add_request_msg")
public class FriendAddRequestMsg {

    //请求失败
    public static final int STATUS_SEND_FAIL = -1;
    //请求未发送
    public static final int STATUS_UNSEND = 0;
    //请求已发送
    public static final int STATUS_SENDED = 1;
    //同意请求
    public static final int STATUS_AGRER = 2;
    //拒绝请求
    public static final int STATUS_REFUSE = 3;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // 消息发送人id
    @Column(name = "frommemberid")
    private Long fromMemberId;
    // 目标好友消息id
    @Column(name = "tomemberid")
    private Long toMemberId;
    // 请求时间
    @Column(name = "requesttime")
    private Date requestTime;
    // 请求消息
    @Column(name = "requestmsg")
    private String requestMsg;
    // 状态码
    @Column(name = "status")
    private int status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getRequestMsg() {
        return requestMsg;
    }

    public void setRequestMsg(String requestMsg) {
        this.requestMsg = requestMsg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
