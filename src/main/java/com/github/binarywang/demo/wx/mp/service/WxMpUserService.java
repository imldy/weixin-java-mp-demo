package com.github.binarywang.demo.wx.mp.service;

import com.github.binarywang.demo.wx.mp.eneity.UserWxInfoPO;
import com.github.binarywang.demo.wx.mp.repository.UserWxInfoRepository;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author imldy
 * @date 2023/03/04 15:24
 **/
@Service
public class WxMpUserService {
    @Autowired
    public UserWxInfoRepository userWxInfoRepository;

    public boolean trySave(WxMpUser userWxInfo) {
        boolean edit = false;
        UserWxInfoPO userWxInfoPO = userWxInfoRepository.findFirstByUid(userWxInfo.getUnionId());
        if (userWxInfoPO == null) {
            userWxInfoPO = new UserWxInfoPO();
            userWxInfoPO.setUid(userWxInfo.getUnionId());
            userWxInfoPO.setMpOpenID(userWxInfo.getOpenId());
            edit = true;
        } else {
            if (userWxInfoPO.getMpOpenID() == null) {
                userWxInfoPO.setMpOpenID(userWxInfo.getOpenId());
                edit = true;
            }
            if (userWxInfoPO.getUid() == null) {
                userWxInfoPO.setUid(userWxInfo.getUnionId());
                edit = true;
            }
        }
        if (edit) {
            UserWxInfoPO save = userWxInfoRepository.save(userWxInfoPO);
        }
        return true;
    }
}
