/*
*DemoFriend.java
*Created on 2014-7-25 下午2:52 by Ivan
*Copyright(c)2014 Guangzhou Onion Information Technology Co., Ltd.
*http://www.cniao5.com
*/
package com.cniao5.controller.msg;

/**
 * Created by Ivan on 14-7-25.
 * Copyright(c)2014 Guangzhou Onion Information Technology Co., Ltd.
 * http://www.cniao5.com
 */
public class DemoFriend {

    private  Long id;

    private String head;

    private String name;

    private String phone;


    public DemoFriend() {
    }

    public DemoFriend(Long id, String head, String name, String phone) {
        this.id = id;
        this.head = head;
        this.name = name;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
