package me.zj22.gudao.server.web.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by fengzheng on 2018/5/11.
 * 得到不同操作系统路径，用来得到上传图片路径
 */
public class PathUtil {

    private static String seperator = System.getProperty("file.separator");
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();

    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "D:/gudao/upload/images/";
        } else {
            basePath = "/usr/gudao/upload/images/";
        }
        basePath = basePath.replace("/", seperator);
        return basePath;
    }

    //图片名生成
    public static String getImageName(String befName){
        String lastName = befName.substring(befName.lastIndexOf("."));
        // 获取随机的五位数
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum+lastName;
    }
}