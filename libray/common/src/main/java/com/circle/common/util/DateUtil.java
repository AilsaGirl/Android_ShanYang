package com.circle.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期操作
 * Created by Circle on 2017/6/12 0012.
 */

public class DateUtil {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat dateymdhmFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static SimpleDateFormat dateymdhFormat = new SimpleDateFormat("yyyy年MM月dd日HH时");
    private static SimpleDateFormat dateFormatMMDDHHMM = new SimpleDateFormat("MM月dd号 HH:mm");
    private static SimpleDateFormat mmssFormat = new SimpleDateFormat("mm:ss");
    private static SimpleDateFormat mdFormat = new SimpleDateFormat("MM-dd");
    private static SimpleDateFormat HMFormat = new SimpleDateFormat("HH:mm");

    public static String formatTommss(Long mills) {
        return mmssFormat.format(new Date(mills));
    }

    public static String formatToShortDate(Long mills) {
        return dateFormat.format(new Date(mills));
    }

    public static String formatToMMDDHHMM(Long mills) {
        return dateFormatMMDDHHMM.format(new Date(mills));
    }

    public static String formatToYMDH(Long mills) {
        return dateFormatMMDDHHMM.format(new Date(mills));
    }

    public static String formatToYMDHHM(Long mills) {
        return dateymdhmFormat.format(new Date(mills));
    }

    public static String formatToMD(Long mills) {
        return mdFormat.format(new Date(mills));
    }

    public static String formatToHM(Long mills) {
        return HMFormat.format(new Date(mills));
    }

    public static String getTimeState() {
        Date date = new Date();
        if (date.getHours() < 9) {
            return "早上好";
        }
        if(date.getHours() < 12){
            return "上午好";
        }
        if (date.getHours() < 14) {
            return "中午好";
        }
        if (date.getHours() < 18) {
            return "下午好";
        }
        if (date.getHours() < 24) {
            return "晚上好";
        }
        return "你好";
    }
}
