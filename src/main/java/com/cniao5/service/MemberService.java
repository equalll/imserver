package com.cniao5.service;


import com.cniao5.dao.MemberDao;
import com.cniao5.model.Member;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * MemberService层接口
 * Created by sony on 2015/12/14.
 */
@Service
public class MemberService {

    /**
     * 注入memberDao
     */
    @Resource
    private MemberDao memberDao;

    /**
     * 通过email获取唯一的会员信息
     *
     * @param email
     * @return
     */
    public Member getMemberByEmail(String email) {
        return this.memberDao.findUniqueBy(Member.EMAIL, email);
    }

    /**
     * 通过id获取会员信息
     *
     * @param id
     * @return
     */
    public Member getMemberById(Long id) {
        return this.memberDao.get(id);
    }

    /**
     * 通过邮箱和密码获取会员信息
     *
     * @param email
     * @param pwd
     * @return
     */
    public Member getMemberByEmailAndPwd(String email, String pwd) {
        Object obj = this.memberDao.createCriteria(
                Restrictions.eq(Member.EMAIL, email),
                Restrictions.eq(Member.PASSWORD, pwd)).uniqueResult();

        return obj == null ? null : (Member) obj;
    }

    /**
     * 通过关键字查询会员列表
     *
     * @param word
     * @return
     */
    public List<Member> queryMembers(String word) {

        Criteria criteria = this.memberDao.createCriteria();
        criteria.add(Restrictions.or(
                Restrictions.like(Member.EMAIL, word, MatchMode.ANYWHERE),
                Restrictions.like(Member.NAME, word, MatchMode.ANYWHERE)));

        criteria.setMaxResults(20);

        return criteria.list();
    }
}
