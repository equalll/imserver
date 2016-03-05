package com.cniao5.controller.msg;

import java.util.Date;

/**
 * 添加好友请求信息
 * Created by sony on 2015/12/14.
 */
public class FriendAddRespMsg {
    /**
     * 联系人id
     */
    private long contactid;
    /**
     * 请求id
     */
    private long requestid;
    /**
     * 请求信息
     */
    private String requestmsg;

    /**
     * 请求时间
     */
    private Date requesttime;
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
     * 联系人是否管理员
     */
    private String contactheadmid;
    /**
     * 联系人大头像
     */
    private String contactheadbig;


    public long getContactid() {
        return contactid;
    }

    public void setContactid(long contactid) {
        this.contactid = contactid;
    }

    public long getRequestid() {
        return requestid;
    }

    public void setRequestid(long requestid) {
        this.requestid = requestid;
    }

    public String getRequestmsg() {
        return requestmsg;
    }

    public void setRequestmsg(String requestmsg) {
        this.requestmsg = requestmsg;
    }

    public Date getRequesttime() {
        return requesttime;
    }

    public void setRequesttime(Date requesttime) {
        this.requesttime = requesttime;
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
