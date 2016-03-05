package com.cniao5.controller.msg;


import com.cniao5.model.Friend;

import java.util.List;

/**
 * 好友响应信息，用于保存将信息转为json数据后的实体
 * Created by sony on 2015/12/14.
 */
public class FriendsRespMsg extends BaseRespMsg {

    private List<Friend> data;

    public List<Friend> getData() {
        return data;
    }

    public void setData(List<Friend> data) {
        this.data = data;
    }
}
