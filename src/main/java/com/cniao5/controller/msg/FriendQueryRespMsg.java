package com.cniao5.controller.msg;

/**
 * 查询好友响应信息
 * Created by sony on 2015/12/14.
 */
public class FriendQueryRespMsg {
    /**
     * 联系人id
     */
    private Long contactid;
    /**
     * 联系人姓名
     */
    private String name;
    /**
     * 联系人邮箱
     */
    private String email;
    /**
     * 联系人大头像
     */
    private String headbig;
    /**
     * 联系人中头像
     */
    private String headmid;
    /**
     * 联系人小头像
     */
    private String headsmall;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadbig() {
        return headbig;
    }

    public void setHeadbig(String headbig) {
        this.headbig = headbig;
    }

    public String getHeadmid() {
        return headmid;
    }

    public void setHeadmid(String headmid) {
        this.headmid = headmid;
    }

    public String getHeadsmall() {
        return headsmall;
    }

    public void setHeadsmall(String headsmall) {
        this.headsmall = headsmall;
    }

    public Long getContactid() {
        return contactid;
    }

    public void setContactid(Long contactid) {
        this.contactid = contactid;
    }
}
