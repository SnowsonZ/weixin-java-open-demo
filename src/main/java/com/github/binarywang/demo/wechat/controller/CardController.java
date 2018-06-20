package com.github.binarywang.demo.wechat.controller;

import com.sun.istack.internal.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import me.chanjar.weixin.common.bean.WxCardApiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.card.CardKeyInfo;
import me.chanjar.weixin.mp.bean.result.WxMpCardResult;

/**
 * @Author: Snowson
 * @Date: 2018/6/8 16:30
 * @Description:
 */
@RestController
@RequestMapping("card")
public class CardController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("api_ticket")
    public String getCardApiTicket(@RequestParam String appId) {
        try {
            return getMpService(appId).getCardService().getCardApiTicket();
        } catch (WxErrorException e) {
            e.printStackTrace();
            logger.error("获取carApiTicket错误");
        }
        return null;
    }

    @PostMapping("api_signature")
    public WxCardApiSignature getCardApiSignature(@RequestParam String appId,
                                                  @RequestParam @Nullable String... optionalSignParam) {
        try {
            return getMpService(appId).getCardService().createCardApiSignature(optionalSignParam);
        } catch (WxErrorException e) {
            e.printStackTrace();
            logger.error("获取cardApiSignature错误");
        }
        return null;
    }

    @PostMapping("card_code")
    public String getCode(@RequestParam String appId, @RequestParam String encrypt_code) {

        try {
            return getMpService(appId).getCardService().decryptCardCode(encrypt_code);
        } catch (WxErrorException e) {
            e.printStackTrace();
            logger.error("解码code失败");
        }
        return null;
    }

    @PostMapping("code_query")
    public WxMpCardResult checkCode(@RequestParam String appId, @RequestParam String cardId,
                                    @RequestParam String code, @RequestParam boolean checkConsume) {

        try {
            return getMpService(appId).getCardService().queryCardCode(cardId, code, checkConsume);
        } catch (WxErrorException e) {
            e.printStackTrace();
            logger.error("检查code失败");
        }
        return null;

    }

    @PostMapping("code_consume")
    public String consumeCode(@RequestParam String appId, @RequestParam String code,
                              @RequestParam @Nullable String cardId) {
        try {
            return getMpService(appId).getCardService().consumeCardCode(code, cardId);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("card_detail")
    public String getCardDetail(@RequestParam String appId, @RequestParam String cardId) {
        try {
            return getMpService(appId).getCardService().getCardDetail(cardId);
        } catch (WxErrorException e) {
            e.printStackTrace();
            logger.error("获取卡券详情失败");
        }
        return null;
    }

    /**
     * 随机获取一张优惠券
     *
     * @param appId
     * @return
     */
    @PostMapping("one")
    public String getCardRandom(@RequestParam String appId) {
        try {
            ArrayList<String> cardList = getMpService(appId).getCardService()
                    .getCardList(0, 50, "");
            while (cardList.size() > 0) {
                int index = (int) (Math.random() * cardList.size());
                String cardId = cardList.get(index);
                //判断库存量
                if (getMpService(appId).getCardService().hasRestCard(cardId)) {
                    return cardId;
                } else {
                    cardList.remove(index);
                }
            }
            return null;
        } catch (WxErrorException e) {
            e.printStackTrace();
            logger.error("获取优惠券失败...");
        }
        return null;
    }

    /**
     * 用户名下卡券
     *
     * @param appId
     * @param openId
     * @param cardId 可以为空
     * @return
     */
    @PostMapping("card_list")
    public ArrayList<CardKeyInfo> getAllCardByUser(@RequestParam String appId,
                                                   @RequestParam String openId,
                                                   @Nullable @RequestParam String cardId) {
        try {
            return getMpService(appId).getCardService().getCardListByOpenId(openId, cardId);
        } catch (WxErrorException e) {
            e.printStackTrace();
            logger.error("get card list by openId error");
        }
        return null;
    }

}
