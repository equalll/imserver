package com.cniao5.dao;


import com.cniao5.daoImpl.HibernateEntityDao;
import com.cniao5.model.Member;
import org.springframework.stereotype.Repository;


/**
 * 会员dao层接口
 * Created by sony on 2015/12/14.
 */
@Repository
public class MemberDao extends HibernateEntityDao<Member, Long> {


}
