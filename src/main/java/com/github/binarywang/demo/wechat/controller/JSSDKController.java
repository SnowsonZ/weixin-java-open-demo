package com.github.binarywang.demo.wechat.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;

/**
 * @Author: Snowson
 * @Date: 2018/6/12 17:19
 * @Description:
 */
@RestController
@RequestMapping("js_sdk")
public class JSSDKController extends BaseController {
    @PostMapping("api_ticket")
    public WxJsapiSignature getJsApiTicket(@RequestParam String appid, @RequestParam String url,
                                           @RequestParam String state) {
        try {
            //TODO FIX the params of POST split automatically
            String retUrl = url + "&state=" + state + "&appid=" + appid;
            return getMpService(appid).createJsapiSignature(retUrl);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    }
}
