package com.cniao5.controller;

import com.cniao5.cache.UserTokenCache;
import com.cniao5.controller.msg.BaseRespMsg;
import com.cniao5.controller.msg.BaseRespStrMsg;
import com.cniao5.controller.msg.MemberRespMsg;
import com.cniao5.model.AccessToken;
import com.cniao5.model.Member;
import com.cniao5.model.PushChannel;
import com.cniao5.service.AccessTokenService;
import com.cniao5.service.MemberService;
import com.cniao5.service.PushChannelService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 登陆控制层
 * Created by sony on 2015/12/14.
 */
@Controller
public class LoginController extends BaseController {

    /**
     * 注入memberService
     */
    @Resource
    private MemberService memberService;

    /**
     * 注入pushChannelService
     */
    @Resource
    private PushChannelService pushChannelService;

    /**
     * 注入accessTokenService
     */
    @Resource
    private AccessTokenService accessTokenService;

    /**
     * 登陆
     *
     * @param email
     * @param pwd
     * @param userId
     * @param channelid
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public BaseRespMsg login(String email, String pwd, String userId, String channelid) {
        //邮箱或者密码为空
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(pwd) ||
                StringUtils.isEmpty(userId) || StringUtils.isEmpty(channelid)) {

            BaseRespStrMsg msg = new BaseRespStrMsg();

            msg.setStatus(BaseRespMsg.FAIL);
            msg.setMsg("email or passWord is missing,please input them");

            System.out.println("----------->login邮箱或者密码为空:" + msg);
            return msg;
        }

        //通过邮箱和密码获取会员信息
        Member member = this.memberService.getMemberByEmailAndPwd(email, pwd);

        BaseRespStrMsg msg = new BaseRespStrMsg();
        if (null == member) {
            msg.setStatus(BaseRespMsg.FAIL);
            msg.setMsg("this member is not exist,please confirm them");

            System.out.println("----------->login通过会员id没有找到该用户信息:" + msg);
            return msg;
        }
        //获取更新后的token
        String token = updateToken(member.getId());
        //保存推送通道
        savePushChannel(member.getId(), userId, channelid);
        //创建响应信息
        msg = builderResponseMsg(member, token);

        System.out.println("----------->login通过邮箱和密码登陆成功：" + msg);
        return msg;
    }


    /**
     * 通过token进行登陆
     *
     * @param email
     * @param token
     * @param userId
     * @param channelid
     * @return
     */
    @RequestMapping(value = "/login/token", method = RequestMethod.POST)
    @ResponseBody
    public BaseRespMsg loginWithToken(String email, String token, String userId, String channelid) {
        //邮箱或者密码为空
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(token) ||
                StringUtils.isEmpty(userId) || StringUtils.isEmpty(channelid)) {

            BaseRespStrMsg msg = new BaseRespStrMsg();

            msg.setStatus(BaseRespMsg.FAIL);
            msg.setMsg("email or passWord is missing ,please input them");

            System.out.println("----------->通过token login邮箱或者密码为空:" + msg);
            return msg;
        }

        //通过邮箱获取会员信息
        Member member = this.memberService.getMemberByEmail(email);

        BaseRespStrMsg msg = new BaseRespStrMsg();

        if (null == member) {
            msg.setStatus(BaseRespMsg.FAIL);
            msg.setMsg("member not exsit");

            System.out.println("----------->通过token login通过会员id没有找到该用户信息:" + msg);
            return msg;
        }

        //通过会员id从数据库获取token
        AccessToken accessToken = accessTokenService.getAccessTokenByMember(member.getId());
        //token不存在
        if (null == accessToken) {
            msg.setStatus(BaseRespMsg.FAIL);
            msg.setMsg("old token not exsit");

            System.out.println("----------->通过token login通过会员id没有找到该用户token信息:" + msg);
            return msg;
        }
        //token不匹配
        if (!token.equals(accessToken.getToken())) {
            msg.setStatus(BaseRespMsg.FAIL);
            msg.setMsg(" tokens not equal");

            System.out.println("----------->通过token login通过会员id找到该用户token信息与新的token不匹配:" + msg);
            return msg;
        }

