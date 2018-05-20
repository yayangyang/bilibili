package com.yayangyang.lib_common.app;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.yayangyang.lib_common.utils.GlideUtil;

/**
 * Created by Administrator on 2017/12/11.
 */

@GlideModule
public final class MyAppGlideModule extends AppGlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDefaultRequestOptions(
                GlideUtil.getDefaultRequestOptions());
    }
}
