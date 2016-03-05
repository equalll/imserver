package com.cniao5.controller.msg;

/**
 * 公共响应信息
 * Created by sony on 2015/12/14.
 */
public class BaseRespMsg {

    public static final int FAIL = 0;
    public static final int SUCCESS = 1;
    public static final String SUCCESS_STR = "success";

    //状态
    private int status;
    //信息
    private String msg;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 响应成功的信息
     *
     * @return
     */
    public static BaseRespMsg newSuccessInstance() {

        BaseRespMsg msg = new BaseRespMsg();
        msg.setStatus(SUCCESS);
        msg.setMsg(SUCCESS_STR);

        return msg;
    }

    /**
     * 响应失败的信息
     *
     * @return
     */
    public static BaseRespMsg newFailInstance() {

        BaseRespMsg msg = new BaseRespMsg();
        msg.setStatus(FAIL);
        msg.setMsg("error");

        return msg;
    }
}
