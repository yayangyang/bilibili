package com.yayangyang.bilibili.loader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.yayangyang.bilibili.Bean.BannerBean;
import com.yayangyang.bilibili.R;
import com.yayangyang.lib_common.app.GlideApp;
import com.yayangyang.lib_common.base.Constant;
import com.yayangyang.lib_common.utils.LogUtils;
import com.yayangyang.lib_common.view.myView.MyImageView;

public class GlideImageLoader extends BaseImageLoader {
    @Override
    public void displayImage(Context context, Object path, MyImageView imageView) {
        BannerBean dataBean = (BannerBean) path;
        LogUtils.e("url:"+dataBean.cover);
//        Glide.with(context).load(R.drawable.ab_back).into(imageView);

        GlideUrl cookie = new GlideUrl(dataBean.cover, new LazyHeaders.Builder()
                .addHeader("Referer", Constant.IMG_BASE_URL)
                .addHeader("Accept-Encoding","gzip").build());
        //使用同一个对象才不会引起内存泄漏
//        RoundedCornersTransformation transformation =
//                (RoundedCornersTransformation) GlideUtil.getCommonTransformation();
//        Glide.with(context).load(cookie)
//                .apply(new RequestOptions())
//                .into(imageView);
        GlideApp.with(context).load(cookie)
                .into((ImageView) imageView.findViewById(R.id.iv_cover));
    }
}