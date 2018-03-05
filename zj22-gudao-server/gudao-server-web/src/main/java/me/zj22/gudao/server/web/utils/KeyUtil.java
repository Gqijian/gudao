package me.zj22.gudao.server.web.utils;

import java.util.UUID;

/**
 * 随机数生成
 * daogu
 * Created by 袁鹏 on 2018/2/9.
 */
public class KeyUtil {
    /**
     * 生成唯一的主键   一般来说。订单id为String但这里为int
     * 格式: 时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey() {

        return  UUID.randomUUID().toString().replace("-","")+UUID.randomUUID().toString().replace("-","");
    }
}
