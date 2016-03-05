package com.cniao5.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 访问时返回的token
 *
 * @author sony
 */
@Table(name = "tb_access_token")
@Entity
public class AccessToken {

    public static final String MEMBER_ID = "memberId";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 会员id
     */
    @Column(name = "memberid")
    private Long memberId;
    /**
     * token 信息
     */
    @Column(name = "token")
    private String token;
    /**
     * 更新时间
     */
    @Column(name = "updatetime")
    private Date updateTime;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
