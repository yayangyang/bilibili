package com.yayangyang.bilibili.api.support;

import android.os.Build;
import android.os.Looper;
import android.util.Log;

import com.yayangyang.lib_common.utils.DeviceUtils;
import com.yayangyang.lib_common.utils.LogUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Retrofit2 Header拦截器。用于保存和设置Cookies
 */
public final class HeaderInterceptor implements Interceptor {

    final String vmName = DeviceUtils.getCurrentRuntimeValue();
    final String vmVersion = DeviceUtils.getVmVersion();
    final String MODEL = Build.MODEL;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        String url = original.url().toString();

        LogUtils.e("请求头22222222222222"+chain.request().toString());
        boolean isMainThread=Looper.getMainLooper() == Looper.myLooper();
        LogUtils.e("是否主线程:"+ isMainThread);

        String userAgent=vmName+"/"+vmVersion+" (Linux; U; Android "+ Build.VERSION.RELEASE
                +"; "+MODEL+" Build/NYC)";
        LogUtils.e(userAgent);
        original = original.newBuilder()
                //"Dalvik/2.1.0 (Linux; U; Android 7.1.1; Android SDK built for x86 Build/NYC)"
                .addHeader("User-Agent",userAgent)
//                    .addHeader("Referer",Constant.IMG_BASE_URL)//出现403错误(取消了这个头,增加了User-Agent头)
//                    .addHeader("Accept-Encoding","gzip")//加上报错,不知原因
                .build();

        return chain.proceed(original);
    }
}
