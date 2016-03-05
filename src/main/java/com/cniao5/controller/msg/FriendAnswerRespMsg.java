package com.cniao5.controller.msg;

import java.util.Date;

/**
 * 回复好友响应信息
 * Created by sony on 2015/12/14.
 */
public class FriendAnswerRespMsg extends BaseRespMsg {

    //同意好友请求
    public static final int TYPE_AGREE = 2;
    //拒绝好友请求
    public static final int TYPE_REFUSE = 3;

    /**
     * 响应id
     */
    private Long requestid;
    /**
     * 联系人id
     */
    private long contactid;
    /**
     * 联系人邮箱
     */
    private String contactemail;
    /**
     * 联系人姓名
     */
    private String contactname;
    /**
     * 联系人小头像
     */
    private String contactheadsmall;
    /**
     * 联系人中头像
     */
    private String contactheadmid;
    /**
     * 联系人大头像
     */
    private String contactheadbig;
    /**
     * 回复信息
     */
    private String answermsg;
    /**
     * 回复时间
     */
    private Date answerTime;
    /**
     * 回复状态
     */
    private int type;


    public Long getRequestid() {
        return requestid;
    }

    public void setRequestid(Long requestid) {
        this.requestid = requestid;
    }

    public String getAnswermsg() {
        return answermsg;
    }

    public void setAnswermsg(String answermsg) {
        this.answermsg = answermsg;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public long getContactid() {
        return contactid;
    }

    public void setContactid(long contactid) {
        this.contactid = contactid;
    }

    public String getContactemail() {
        return contactemail;
    }

    public void setContactemail(String contactemail) {
        this.contactemail = contactemail;
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    public String getContactheadsmall() {
        return contactheadsmall;
    }

    public void setContactheadsmall(String contactheadsmall) {
        this.contactheadsmall = contactheadsmall;
    }

    public String getContactheadmid() {
        return contactheadmid;
    }

    public void setContactheadmid(String contactheadmid) {
        this.contactheadmid = contactheadmid;
    }

    public String getContactheadbig() {
        return contactheadbig;
    }

    public void setContactheadbig(String contactheadbig) {
        this.contactheadbig = contactheadbig;
    }
}
