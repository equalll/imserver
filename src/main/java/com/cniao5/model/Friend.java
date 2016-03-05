package com.cniao5.model;

import java.util.Date;

/**
 * 好友
 */
public class Friend extends Member {

    private Date createtime;

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
