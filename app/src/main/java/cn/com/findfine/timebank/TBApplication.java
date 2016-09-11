package cn.com.findfine.timebank;

import android.app.Application;
import android.content.Context;

/**
 * Created by yangchen on 16/9/3.
 */
public class TBApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}
