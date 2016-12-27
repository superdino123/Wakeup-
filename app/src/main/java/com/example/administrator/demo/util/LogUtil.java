package com.example.administrator.demo.util;

/**
 * Created by Administrator on 2016/12/27.
 */
import android.util.Log;

public class LogUtil {

    public static boolean isShowLog = false;//true:打印log，false:不打印log

    public static void e(String message) {
        if (!isShowLog) return;
        Log.e("qrobot", message);
    }

}