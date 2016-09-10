package cn.com.findfine.timebank.log;

import android.util.Log;

/**
 * Created by yangchen on 16/9/10.
 */
public class TLog {

    private final static String TAG = "TimeBank";

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }
}
