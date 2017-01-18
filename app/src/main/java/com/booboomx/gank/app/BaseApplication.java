package com.booboomx.gank.app;

import android.app.Application;

/**
 * Created by booboomx on 17/1/16.
 */

public class BaseApplication extends Application{

    public static  BaseApplication mInstance;

    public static BaseApplication getInstance(){
        return mInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }
}
