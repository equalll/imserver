package com.cniao5.service;


import com.cniao5.dao.PushChannelDao;
import com.cniao5.model.PushChannel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * PushChannelService层接口
 * Created by sony on 2015/12/14.
 */
@Service
public class PushChannelService {

    // @Autowired与@Resource都可以用来装配bean，@Autowired属于spring，@resource属于j2ee
    @Resource
    private PushChannelDao pushChannelDao;

    /**
     * 保存推送通道
     *
     * @param pushChannel
     */
    public void createUser(PushChannel pushChannel) {
        this.pushChannelDao.save(pushChannel);
    }

    /**
     * 通过会员id获取推送通道
     *
     * @param memberid
     * @return
     */
    public PushChannel getPushChannelByMemberId(long memberid) {
        return this.pushChannelDao.findUniqueBy("memberId", memberid);
    }

    /**
     * 更新推送通道
     *
     * @param pushChannel
     */
    public void updatePushChannel(PushChannel pushChannel) {
        this.pushChannelDao.update(pushChannel);
    }

}
