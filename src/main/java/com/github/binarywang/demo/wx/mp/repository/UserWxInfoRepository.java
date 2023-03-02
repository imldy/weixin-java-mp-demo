package com.github.binarywang.demo.wx.mp.repository;

import com.github.binarywang.demo.wx.mp.eneity.UserWxInfoPO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author imldy
 * @date 2023/03/02 18:42
 **/
public interface UserWxInfoRepository  extends MongoRepository<UserWxInfoPO, ObjectId> {
    public UserWxInfoPO findFirstByUid(String uid);
}
