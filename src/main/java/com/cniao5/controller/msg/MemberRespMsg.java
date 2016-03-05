package com.cniao5.controller.msg;

/**
 * 会员响应信息
 * Created by sony on 2015/12/14.
 */
public class MemberRespMsg {

    /**
     * 会员id
     */
    private Long id;
    /**
     * 会员姓名
     */
    private String name;
    /**
     * 会员邮箱
     */
    private String email;
    /**
     * token信息
     */
    private String token;
    /**
     * 会员大头像
     */
    private String headbig;
    /**
     * 会员中头像
     */
    private String headmid;
    /**
     * 会员小头像
     */
    private String headsmall;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
}
