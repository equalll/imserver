package com.cniao5.controller;

import com.cniao5.controller.msg.BaseRespMsg;
import com.cniao5.controller.msg.BaseRespStrMsg;
import com.cniao5.controller.msg.DemoFriend;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试转json数据的控制层
 * Created by sony on 2015/12/14.
 */
@Controller
@RequestMapping("/test")
public class DemoController extends BaseController {


    private String[] mFriendNames = {"Ivan", "Sunny", "Sarah", "Joyce", "Joe", "Kevin", "Sissi", "Robin", "Eric"};

    private String rootURL = "http://192.168.1.105:8080/pushserver/imgs/head/";


    @ResponseBody
    @RequestMapping("/list")
    public BaseRespMsg getFriends() {


        List<DemoFriend> friends = new ArrayList<DemoFriend>();

        friends.add(new DemoFriend(1l, rootURL + "4.jpg", "Ivan", "138001380001"));
        friends.add(new DemoFriend(2l, rootURL + "6.jpg", "Joe", "138001380001"));
        friends.add(new DemoFriend(3l, rootURL + "7.jpg", "Joyce", "138001380001"));
        friends.add(new DemoFriend(4l, rootURL + "8.jpg", "Sissi", "138001380001"));
        friends.add(new DemoFriend(5l, rootURL + "9.jpg", "Robin", "138001380001"));
        friends.add(new DemoFriend(6l, rootURL + "10.jpg", "Eric", "138001380001"));
        friends.add(new DemoFriend(6l, rootURL + "14.jpg", "Kevin", "138001380001"));
        friends.add(new DemoFriend(6l, rootURL + "15.jpg", "Sarah", "138001380001"));
        friends.add(new DemoFriend(6l, rootURL + "16.jpg", "Sun", "138001380001"));
        friends.add(new DemoFriend(6l, rootURL + "17.jpg", "sunny", "138001380001"));

        BaseRespStrMsg msg = new BaseRespStrMsg();
        msg.setMsg(BaseRespMsg.SUCCESS_STR);
        msg.setStatus(BaseRespMsg.SUCCESS);

        msg.setData(gson.toJson(friends));
        return msg;
    }


    @ResponseBody
    @RequestMapping("/single")
    public BaseRespMsg single() {

        DemoFriend friend = new DemoFriend(1l, rootURL + "4.jpg", "Ivan", "138001380001");

        BaseRespStrMsg msg = new BaseRespStrMsg();
        msg.setMsg(BaseRespMsg.SUCCESS_STR);
        msg.setStatus(BaseRespMsg.SUCCESS);

        msg.setData(gson.toJson(friend));

        return msg;
    }
}
