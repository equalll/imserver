package com.cniao5.controller;

import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.cniao5.controller.msg.BaseRespMsg;
import com.cniao5.controller.msg.BaseRespStrMsg;
import com.cniao5.model.ChatMsg;
import com.cniao5.model.ClientMsg;
import com.cniao5.model.PushChannel;
import com.cniao5.service.PushChannelService;
import com.cniao5.service.PushMsgService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


/**
 * 聊天控制层
 * Created by sony on 2015/12/14.
 */
@Controller
@RequestMapping("/chart")
public class ChartController extends BaseController {

    /**
     * 注入pushMsgService
     *
     */
    @Resource
    private PushMsgService pushMsgService;

    /**
     * 注入pushChannelService
     */
    @Resource
    private PushChannelService pushChannelService;

    /**
     * 向好友发送消息
     * @param memberId
     * @param friendId
     * @param msg
     * @return
     * @ResponseBody用于返回JSON串
     */
    @ResponseBody
    @RequestMapping(value = "/friend/send")
    public BaseRespStrMsg sendMsgToFriend(Long memberId, Long friendId, String msg) {
        //发送信息失败
        if (msg == null) {
            BaseRespStrMsg respMsg = new BaseRespStrMsg();
            respMsg.setStatus(BaseRespMsg.FAIL);
            respMsg.setMsg("msg is null");

            return respMsg;
        }

        //通过会员id获取推送通道
        PushChannel pushChannel = pushChannelService.getPushChannelByMemberId(friendId);

        //获取推送通道失败
        if (pushChannel == null) {
            BaseRespStrMsg respMsg = new BaseRespStrMsg();
            respMsg.setStatus(BaseRespMsg.FAIL);
            respMsg.setMsg("can't find the friend who id is " + friendId);

            return respMsg;
        }

        //创建聊天信息
        ChatMsg chatMsg = new ChatMsg();
        chatMsg.setChatTime(System.currentTimeMillis());
        chatMsg.setFromMemberId(memberId);
        chatMsg.setToMemberId(friendId);
        chatMsg.setMsg(msg);
        chatMsg.setMsgType(ChatMsg.MSG_TYPE_TEXT);

        //创建客户端信息
        ClientMsg<ChatMsg> clientMsg = new ClientMsg<ChatMsg>();
        clientMsg.setMsgType(ClientMsg.MSG_TYPE_CHAT);
        clientMsg.setData(chatMsg);

        //将信息转为json

        msg = gson.toJson(clientMsg);

        //推送请求
        PushUnicastMessageRequest request = new PushUnicastMessageRequest();
        request.setUserId(pushChannel.getUserId());
        request.setChannelId(Long.parseLong(pushChannel.getChannelId()));
        request.setMessage(msg);

        //请求设备为android
        request.setDeviceType(3);// device_type => 1: web 2: pc 3:android  4:ios 5:wp
        /**
         * 消息类型
         0：消息（透传给应用的消息体）
         1：通知（对应设备上的消息通知）
         默认值为0。
         */
        request.setMessageType(0);

        //创建BaseRespStrMsg对象
        BaseRespStrMsg respMsg = new BaseRespStrMsg();
        respMsg.setStatus(BaseRespMsg.SUCCESS);

        try {
            //获取推送请求的响应
            PushUnicastMessageResponse response = this.pushMsgService.pushUnicastMessage(request);

            respMsg.setStatus(BaseRespMsg.SUCCESS);
            respMsg.setMsg(BaseRespMsg.SUCCESS_STR);
            respMsg.setData(msg);
        } catch (ChannelClientException e) {
            respMsg.setStatus(BaseRespMsg.FAIL);
            respMsg.setMsg(e.getMessage());
            e.printStackTrace();
        } catch (ChannelServerException e) {
            respMsg.setStatus(BaseRespMsg.FAIL);
            respMsg.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return respMsg;
    }
}
