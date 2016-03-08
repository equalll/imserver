package com.cniao5.service;


import com.cniao5.dao.MemberDao;
import com.cniao5.model.Member;
import com.cniao5.util.RandomNameAndPhoneUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.Date;
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


    /**
     * 保存注册会员到数据库中
     *
     * @param email
     * @param pwd
     */
    public void addMember(String email, String pwd) {
        Member member = new Member();
        member.setEmail(email);
        member.setPassword(pwd);
        member.setName(RandomNameAndPhoneUtils.getChineseName());
        member.setAccountType(1);
        member.setAddress("山东");
        member.setCity(RandomNameAndPhoneUtils.getRoad());
        member.setName(RandomNameAndPhoneUtils.getChineseName());
        member.setCreator(RandomNameAndPhoneUtils.getChineseName());
        member.setCompany("google");
        member.setCurrentAccount(1L);
        member.setCurrentIntergration(1L);
        member.setFocusTechnology("计算机科学与技术");
        member.setGender("男");
        member.setMobile(RandomNameAndPhoneUtils.getTel());
        member.setModifier(RandomNameAndPhoneUtils.getChineseName());
        member.setRegisteTime(new Date());
        member.setStatus(1);
        member.setWorkYear(2);
        member.setHeadbig("http://192.168.189.84:8080/imserver/imgs/head/13.jpg");
        member.setHeadmid("http://192.168.189.84:8080/imserver/imgs/head/13.jpg");
        member.setHeadsmall("http://192.168.189.84:8080/imserver/imgs/head/13.jpg");
        member.setProvince("山东");

        System.out.println("------------------>保存的会员信息:" + member.toString());

        this.memberDao.save(member);
    }
}
