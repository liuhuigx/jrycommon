
package com.jerryliu.jrycore.log;

public class Liu {

    private static String TAG = "jry";

    public static void d(String tag, String s) {

        log(tag + ":" + s);
    }

    public static void d(String s) {

        log(s);
    }

    public void e(String s) {
    }

    public void v(String s) {
    }

    public static void log(String msg) {
        android.util.Log.d(TAG, msg);
    }

}
