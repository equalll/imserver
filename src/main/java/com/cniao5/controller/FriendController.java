package com.cniao5.controller;

import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.cniao5.controller.msg.*;
import com.cniao5.model.*;
import com.cniao5.service.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 好友控制层
 * Created by sony on 2015/12/14.
 */
@Controller
@RequestMapping("/friend")
public class FriendController extends BaseController {

    /**
     * 注入pushMsgService
     */
    @Resource
    private PushMsgService pushMsgService;
    /**
     * 注入friendShipService
     */
    @Resource
    private FriendShipService friendShipService;
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
     * 注入friendAddRequestMsgService
     */
    @Resource
    private FriendAddRequestMsgService friendAddRequestMsgService;

    /**
     * 通过会员id获取好友
     *
     * @param memberid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/my", method = RequestMethod.POST)
    public BaseRespStrMsg findMemberFriends(Long memberid) {

        //通过会员id获取好友
        List<Friend> friends = friendShipService.findMemberFriend(memberid);

        //设置响应的信息
        BaseRespStrMsg msg = new BaseRespStrMsg();

        msg.setMsg(BaseRespMsg.SUCCESS_STR);
        msg.setStatus(BaseRespMsg.SUCCESS);

        msg.setData(gson.toJson(friends));

        System.out.println("----------->findMemberFriends通过登陆的会员id查找我的好友" + msg);

        return msg;

    }

    /**
     * 通过关键字查询会员列表
     *
     * @param word
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public BaseRespStrMsg query(String word) {

        //通过关键字查询会员列表
        List<Member> members = this.memberService.queryMembers(word);

        List<FriendQueryRespMsg> mrs = null;
        //该关键字查询出的好友存在
        if (members != null && members.size() > 0) {

            mrs = new ArrayList<FriendQueryRespMsg>(members.size());

            for (Member m : members) {
                FriendQueryRespMsg msg = new FriendQueryRespMsg();
                msg.setContactid(m.getId());
                msg.setEmail(m.getEmail());
                msg.setHeadbig(m.getHeadbig());
                msg.setHeadmid(m.getHeadmid());
                msg.setHeadsmall(m.getHeadsmall());
                msg.setName(m.getName());

                mrs.add(msg);
            }
        }

        //设置查询成功的响应信息
        BaseRespStrMsg msg = new BaseRespStrMsg();
        msg.setMsg(BaseRespMsg.SUCCESS_STR);
        msg.setStatus(BaseRespMsg.SUCCESS);

        msg.setData(gson.toJson(mrs));

        System.out.println("----------->query通过关键字查找存在的会员：" + msg.getData());
        return msg;
    }

    /**
     * 添加好友请求
     *
     * @param memberid
     * @param friendid
     * @param msg
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/request", method = RequestMethod.POST)
    public BaseRespMsg addFriendRequest(Long memberid, Long friendid, String msg) {
        //通过好友id获取会员信息
        Member friend = memberService.getMemberById(friendid);

        //通过friendid找不到要添加的好友
        if (friend == null) {

            //设置响应成功的信息
            BaseRespMsg respMsg = BaseRespMsg.newFailInstance();
            respMsg.setMsg("friend not exist who id is " + friendid);

            System.out.println("----------->addFriendRequest通过friendid查找的会员不存在：" + msg);
            return respMsg;
        }
        //通过好友id获取推送通道
        PushChannel pushChannel = pushChannelService.getPushChannelByMemberId(friendid);
        //通过friendid查找的会员存在后，获取该会员的推送通道
        if (null == pushChannel) {
            // 保存请求，等用户登录时再发送请求
            //do save
            //推送通道不存在，说明没有在线
            BaseRespMsg respMsg = BaseRespMsg.newSuccessInstance();
            respMsg.setMsg("friend unline");

            System.out.println("----------->addFriendRequest通过friendid查找的会员存在但是不在线：" + msg);
            return respMsg;

        }

        //通过friendid查找的会员存在并且在线
        //创建好友添加请求信息，保存到数据库中
        FriendAddRequestMsg addRequestMsg = new FriendAddRequestMsg();
        addRequestMsg.setFromMemberId(memberid);
        addRequestMsg.setToMemberId(friendid);
        addRequestMsg.setRequestMsg(msg);
        addRequestMsg.setRequestTime(new Date());
        // 先把状态设置为已发送，如果推送发送失败再改为失败
        addRequestMsg.setStatus(FriendAddRequestMsg.STATUS_SENDED);

        //添加好友请求
        friendAddRequestMsgService.createRequestMsg(addRequestMsg);

        // 相对被请求方而言，发送请求方就是朋友
        //通过会员id获取会员信息
        Member memberFrom = memberService.getMemberById(memberid);

        //设置当前会员的好友添加的具体信息
        FriendAddRespMsg requestMsg = new FriendAddRespMsg();

        //设置请求的信息
        requestMsg.setRequestid(addRequestMsg.getId());
        requestMsg.setContactid(memberid);
        requestMsg.setContactemail(memberFrom.getEmail());
        requestMsg.setContactname(memberFrom.getName());
        requestMsg.setContactheadbig(memberFrom.getHeadbig());
        requestMsg.setContactheadmid(memberFrom.getHeadmid());
        requestMsg.setContactheadsmall(memberFrom.getHeadsmall());
        requestMsg.setRequestmsg(msg);
        requestMsg.setRequesttime(new Date());

        //设置客户端响应的信息
        ClientMsg<FriendAddRespMsg> clientMsg = new ClientMsg<FriendAddRespMsg>();
        clientMsg.setMsgType(ClientMsg.MSG_TYPE_ADDFIEND_REQUEST);
        clientMsg.setData(requestMsg);

        //将信息转为json
        String pushMsg = gson.toJson(clientMsg);

        System.out.println("----------->addFriendRequest要推送到推送服务器的信息：" + msg);

        //将添加好友请求通过要添加好友的推送通道推送到推送服务器
        PushUnicastMessageRequest request = new PushUnicastMessageRequest();
        request.setUserId(pushChannel.getUserId());
        request.setChannelId(Long.parseLong(pushChannel.getChannelId()));
        request.setMessage(pushMsg);
        request.setDeviceType(3);// device_type => 1: web 2: pc 3:android  4:ios 5:wp
        request.setMessageType(0); //   0：消息（透传给应用的消息体）  1：通知（对应设备上的消息通知） 默认值为0。

        //设置响应的信息
        BaseRespStrMsg respMsg = new BaseRespStrMsg();
        //respMsg.setStatus(BaseRespMsg.SUCCESS);

        try {
            //获取请求响应
            PushUnicastMessageResponse response = this.pushMsgService.pushUnicastMessage(request);
            respMsg.setStatus(BaseRespMsg.SUCCESS);
            respMsg.setMsg(BaseRespMsg.SUCCESS_STR);

        } catch (Exception e) {
            e.printStackTrace();

            respMsg.setStatus(BaseRespMsg.FAIL);
            respMsg.setMsg(e.getMessage());

            addRequestMsg.setStatus(FriendAddRequestMsg.STATUS_SEND_FAIL);
            //更新添加好友的信息为失败
            friendAddRequestMsgService.updateRequestMsg(addRequestMsg);

        }

        System.out.println("----------->addFriendRequest要推送到推送服务器后服务器响应的信息：" + respMsg);
        return respMsg;
    }

    /**
     * 回复好友请求
     *
     * @param requestid
     * @param answer
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/request/answer", method = RequestMethod.POST)
    public BaseRespMsg answerRequest(Long requestid, int answer) {

        //获取好友请求信息
        FriendAddRequestMsg addRequestMsg = this.friendAddRequestMsgService.getRequestMsg(requestid);
        //好友请求不存在
        if (addRequestMsg == null) {
            BaseRespMsg msg = BaseRespMsg.newFailInstance();
            msg.setMsg("Request not exist whice id is " + requestid);

            System.out.println("----------->answerRequest通过requestid查找好友请求信息不存在：" + msg);
            return msg;
        }

        //好友添加请求信息存在，创建回复好友信息对象
        FriendAnswerRespMsg respMsg = new FriendAnswerRespMsg();
        //同意或者拒绝好友请求
        respMsg.setType(answer);
        respMsg.setAnswerTime(new Date());

        //同意好友请求
        if (answer == FriendAnswerRespMsg.TYPE_AGREE) {
            //通过会员id获取会员信息
            Member member = memberService.getMemberById(addRequestMsg.getToMemberId());

            respMsg.setRequestid(addRequestMsg.getId());
            respMsg.setContactid(member.getId());
            respMsg.setContactemail(member.getEmail());
            respMsg.setContactname(member.getName());
            respMsg.setContactheadbig(member.getHeadbig());
            respMsg.setContactheadmid(member.getHeadmid());
            respMsg.setContactheadsmall(member.getHeadsmall());

            // 保存朋友关系
            if (null != friendShipService.findFriendShipByMemeberIdAndFriendId(addRequestMsg.getFromMemberId(), addRequestMsg.getToMemberId())) {
                //创建friendShip对象
                FriendShip friendShip = new FriendShip();
                friendShip.setMemberId(addRequestMsg.getFromMemberId());
                friendShip.setFriendid(addRequestMsg.getToMemberId());
                friendShip.setCreatTime(new Date());
                friendShipService.createFriendShip(friendShip);
            }

            if (null != friendShipService.findFriendShipByMemeberIdAndFriendId(addRequestMsg.getToMemberId(), addRequestMsg.getFromMemberId())) {
                FriendShip friendShip2 = new FriendShip();
                friendShip2.setMemberId(addRequestMsg.getToMemberId());
                friendShip2.setFriendid(addRequestMsg.getFromMemberId());
                friendShip2.setCreatTime(new Date());
                friendShipService.createFriendShip(friendShip2);
            }
        }

        //客户端相应信息
        ClientMsg<FriendAnswerRespMsg> clientMsg = new ClientMsg<FriendAnswerRespMsg>();
        clientMsg.setMsgType(ClientMsg.MSG_TYPE_ADDFIEND_ANSWER);
        clientMsg.setData(respMsg);

        String msg = gson.toJson(clientMsg);

        System.out.printf(msg);

        //推动请求
        PushChannel pushChannel = pushChannelService.getPushChannelByMemberId(addRequestMsg.getFromMemberId());
        PushUnicastMessageRequest request = new PushUnicastMessageRequest();
        request.setUserId(pushChannel.getUserId());
        request.setChannelId(Long.parseLong(pushChannel.getChannelId()));
        request.setMessage(msg);
        request.setDeviceType(3);// device_type => 1: web 2: pc 3:android  4:ios 5:wp
        request.setMessageType(0); //   0：消息（透传给应用的消息体）  1：通知（对应设备上的消息通知） 默认值为0。


        BaseRespStrMsg baseRespStrMsg = new BaseRespStrMsg();
        baseRespStrMsg.setStatus(BaseRespMsg.SUCCESS);


        try {
            //获取响应
            PushUnicastMessageResponse response = this.pushMsgService.pushUnicastMessage(request);
            baseRespStrMsg.setStatus(BaseRespMsg.SUCCESS);
            baseRespStrMsg.setMsg(BaseRespMsg.SUCCESS_STR);

            addRequestMsg.setStatus(answer);
            friendAddRequestMsgService.updateRequestMsg(addRequestMsg);

        } catch (Exception e) {

            baseRespStrMsg.setStatus(BaseRespMsg.FAIL);
            baseRespStrMsg.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return baseRespStrMsg;
    }

    /**
     * 获取个人简介
     *
     * @param memberid
     * @param token
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public BaseRespStrMsg profile(Long memberid, String token) {

        //通过会员id获取会员信息
        Member member = this.memberService.getMemberById(memberid);

        if (member == null) {
            BaseRespStrMsg msg = new BaseRespStrMsg();
            msg.setStatus(BaseRespStrMsg.FAIL);
            msg.setMsg("friend not exist who id is " + memberid);

            return msg;

        }
        BaseRespStrMsg msg = new BaseRespStrMsg();
        msg.setStatus(BaseRespStrMsg.SUCCESS);
        msg.setMsg(BaseRespStrMsg.SUCCESS_STR);

        //创建个人简介对象
        Profile profile = new Profile();
        profile.setMemberId(member.getId());
        profile.setEmail(member.getEmail());
        profile.setName(member.getName());
        profile.setAge(0);
        profile.setProvince(member.getProvince());
        profile.setCity(member.getCity());
        profile.setCompany(member.getCompany());

        //将返回的个人简介转为json、
        String data = gson.toJson(profile);
        System.out.println("data:" + data);
        msg.setData(data);

        return msg;
    }
}
