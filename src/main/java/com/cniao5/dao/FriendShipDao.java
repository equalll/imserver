package com.cniao5.dao;


import com.cniao5.daoImpl.HibernateEntityDao;
import com.cniao5.model.FriendShip;
import org.springframework.stereotype.Repository;

/**
 * 好友关系dao层接口
 * Created by sony on 2015/12/14.
 */
@Repository
public class FriendShipDao extends HibernateEntityDao<FriendShip,Long> {
}
