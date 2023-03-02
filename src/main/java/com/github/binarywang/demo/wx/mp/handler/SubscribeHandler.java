package com.github.binarywang.demo.wx.mp.handler;

import java.util.Map;

import com.github.binarywang.demo.wx.mp.eneity.UserWxInfoPO;
import com.github.binarywang.demo.wx.mp.repository.UserWxInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.binarywang.demo.wx.mp.builder.TextBuilder;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Component
public class SubscribeHandler extends AbstractHandler {
    @Autowired
    public UserWxInfoRepository userWxInfoRepository;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

        // 获取微信用户基本信息
        try {
            WxMpUser userWxInfo = weixinService.getUserService()
                .userInfo(wxMessage.getFromUser(), null);
            if (userWxInfo != null) {
                userWxInfo.setUnionId("oKOdI67B5_qwzzLJttiCnA86lBa8");
                this.logger.debug("获取到用户信息[{}]", userWxInfo.getUnionId());
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
            }
        } catch (WxErrorException e) {
            if (e.getError().getErrorCode() == 48001) {
                this.logger.info("该公众号没有获取用户信息权限！");
            }
        }


        WxMpXmlOutMessage responseResult = null;
        try {
            responseResult = this.handleSpecial(wxMessage);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        if (responseResult != null) {
            return responseResult;
        }

        try {
            return new TextBuilder().build("感谢关注", wxMessage, weixinService);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
        throws Exception {
        // TODO
        return null;
    }

}
