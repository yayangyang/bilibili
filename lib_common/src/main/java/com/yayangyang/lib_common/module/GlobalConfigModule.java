package com.yayangyang.lib_common.module;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.yayangyang.lib_common.base.Constant;
import com.yayangyang.lib_common.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;

/**
 * ================================================
 * 框架独创的建造者模式 {@link Module},可向框架中注入外部配置的自定义参数
 */
@Module
public class GlobalConfigModule {
    private HttpUrl mBaseUrl;
    private List<Interceptor> mInterceptors;
    private List<Interceptor> mNetWorkInterceptors;
    private ClientModule.OkhttpConfiguration mOkhttpConfiguration;
    private ClientModule.RetrofitConfiguration mRetrofitConfiguration;

    private GlobalConfigModule(Builder builder) {
        this.mBaseUrl = builder.baseUrl;
        this.mInterceptors = builder.interceptors;
        this.mNetWorkInterceptors = builder.netWorkInterceptors;
        this.mOkhttpConfiguration = builder.okhttpConfiguration;
        this.mRetrofitConfiguration = builder.retrofitConfiguration;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Singleton
    @Provides
    @Named("interceptors")
    List<Interceptor> provideInterceptors() {
        return mInterceptors;
    }

    @Singleton
    @Provides
    @Named("netWorkInterceptors")
    List<Interceptor> provideNetWorkInterceptors() {
        return mNetWorkInterceptors;
    }

    @Singleton
    @Provides
    HttpUrl provideBaseUrl() {
        return mBaseUrl == null ? HttpUrl.parse(Constant.API_BASE_URL) : mBaseUrl;
    }

    @Singleton
    @Provides
    @Nullable
    ClientModule.OkhttpConfiguration provideOkhttpConfiguration() {
        return mOkhttpConfiguration;
    }

    @Singleton
    @Provides
    @Nullable
    ClientModule.RetrofitConfiguration provideRetrofitConfiguration() {
        return mRetrofitConfiguration;
    }

    public static final class Builder {
        private HttpUrl baseUrl;
        private List<Interceptor> interceptors;
        private List<Interceptor> netWorkInterceptors;
        private ClientModule.OkhttpConfiguration okhttpConfiguration;
        private ClientModule.RetrofitConfiguration retrofitConfiguration;

        private Builder() {
        }

        public Builder baseurl(String baseUrl) {//基础url
            if (TextUtils.isEmpty(baseUrl)) {
                throw new NullPointerException("BaseUrl can not be empty");
            }
            this.baseUrl = HttpUrl.parse(baseUrl);
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor) {//动态添加任意个interceptor
            if (interceptors == null)
                interceptors = new ArrayList<>();
            this.interceptors.add(interceptor);
            return this;
        }

        public Builder addNetWorkInterceptor(Interceptor netWorkInterceptor) {
            LogUtils.e("添加了");
            if (netWorkInterceptors == null)
                netWorkInterceptors = new ArrayList<>();
            this.netWorkInterceptors.add(netWorkInterceptor);
            return this;
        }

        public Builder okhttpConfiguration(ClientModule.OkhttpConfiguration okhttpConfiguration) {
            this.okhttpConfiguration = okhttpConfiguration;
            return this;
        }

        public Builder retrofitConfiguration(ClientModule.RetrofitConfiguration retrofitConfiguration) {
            this.retrofitConfiguration = retrofitConfiguration;
            return this;
        }

        public GlobalConfigModule build() {
            return new GlobalConfigModule(this);
        }

    }

}
