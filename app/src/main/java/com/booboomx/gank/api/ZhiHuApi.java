package com.booboomx.gank.api;

import com.booboomx.gank.bean.NewsTimeLineBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by booboomx on 17/1/17.
 */

public interface ZhiHuApi {

//    @GET("start-image/1080*1920")
//    Observable<>


    // http://news-at.zhihu.com/api/4/news/latest
    @GET("news/latest")
    Observable<NewsTimeLineBean>getLatestNews();

    @GET("news/before/{time}")
    Observable<NewsTimeLineBean>getBeforeNews(@Path("time")String time);


//    @GET("news/{id}")
//    Observable<News>getDetailNews(@Path("id")String id);




}
