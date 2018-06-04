package me.zj22.gudao.server.web.service.impl;

import com.alibaba.fastjson.JSONObject;

import me.chanjar.weixin.mp.api.WxMpService;
import me.zj22.gudao.server.web.config.WeChatConfig;
import me.zj22.gudao.server.web.constants.Constants;
import me.zj22.gudao.server.web.enums.RequestMethodType;
import me.zj22.gudao.server.web.handler.HttpsRequestHandler;
import me.zj22.gudao.server.web.pojo.dto.OrderDTO;
import me.zj22.gudao.server.web.pojo.dto.User;
import me.zj22.gudao.server.web.pojo.vo.AccessToken;
import me.zj22.gudao.server.web.pojo.vo.TemplateData;
import me.zj22.gudao.server.web.pojo.vo.WxMpTemplateData;
import me.zj22.gudao.server.web.pojo.vo.WxMpTemplateMessage;
import me.zj22.gudao.server.web.service.PushMessageService;
import me.zj22.gudao.server.web.service.UserSerivce;
import me.zj22.gudao.server.web.utils.JsonUtils;
import me.zj22.gudao.server.web.utils.ServletContextUtil;
import me.zj22.gudao.server.web.utils.TemplateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
{{first.DATA}}
商品名称：{{keyword1.DATA}}
商家电话：{{keyword2.DATA}}
快递单号：{{keyword3.DATA}}
状态：{{keyword4.DATA}}
总价：{{keyword5.DATA}}
{{remark.DATA}}
 */
@Service
public class PushMessageServiceImpl implements PushMessageService {

    private static final Logger LOG = LoggerFactory.getLogger(PushMessageServiceImpl.class);


    @Autowired
    private UserSerivce userSerivce;

    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(WeChatConfig.getWechatTemplateId());
        //查询用户
        User user = userSerivce.selectByPrimaryKey(orderDTO.getUserId());
        //这里的openid是针对当前公众号的，不同公众号你的openid不一样
        templateMessage.setToUser("oKsSgwO277qe49-85QILbgaMOyRQ");

        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "亲，您好！您的商品已发货。"),
                new WxMpTemplateData("keyword1", "咕叨酱"),
                new WxMpTemplateData("keyword2", orderDTO.getReceiverName()),
                new WxMpTemplateData("keyword3", orderDTO.getAddress()),//快递id
                new WxMpTemplateData("keyword4", "1802014xxxxx"),
                new WxMpTemplateData("keyword5", "￥" + orderDTO.getOrderAmount()),
                new WxMpTemplateData("remark", "快递单号："+orderDTO.getTrackNumber())
        );
        templateMessage.setData(data);
        try {
            AccessToken accessToken = (AccessToken) ServletContextUtil.get().getAttribute("access_token");
            String url = Constants.TEMPLATE_URL.replace("ACCESS_TOKEN",accessToken.getAccess_token());
            LOG.info("\n==================\n"+(JsonUtils.objectToJson(templateMessage)).toString().replace("[","{").replace("]","}")+"\n==================\n");
            JSONObject jsonObject = HttpsRequestHandler.httpsRequest(url, RequestMethodType.POST, (JsonUtils.objectToJson(templateMessage)).toString().replace("[","{").replace("]","}"));
            LOG.info("【发送微信消息返回信息：】" + jsonObject.get("errcode"));

//            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        }catch (RuntimeException e) {
            LOG.error("【微信模版消息】发送失败, {}", e);
        }
    }

    /**
     * 发送微信消息(模板消息)
     * @return
     */
    @Override
    public  String sendWechatMsgToUser(OrderDTO orderDTO) {

        Map<String, TemplateData> param = getTemplateDate(orderDTO);

        AccessToken accessToken = (AccessToken) ServletContextUtil.get().getAttribute("access_token");
        String tmpurl = Constants.TEMPLATE_URL.replace("ACCESS_TOKEN",accessToken.getAccess_token());

        //查询用户
        User user = userSerivce.selectByPrimaryKey(orderDTO.getUserId());
        //这里的openid是针对当前公众号的，不同公众号你的openid不一样
        JSONObject json = new JSONObject();
        json.put("touser", user.getOpenid());
        json.put("template_id", WeChatConfig.getWechatTemplateId());
        json.put("data", TemplateUtil.packJsonmsg(param));
        try{
            LOG.info("\n\n"+json.toString()+"\n\n");
            JSONObject result = HttpsRequestHandler.httpsRequest(tmpurl, RequestMethodType.POST, json.toString());
            JSONObject resultJson = new JSONObject(result);
            LOG.info("发送微信消息返回信息：" + resultJson.get("errcode"));
            String errmsg = (String) resultJson.get("errmsg");
            if(!"ok".equals(errmsg)){  //如果为errmsg为ok，则代表发送成功，公众号推送信息给用户了。
                return "error";
            }
        }catch(Exception e){
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
    //封装模板
    private static Map<String,TemplateData> getTemplateDate(OrderDTO orderDTO){
        Map<String,TemplateData> param = new HashMap<>();
        param.put("first",new TemplateData("亲，您好！您的商品已发货。","#696969"));
        param.put("keyword1",new TemplateData("咕叨酱","#696969"));
        param.put("keyword2",new TemplateData(orderDTO.getReceiverName(),"#696969"));
        param.put("keyword3",new TemplateData(orderDTO.getAddress(),"#696969"));
        param.put("keyword4",new TemplateData("18345xxxxxx","#696969"));
        param.put("keyword5",new TemplateData("￥" + orderDTO.getOrderAmount(),"#696969"));
        param.put("remark",new TemplateData("快递单号："+orderDTO.getTrackNumber(),"#696969"));
        return param;
    }

}
