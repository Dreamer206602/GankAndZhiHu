package com.booboomx.gank.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by booboomx on 17/1/17.
 */

public class StateUtils {

    public static  boolean isNetworkAvailable(Context context){

        if(context!=null){
            ConnectivityManager manager= (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo info =
                    manager.getActiveNetworkInfo();
            if(info!=null){
                return info.isAvailable();
            }

        }

        return false;
    }



}
