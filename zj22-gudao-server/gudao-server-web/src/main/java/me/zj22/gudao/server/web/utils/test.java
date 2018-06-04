package me.zj22.gudao.server.web.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by fengzheng on 2018/5/11.
 */
public class test {
    public  static void  main(String[] args) throws IOException {
        File file = new File("C:\\Users\\fengzheng\\Desktop");
        String filePath = PathUtil.getImgBasePath() +PathUtil.getImageName("xxx.jpg");
        File saveDir = new File(filePath);
        if (!saveDir.getParentFile().exists())
            saveDir.getParentFile().mkdirs();

       System.out.print(filePath);

    }
}