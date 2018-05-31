package com.yayangyang.bilibili.api;


import com.yayangyang.bilibili.Bean.ComicRecommend;
import com.yayangyang.bilibili.Bean.HomeLive;
import com.yayangyang.bilibili.Bean.user.ComicUpdate;
import com.yayangyang.bilibili.Bean.user.Login;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface MainService {

    @GET("/room/v2/AppIndex/getAllList")
    Observable<HomeLive> getHomeLive(@Query("actionKey") String actionKey,
                                           @Query("appkey") String appkey,
                                           @Query("build") String build,
                                           @Query("device") String device,
                                           @Query("mobi_app") String mobi_app,
                                           @Query("platform") String platform,
                                           @Query("scale") String scale);

    /**
     * 获取推荐漫画
     * @param channel
     * @param version
     * @return
     */
    @GET("http://v2.api.dmzj.com/v3/recommend.json")
    Observable<List<ComicRecommend>> getComicRecommend(@Query("channel") String channel, @Query("version") String version);

}