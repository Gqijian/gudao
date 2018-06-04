package me.zj22.gudao.server.web.utils;

import me.zj22.gudao.server.web.enums.CodeEnum;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/19.
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(byte code, Class<T> enumClass){

        for (T each : enumClass.getEnumConstants()){
            if(code == each.getCode()){
                return each;
            }
        }
        return null;
    }
}
