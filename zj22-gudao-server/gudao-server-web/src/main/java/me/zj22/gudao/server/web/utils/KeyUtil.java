package me.zj22.gudao.server.web.utils;

import java.util.Random;

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
    public static synchronized Integer genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(90000) + 10000;
        String uniqueKey = System.currentTimeMillis() + String.valueOf(number);
        return Integer.valueOf(uniqueKey.substring(uniqueKey.length()-9,uniqueKey.length()));
    }
}
