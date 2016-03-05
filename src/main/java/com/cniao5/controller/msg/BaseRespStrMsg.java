package com.cniao5.controller.msg;

/**
 * 用于保存将公共响应信息转换成JSON字符串的实体
 * Created by sony on 2015/12/14.
 */
public class BaseRespStrMsg extends BaseRespMsg {

    /**
     * 对象或者数组转换成的JSON字符串
     */
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
