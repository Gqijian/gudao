package me.zj22.gudao.server.web.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期时间转换
 * daogu
 * Created by 袁鹏 on 2018/2/5.
 */
public class TimeParse {
    //格式化的格式 默认："yyyy-MM-dd HH:mm:ss";
    private static final String date_format = "yyyy-MM-dd HH:mm:ss";
    //考虑多线程下的并发安全
    private static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = new ThreadLocal<>();

    public static DateFormat getSimpleDateFormat(){
        SimpleDateFormat sdf = simpleDateFormatThreadLocal.get();
        if(sdf == null){
            sdf = new SimpleDateFormat(date_format);
            simpleDateFormatThreadLocal.set(sdf);
        }
            return  sdf;
    }



    /**
     * 获取当前系统UNIX时间戳（单位：秒）
     */
    public static int getCurrentTimeUNIX(){
        return (int)(System.currentTimeMillis()/1000);
    }


    /**
     * 日期格式字符串转换成时间戳
     *
     * @param dateStr 字符串日期
     *
     * @return
     */
    public static long Time2NUIX(String dateStr){

        try {
            return  (long)getSimpleDateFormat().parse(dateStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;

    }


    /**
     * 将Unix时间戳转换成指定格式日期字符串
     * @return 返回结果 如："2018-01-01 10:01:01";
     */
    public static String NUIX2Time(Date date) {
        return NUIX2Time(0, date);
    }

    public static String NUIX2Time(int timestampString) {
        return NUIX2Time(timestampString, null);
    }

    public static String NUIX2Time(int timestampString,Date date) {
        String formatDate = null;
        if(timestampString != 0){
            Long timestamp = (long)timestampString * 1000;
            formatDate = getSimpleDateFormat().format(new Date(timestamp));
        }
        if(date != null){
            formatDate = getSimpleDateFormat().format(date);
        }
        return formatDate;
    }

}
