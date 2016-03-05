package com.cniao5.controller.msg;

/**
 * 用于保存将公共响应信息转换成JSON字符串的实体
 * Created by sony on 2015/12/14.
 */
public class BaseRespEntityMsg<T> extends BaseRespMsg {

    private T data;

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
