package me.zj22.gudao.server.web.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/9.
 */
@Component
public class WeChatPayConfig {

   @Bean
   public BestPayServiceImpl basePayService(){
      WxPayH5Config wxPayH5Config = new WxPayH5Config();
      wxPayH5Config.setAppId(WeChatConfig.getWechatAppId());
      wxPayH5Config.setAppSecret(WeChatConfig.getWechatAppSecret());
      wxPayH5Config.setMchId(WeChatConfig.getWechatMchId());
      wxPayH5Config.setMchKey(WeChatConfig.getWechatMchkey());
      wxPayH5Config.setKeyPath(WeChatConfig.getWechatKeypath());
      wxPayH5Config.setNotifyUrl(WeChatConfig.getWechatNotifyurl());
      BestPayServiceImpl bestPayServiceImpl = new BestPayServiceImpl();
      bestPayServiceImpl.setWxPayH5Config(wxPayH5Config);

      return bestPayServiceImpl;
   }
}
