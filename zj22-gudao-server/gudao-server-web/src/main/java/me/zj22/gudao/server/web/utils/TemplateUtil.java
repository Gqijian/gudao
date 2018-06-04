package me.zj22.gudao.server.web.utils;

import com.alibaba.fastjson.JSONObject;
import me.zj22.gudao.server.web.pojo.vo.TemplateData;

import java.util.Map;

/**
 * Created by fengzheng on 2018/5/12.
 */
public class TemplateUtil {
    /**
     * 封装模板详细信息
     * @return
     */
    public static JSONObject packJsonmsg(Map<String, TemplateData> param) {
        JSONObject json = new JSONObject();
        for (Map.Entry<String,TemplateData> entry : param.entrySet()) {
            JSONObject keyJson = new JSONObject();
            TemplateData  dta=  entry.getValue();
            keyJson.put("value",dta.getValue());
            keyJson.put("color", dta.getColor());
            json.put(entry.getKey(), keyJson);
        }
        return json;
    }
}