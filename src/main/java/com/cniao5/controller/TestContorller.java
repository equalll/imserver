package com.cniao5.controller;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.rs.GetPolicy;
import com.qiniu.api.rs.URLUtils;
import org.apache.commons.codec.EncoderException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试七牛云存储的控制层
 */
@Controller
public class TestContorller {

    @RequestMapping("/lesson/url")
    @ResponseBody
    public String getUrl() {
        Config.ACCESS_KEY = "LHh7BtLRnk7PcmZdWs20nfVKFvGteUe9gMGU41tL";
        Config.SECRET_KEY = "k1_MeFlMRU8E5PkanUKXk4hPXwVpPvosnZQNY1AV";

        try {
            Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
            String baseUrl = URLUtils.makeBaseUrl("7xp7d0.com1.z0.glb.clouddn.com", "1.mp4");
            GetPolicy getPolicy = new GetPolicy();
            getPolicy.expires = 120;
            String downloadUrl = getPolicy.makeRequest(baseUrl, mac);

            System.out.println(downloadUrl);

            return downloadUrl;
        } catch (AuthException e) {
            e.printStackTrace();
        } catch (EncoderException e) {
            e.printStackTrace();
        }
        return "";
    }
}
