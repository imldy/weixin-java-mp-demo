package com.github.binarywang.demo.wx.mp.handler;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.builder.outxml.ImageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.EventType;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Component
public class MenuHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {
        switch (wxMessage.getEventKey()){
            case "wx":
                ImageBuilder imageBuilder = WxMpXmlOutMessage.IMAGE().mediaId("ygQ4DdEnLKTOdfs6loDCFw-LVgklDzzKquwNq8bLkkgYwcqRwxywn-iQXA_6hhtb");
                return imageBuilder.fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                    .build();
            case "wxGroup":
                ImageBuilder imageBuilder1 = WxMpXmlOutMessage.IMAGE().mediaId("ygQ4DdEnLKTOdfs6loDCF0uE-K0jq1S--cHBGul4LKqhjuCJvThCmKVGbAtJfutb");
                return imageBuilder1.fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                    .build();
        }
        if (EventType.VIEW.equals(wxMessage.getEvent())) {
            return null;
        }

        return WxMpXmlOutMessage.TEXT().content("Hi")
            .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
            .build();
    }

}
