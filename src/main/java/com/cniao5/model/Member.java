package com.cniao5.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 会员信息
 *
 * @author sony
 */
@Entity
@Table(name = "tb_member")
public class Member {
    //管理员
    public final static int ACCOUNT_ADMIN = 2;
    //会员
    public final static int ACCOUNT_MEMBER = 1;
    //教师
    public final static int ACCOUNT_TEACHER = 3;

    //账号状态
    public final static int STATUS_NORMAL = 1;

    public final static String ID = "id";
    //邮箱
    public final static String EMAIL = "email";
    //密码
    public final static String PASSWORD = "password";
    //姓名
    public final static String NAME = "name";
    //分类
    public final static String ACCOUNT_TYPE = "accountType";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // 会员名
    @Column(name = "name")
    private String name;

    // 拼音索引
    @Column(name = "pinyin")
    private String pinyin;

    // 会员邮箱
    @Column(name = "email")
    private String email;

    // 会员密码
    @Column(name = "password")
    private String password;
    // 会员性别
    @Column(name = "gender")
    private String gender;
    // 会员省份
    @Column(name = "province")
    private String province;
    // 会员城市
    @Column(name = "city")
    private String city;
    // 会员地址
    @Column(name = "address")
    private String address;
    // 会员注册时间
    @Column(name = "registe_time")
    private Date registeTime;
    //
    @Column(name = "introducer_id")
    private Long introducerId;
    // 会员账号状态
    @Column(name = "status")
    private Integer status; // 0 删除 1 有效 2 未激活 3冻结
    // 会员信息添加人
    @Column(name = "creator")
    private String creator;
    // 会员信息修改人
    @Column(name = "modifier")
    private String modifier;
    // 会员信息修改时间
    @Column(name = "modified_time")
    private Date modifiedTime;

    @Column(name = "current_intergration")
    private Long currentIntergration;

    @Column(name = "current_account")
    private Long currentAccount;
    // 会员当前班级
    @Column(name = "current_grade")
    private Long currentGrade;
    // 会员手机号
    @Column(name = "mobile")
    private String mobile;
    // 会员qq
    @Column(name = "qq")
    private String qq;
    // 会员专业技术
    @Column(name = "focus_technology")
    private String focusTechnology;
    // 会员感兴趣
    @Column(name = "interest")
    private String interest;
    // 会员毕业与否
    @Column(name = "graduated")
    private String graduated;

    @Column(name = "gegree")
    private String gegree;
    // 会员专业
    @Column(name = "profession")
    private String profession;
    // 会员公司
    @Column(name = "company")
    private String company;

    @Column(name = "post")
    private String post;
    // 会员工作年份
    @Column(name = "work_year")
    private Integer workYear;
    // 会员自我介绍
    @Column(name = "intro_self")
    private String introSelf;
    // 会员账号类型
    @Column(name = "accounttype")
    private Integer accountType;// 账号类型：1是会员，2是管理员，3是讲师，4是企业机构
    // 会员大头像
    @Column(name = "headbig")
    private String headbig;
    // 会员中头像
    @Column(name = "headmid")
    private String headmid;
    // 会员小头像
    @Column(name = "headsmall")
    private String headsmall;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRegisteTime() {
        return registeTime;
    }

    public void setRegisteTime(Date registeTime) {
        this.registeTime = registeTime;
    }

    public Long getIntroducerId() {
        return introducerId;
    }

    public void setIntroducerId(Long introducerId) {
        this.introducerId = introducerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Long getCurrentIntergration() {
        return currentIntergration;
    }

    public void setCurrentIntergration(Long currentIntergration) {
        this.currentIntergration = currentIntergration;
    }

    public Long getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(Long currentAccount) {
        this.currentAccount = currentAccount;
    }

    public Long getCurrentGrade() {
        return currentGrade;
    }

    public void setCurrentGrade(Long currentGrade) {
        this.currentGrade = currentGrade;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getFocusTechnology() {
        return focusTechnology;
    }

    public void setFocusTechnology(String focusTechnology) {
        this.focusTechnology = focusTechnology;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getGraduated() {
        return graduated;
    }

    public void setGraduated(String graduated) {
        this.graduated = graduated;
    }

    public String getGegree() {
        return gegree;
    }

    public void setGegree(String gegree) {
        this.gegree = gegree;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Integer getWorkYear() {
        return workYear;
    }

    public void setWorkYear(Integer workYear) {
        this.workYear = workYear;
    }

    public String getIntroSelf() {
        return introSelf;
    }

    public void setIntroSelf(String introSelf) {
        this.introSelf = introSelf;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getHeadbig() {
        return headbig;
    }

    public void setHeadbig(String headbig) {
        this.headbig = headbig;
    }

    public String getHeadmid() {
        return headmid;
    }

    public void setHeadmid(String headmid) {
        this.headmid = headmid;
    }

    public String getHeadsmall() {
        return headsmall;
    }

    public void setHeadsmall(String headsmall) {
        this.headsmall = headsmall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Member member = (Member) o;

        if (id != member.id)
            return false;
        if (!name.equals(member.name))
            return false;

        return true;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }


}
