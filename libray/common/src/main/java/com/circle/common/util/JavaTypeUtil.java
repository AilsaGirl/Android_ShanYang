package com.circle.common.util;

/**
 * 类型转换
 * Created by Cicle on 2017/4/13 0013.
 */

public class JavaTypeUtil {

    public static int StringToInt(String value){
        try {
            return Integer.parseInt(value);
        }catch (Exception e){
            return 0;
        }
    }
    public static double StringToDouble(String value){
        try {
            return Double.parseDouble(value);
        }catch (Exception e){
            return 0;
        }
    }


    public static long StringToLong(String value){
        try {
            return Long.parseLong(value);
        }catch (Exception e){
            return 0;
        }
    }
}
