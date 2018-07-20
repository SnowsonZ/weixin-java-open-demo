package me.chanjar.weixin.mp.bean.card;

import java.io.Serializable;

import lombok.Data;

/**
 * @Author: Snowson
 * @Date: 2018/6/14 15:25
 * @Description:
 */
@Data
public class CardKeyInfo implements Serializable {
    private static final long serialVersionUID = -7214968038889436438L;
    private String code;
    private String card_id;
}
