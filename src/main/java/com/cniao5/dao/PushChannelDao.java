package com.cniao5.dao;


import com.cniao5.daoImpl.HibernateEntityDao;
import com.cniao5.model.PushChannel;
import org.springframework.stereotype.Repository;


/**
 * 推送通道dao层接口
 * Created by sony on 2015/12/14.
 */
@Repository
public class PushChannelDao extends HibernateEntityDao<PushChannel, Long> {
}
