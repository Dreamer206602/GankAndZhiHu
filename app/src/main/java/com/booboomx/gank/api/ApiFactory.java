package com.booboomx.gank.api;

/**
 * Created by booboomx on 17/1/17.
 */

public class ApiFactory {
    public static final Object monitor = new Object();
    public static ZhiHuApi zhiHuApi = null;
    public static GankApi gankApi = null;


    public static ZhiHuApi getZhiHuApi() {
        synchronized (monitor) {
            if (zhiHuApi == null) {
                zhiHuApi = new ApiRetrofit().getZhiHuApi();
            }
            return zhiHuApi;
        }
    }


    public static GankApi getGankApi() {
        synchronized (monitor) {
            if (gankApi == null) {
                gankApi = new ApiRetrofit().getGankApi();
            }
            return gankApi;
        }
    }
}


