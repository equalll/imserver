package com.cniao5.service;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

/**
 * PushMsgService百度推送控制层接口
 * Created by sony on 2015/12/14.
 */
@Service
public class PushMsgService {

    //BaiduChannelClient该类提供了所有的面向用户使用的接口
    private BaiduChannelClient channelClient;

    private Gson gson = new Gson();

    public PushMsgService() {

        //ChannelKeyPair类用于接收分配给开发者app的apiKey 和 secretKey
        //ChannelKeyPair pair = new ChannelKeyPair("kcoY7KwZipqQSpghLjz4DQGz",
        //"gxOMglwcKu7FNxKnORIsURmncaf7Yzvr");

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
     * 通过推送的请求，获取响应
     *
     * @param request
     * @return
     * @throws ChannelClientException
     * @throws ChannelServerException
     */
    public PushUnicastMessageResponse pushUnicastMessage(
            PushUnicastMessageRequest request) throws ChannelClientException,
            ChannelServerException {

        return channelClient.pushUnicastMessage(request);
    }
}
