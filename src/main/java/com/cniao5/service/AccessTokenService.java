package com.cniao5.service;


import com.cniao5.dao.AccessTokenDao;
import com.cniao5.model.AccessToken;
import com.cniao5.util.RandomStrUtil;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.Date;

/**
 * AccessTokenService层接口
 * Created by sony on 2015/12/14.
 */
@Service
public class AccessTokenService {

    /**
     * 注入accessTokenDao
     */
    @Resource
    private AccessTokenDao accessTokenDao;

    /**
     * 获取token
     *
     * @param id
     * @return
     */
    public AccessToken getAccessToken(Long id) {
        return this.accessTokenDao.get(id);
    }

    /**
     * 获取唯一token通过会员id
     *
     * @param memberid
     * @return
     */
    public AccessToken getAccessTokenByMember(Long memberid) {
        return this.accessTokenDao.findUniqueBy(AccessToken.MEMBER_ID, memberid);
    }

    /**
     * 创建token
     *
     * @param token
     */
    public void createAccessToken(AccessToken token) {
        this.accessTokenDao.save(token);
    }

    /**
     * 更新token
     *
     * @param memberid
     * @return
     */
    public String updateToken(Long memberid) {

        // 生成随机字符串
        String token = RandomStrUtil.randomStr(32);
        // 通过会员id获取token
        AccessToken accessToken = this.getAccessTokenByMember(memberid);

        if (accessToken != null) {
            // 存在token时，更新token
            accessToken.setToken(token);
            accessToken.setUpdateTime(new Date());
            this.accessTokenDao.update(accessToken);
        } else {
            // 不存在token时，新建一个token，并更新相关字段信息
            accessToken = new AccessToken();
            accessToken.setMemberId(memberid);
            accessToken.setToken(token);
            accessToken.setUpdateTime(new Date());

            createAccessToken(accessToken);
        }

        return token;
    }

}
