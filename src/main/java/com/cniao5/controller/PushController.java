package com.cniao5.controller;

import com.cniao5.model.Notification;
import com.cniao5.model.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.FetchTagRequest;
import com.baidu.yun.channel.model.FetchTagResponse;
import com.baidu.yun.channel.model.PushBroadcastMessageRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageResponse;
import com.baidu.yun.channel.model.PushTagMessageRequest;
import com.baidu.yun.channel.model.PushTagMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.google.gson.Gson;

/**
 * 推送控制层
 * Created by sony on 2015/12/14.
 */
@Controller
public class PushController {
    //创建BaiduChannelClient对象
    private BaiduChannelClient channelClient;
    //创建gson对象
    private Gson gson = new Gson();

    public PushController() {
        ChannelKeyPair pair = new ChannelKeyPair("HhIopZ2V44iw2N1ZGCnBifFP",
                "8tGS1u1YezdLy15pkNwcRIRnqqAVtdOI");

        channelClient = new BaiduChannelClient(pair);

        channelClient.setChannelLogHandler(new YunLogHandler() {

            @Override
            public void onHandle(YunLogEvent event) {

                System.out.println("YunLogEvent:" + event.getMessage());

            }
        });
    }

    /**
     * 获取TAGS
     *
     * @return
     */
    @RequestMapping("/tags")
    public ModelAndView findTags() {

        FetchTagRequest request = new FetchTagRequest();

        FetchTagResponse response = null;
        try {

            response = channelClient.fetchTag(request);

        } catch (ChannelClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ModelAndView mv = new ModelAndView();

        mv.addObject("tags", response.getTags());

        return mv;

    }

    /**
     * 推送消息
     *
     * @param title       通知标题
     * @param description 通知内容
     * @param push_type   推送类型，取值范围为：1～3<br/>
     *                    1：单个人，必须指定user_id 和 channel_id （指定用户的指定设备）或者user_id（指定用户的所有设备）<br/>
     *                    2：一群人，必须指定 tag<br/>
     *                    3：所有人，无需指定tag、user_id、channel_id<br/>
     * @param tag
     * @param device_type 设备类型，取值范围为：1～5<br/>
     *                    云推送支持多种设备，各种设备的类型编号如下：<br/>
     *                    1：浏览器设备；<br/>
     *                    2：PC设备；<br/>
     *                    3：Andriod设备；<br/>
     *                    4：iOS设备；<br/>
     *                    5：Windows Phone设备；<br/>
     *                    如果存在此字段，则向指定的设备类型推送消息。 默认为android设备类型<br/>
     * @return
     */
    @RequestMapping("/sennotification")
    public
    @ResponseBody
    Response sendNotification(String title, String description, Integer push_type, String tag, Integer device_type) {
        if (push_type == 3) {
            return sendNotificationToAll(title, description, device_type);
        } else if (push_type == 2) {
            return sendNotificationToTag(title, description, tag, device_type);
        }

        return new Response(Response.ERROR, "");
    }

    private Response sendNotificationToTag(String title, String description, String tag, Integer device_type) {

        Notification not = new Notification();
        not.setTitle(title);
        not.setDescription(description);

        PushTagMessageRequest request = new PushTagMessageRequest();
        request.setDeviceType(device_type);
        request.setMessageType(1);
        request.setTagName(tag);
        request.setMessage(not.toJson(gson));

        try {

            PushTagMessageResponse response = channelClient.pushTagMessage(request);

            int amt = response.getSuccessAmount();
            if (amt > 0)
                return new Response(Response.SUCCESS, "success");
            else
                return new Response(Response.ERROR, "推送失败");

        } catch (ChannelClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new Response(Response.ERROR, e.getMessage());
        } catch (ChannelServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new Response(Response.ERROR, e.getMessage());
        }

    }


    private Response sendNotificationToAll(String title, String description, Integer device_type) {

        Notification not = new Notification();
        not.setTitle(title);
        not.setDescription(description);

        PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();

        // 1是通知，2是消息
        request.setMessageType(1);
        request.setDeviceType(device_type);
        request.setMessage(not.toJson(gson));

        try {
            PushBroadcastMessageResponse response = channelClient.pushBroadcastMessage(request);

            int amt = response.getSuccessAmount();
            if (amt > 0)
                return new Response(Response.SUCCESS, "success");
            else
                return new Response(Response.ERROR, "推送失败");
        } catch (ChannelClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            return new Response(Response.ERROR, e.getMessage());
        } catch (ChannelServerException e) {
            // TODO Auto-generated catch block
            return new Response(Response.ERROR, e.getMessage());
        }
    }
}
