package com.cniao5.model;

import java.io.Serializable;

/**
 * 客户端信息
 *
 * @param <T>
 * @author sony
 */
public class ClientMsg<T> implements Serializable {
    //表示信息为聊天
    public static final int MSG_TYPE_CHAT = 1;
    //信息类型为添加好友请求
    public static final int MSG_TYPE_ADDFIEND_REQUEST = 2;
    //信息类型为添加好友响应
    public static final int MSG_TYPE_ADDFIEND_ANSWER = 3;

    private Long id;
    // 信息类型
    private int msgType;
    // 信息实体
    private T data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
