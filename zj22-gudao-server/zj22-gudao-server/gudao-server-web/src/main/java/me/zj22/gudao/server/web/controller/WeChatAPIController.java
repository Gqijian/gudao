package me.zj22.gudao.server.web.controller;

import me.zj22.gudao.server.web.config.WeChatConfig;
import me.zj22.gudao.server.web.utils.CheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/8.
 */
@Controller
@RequestMapping("/api")
public class WeChatAPIController {

    private static final Logger LOG = LoggerFactory.getLogger(WeChatAPIController.class);

    /**
     * 校验信息是否是从微信服务器发过来的
     *
     * @param req
     * @param out
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/wechat", method = {RequestMethod.GET})
    public void valid(HttpServletRequest req, PrintWriter out) throws NoSuchAlgorithmException {
        String signature = req.getParameter("signature"); // 微信加密签名
        String timestamp = req.getParameter("timestamp"); // 时间戳
        String nonce = req.getParameter("nonce"); // 随机数
        String echostr = req.getParameter("echostr"); // 随机字符串
        // token配对
        String token = WeChatConfig.getWechatAppToken();
        if (LOG.isDebugEnabled()) {
            LOG.debug("\n\n\nselect token is:" + token + "\n\n\n");
        }
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (CheckUtil.checkSignature(token, signature, timestamp, nonce)) {
            // 还需要再系统中找到用户的url与之匹配再返回echostr
            LOG.info("\n\n\n--------------------------------\n微信成功接入\n-------------------------------\n\n\n");
            out.print(echostr);
        } else {
            LOG.error("微信以外的站点调用微信接口");
        }
        out.flush();
        out.close();
    }
}

