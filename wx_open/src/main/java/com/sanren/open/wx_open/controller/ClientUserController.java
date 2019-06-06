package com.sanren.open.wx_open.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * @Author: Snowson
 * @Date: 2018/6/8 16:31
 * @Description:  前台用户Operation
 */
@RestController
@RequestMapping("/user")
public class ClientUserController extends BaseController {

    @PostMapping("/info")
    public WxMpUser getUserInfo(@RequestParam String code, @RequestParam String appId) {
        WxMpService mpService
                = mWxOpenService.getWxOpenComponentService().getWxMpServiceByAppid(appId);
        try {
            return mpService.oauth2getUserInfo(mpService.oauth2getAccessToken(code), "zh_CN");
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    }
}
