package com.booboomx.gank.api;

import com.booboomx.gank.bean.MeizhiBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by booboomx on 17/1/18.
 */

public interface GankApi {

    // http://gank.io/api/data/福利/10/1
    @GET("data/福利/10/{page}")
    Observable<MeizhiBean> getMeizhiData(@Path("page") int page);

//    @GET("day/{year}/{month}/{day}")
//    Observable<GankData> getGankData(@Path("year") int year, @Path("month") int month, @Path("day") int day);
//
//    @GET("data/休息视频/10/{page}")
//    Observable<Video> getVideoData(@Path("page") int page);


}
