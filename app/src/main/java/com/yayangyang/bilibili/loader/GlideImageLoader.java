package com.yayangyang.bilibili.loader;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.yayangyang.bilibili.Bean.BannerBean;
import com.yayangyang.bilibili.R;
import com.yayangyang.lib_common.app.BaseApplication;
import com.yayangyang.lib_common.app.GlideApp;
import com.yayangyang.lib_common.base.Constant;
import com.yayangyang.lib_common.utils.GlideUtil;
import com.yayangyang.lib_common.utils.LogUtils;
import com.yayangyang.lib_common.view.myView.MyImageView;

public class GlideImageLoader extends BaseImageLoader {
    @Override
    public void displayImage(Context context, Object path, MyImageView imageView) {
        BannerBean dataBean = (BannerBean) path;
        LogUtils.e("url:"+dataBean.getPic());
//        Glide.with(context).load(R.drawable.ic_download).into((ImageView) imageView.findViewById(R.id.iv_cover));

        GlideUrl cookie = new GlideUrl(dataBean.getPic(), new LazyHeaders.Builder()
                .addHeader("Accept-Encoding","gzip").build());
        //使用同一个对象才不会引起内存泄漏
//        RoundedCornersTransformation transformation =
//                (RoundedCornersTransformation) GlideUtil.getCommonTransformation();
//        Glide.with(context).load(cookie)
//                .apply(new RequestOptions())
//                .into(imageView);
        Glide.with(context).load(cookie)
                .apply(GlideUtil.getRoundCornerRequestOptions())
                .into((ImageView) imageView.findViewById(R.id.iv_cover));
    }
}