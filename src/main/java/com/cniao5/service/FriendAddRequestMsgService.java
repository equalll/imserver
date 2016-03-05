package com.cniao5.service;


import com.cniao5.dao.FriendAddRequestMsgDao;
import com.cniao5.model.FriendAddRequestMsg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * FriendAddRequestMsgService层接口
 * Created by sony on 2015/12/14.
 */
@Service
public class FriendAddRequestMsgService {

    /**
     * 注入friendAddRequestMsgDao
     */
    @Resource
    private FriendAddRequestMsgDao friendAddRequestMsgDao;

    /**
     * 添加好友请求
     *
     * @param msg
     */
    public void createRequestMsg(FriendAddRequestMsg msg) {
        this.friendAddRequestMsgDao.save(msg);
    }

    /**
     * 更新好友请求
     *
     * @param msg
     */
    public void updateRequestMsg(FriendAddRequestMsg msg) {
        this.friendAddRequestMsgDao.update(msg);
    }

    /**
     * 获取好友请求信息
     *
     * @param id
     * @return
     */
    public FriendAddRequestMsg getRequestMsg(Long id) {
        return this.friendAddRequestMsgDao.get(id);
    }


}
