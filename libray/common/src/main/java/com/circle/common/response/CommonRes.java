package com.circle.common.response;

import java.io.Serializable;


/**
 * Created by Circle on 2017/4/2 0002.
 */

public class CommonRes<T> implements Serializable {

    public int code;// 200,
    public T result;
    public String msg;


    public boolean success() {
        return code == 1;
    }

    public boolean isTokenExpire() {
        return code == 406;
    }
}
