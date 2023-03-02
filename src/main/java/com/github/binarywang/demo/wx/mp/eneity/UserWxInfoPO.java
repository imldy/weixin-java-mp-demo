package com.github.binarywang.demo.wx.mp.eneity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * 微信用户专属的信息
 *
 * @author imldy
 * @date 2023/03/02 15:59
 **/
@Document("userWxInfo")
@Data
public class UserWxInfoPO {
    @MongoId
    ObjectId id;
    /**
     * 同一主体下的id
     */
    private String uid;
    /**
     * 小程序用户的openID
     */
    private String maOpenId;
    /**
     * 公众号用户的openID
     */
    private String mpOpenID;
}
