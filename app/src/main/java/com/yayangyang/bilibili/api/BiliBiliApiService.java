package com.yayangyang.bilibili.api;


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

public interface BiliBiliApiService {

    @FormUrlEncoded
    @POST("http://user.dmzj.com/login/three_confirm")
    Observable<Login> login(@FieldMap Map<String, String> params);

}