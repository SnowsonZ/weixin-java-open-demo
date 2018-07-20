package me.chanjar.weixin.mp.bean.card;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Snowson
 * @Date: 2018/7/19 14:40
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDetailInfo implements Serializable {
    private static final long serialVersionUID = -3748893911252155153L;
    private String cardId;
    private String cardName;
}
