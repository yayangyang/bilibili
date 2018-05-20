package com.yayangyang.lib_common.api;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;

@Singleton//这里的声明应该相当于在Module类中声明的提供方法的@Singleton
public class BilibiliApi {

    private Retrofit mRetrofit;

    private Map<String, Object> mServiceList=new HashMap<>();

    @Inject
    public BilibiliApi(Retrofit retrofit) {
        mRetrofit=retrofit;
    }

    public synchronized <T> T getService(Class<T> service) {
        T retrofitService = (T) mServiceList.get(service.getCanonicalName());
        if (retrofitService == null) {
            retrofitService = mRetrofit.create(service);
            mServiceList.put(service.getCanonicalName(), retrofitService);
        }
        return retrofitService;
    }

}
