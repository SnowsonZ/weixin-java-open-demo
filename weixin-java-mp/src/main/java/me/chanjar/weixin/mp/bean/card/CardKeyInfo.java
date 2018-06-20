package me.chanjar.weixin.mp.bean.card;

import java.io.Serializable;

/**
 * @Author: Snowson
 * @Date: 2018/6/14 15:25
 * @Description:
 */
public class CardKeyInfo implements Serializable {
    private static final long serialVersionUID = -7214968038889436438L;
    private String code;
    private String card_id;

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
