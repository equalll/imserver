package com.cniao5.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 公共控制层，创建gson对象
 * Created by sony on 2015/12/14.
 */
public class BaseController {


    protected Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .serializeNulls()
//            .setPrettyPrinting()
            .create();
}
