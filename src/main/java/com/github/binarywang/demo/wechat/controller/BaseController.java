package com.github.binarywang.demo.wechat.controller;

import com.github.binarywang.demo.wechat.service.WxOpenServiceInit;

import org.springframework.beans.factory.annotation.Autowired;

import me.chanjar.weixin.mp.api.WxMpService;

/**
 * @Author: Snowson
 * @Date: 2018/6/8 16:32
 * @Description:
 */
public class BaseController {
    @Autowired
    protected WxOpenServiceInit mWxOpenService;

    protected WxMpService getMpService(String appId) {
        return mWxOpenService.getWxOpenComponentService().getWxMpServiceByAppid(appId);
    }
}
