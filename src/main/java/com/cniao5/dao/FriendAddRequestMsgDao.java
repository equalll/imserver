package com.cniao5.dao;


import com.cniao5.daoImpl.HibernateEntityDao;
import com.cniao5.model.FriendAddRequestMsg;
import org.springframework.stereotype.Repository;


/**
 * 添加好友请求dao层接口
 * Created by sony on 2015/12/14.
 */
@Repository
public class FriendAddRequestMsgDao extends HibernateEntityDao<FriendAddRequestMsg,Long> {
}
