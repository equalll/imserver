package com.cniao5.dao;


import com.cniao5.daoImpl.HibernateEntityDao;
import com.cniao5.model.AccessToken;
import org.springframework.stereotype.Repository;

/**
 * 访问令牌dao层接口
 * Created by sony on 2015/12/14.
 */
@Repository
public class AccessTokenDao extends HibernateEntityDao<AccessToken, Long> {
}