        //获取更新后的token
        token = updateToken(member.getId());
        //保存推送通道
        savePushChannel(member.getId(), userId, channelid);
        //创建响应信息
        msg = builderResponseMsg(member, token);

        System.out.println("----------->通过token login成功:" + msg);
        return msg;
    }

    /**
     * 创建登陆成功信息
     *
     * @param member
     * @param token
     * @return
     */
    private BaseRespStrMsg builderResponseMsg(Member member, String token) {
        BaseRespStrMsg msg = new BaseRespStrMsg();
        //登陆成功 success
        msg.setMsg(BaseRespMsg.SUCCESS_STR);
        //状态成功 1
        msg.setStatus(BaseRespMsg.SUCCESS);

        //登陆成功以后获取的信息就是会员的信息
        MemberRespMsg loginRespMsg = new MemberRespMsg();
        loginRespMsg.setId(member.getId());
        loginRespMsg.setName(member.getName());
        loginRespMsg.setEmail(member.getEmail());
        loginRespMsg.setToken(token);
        loginRespMsg.setHeadbig(member.getHeadbig());
        loginRespMsg.setHeadmid(member.getHeadmid());
        loginRespMsg.setHeadsmall(member.getHeadsmall());

        //将信息转为json
        msg.setData(gson.toJson(loginRespMsg));

        return msg;
    }

    /**
     * 保存推送通道
     *
     * @param memberid
     * @param userId
     * @param channelid
     */
    private void savePushChannel(Long memberid, String userId, String channelid) {
        //通过会员id获取推送通道
        PushChannel pushChannel = this.pushChannelService.getPushChannelByMemberId(memberid);
        if (pushChannel == null) {
            //没有推送的通道
            pushChannel = new PushChannel();
            pushChannel.setChannelId(channelid);
            pushChannel.setMemberId(memberid);
            pushChannel.setModifyTime(new Date());
            pushChannel.setUserId(userId);
            //保存新的推送通道
            this.pushChannelService.createUser(pushChannel);
        } else {
            //获取到了数据库中保存的推送通道
            pushChannel.setChannelId(channelid);
            pushChannel.setModifyTime(new Date());
            pushChannel.setUserId(userId);
            //更新推送通道
            this.pushChannelService.updatePushChannel(pushChannel);
        }
    }

    /**
     * 更新token信息
     *
     * @param memberid
     * @return
     */
    private String updateToken(Long memberid) {
        //更新token
        String token = accessTokenService.updateToken(memberid);
        //将登陆的token信息放在缓存中
        UserTokenCache.put(token, memberid);
        return token;
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public BaseRespMsg register(String email, String pwd) {
        //邮箱或者密码为空
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(pwd)) {

            BaseRespStrMsg msg = new BaseRespStrMsg();

            msg.setStatus(BaseRespMsg.FAIL);
            msg.setMsg("请输入邮箱和密码");

            System.out.println("----------->login邮箱或者密码为空:" + msg);
            return msg;
        }

        //通过邮箱和密码获取会员信息
        Member member = this.memberService.getMemberByEmailAndPwd(email, pwd);

        BaseRespStrMsg msg = new BaseRespStrMsg();
        if (null != member) {
            msg.setStatus(BaseRespMsg.FAIL);
            msg.setMsg("该会员已注册，请直接登陆...");

            System.out.println("----------->login通过会员已经注册:" + msg);
            return msg;
        }

        //保存信息到数据库中
        memberService.addMember(email, pwd);

        //创建响应信息
        msg.setMsg("注册成功");
        msg.setStatus(1);
        msg.setData("注册成功");

        System.out.println("-------------->" + msg);

        return msg;
    }
}
