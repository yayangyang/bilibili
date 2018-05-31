package com.yayangyang.bilibili.ui.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yayangyang.bilibili.Bean.HomeLive;
import com.yayangyang.bilibili.MainUtils.MainStringUtil;
import com.yayangyang.bilibili.R;
import com.yayangyang.lib_common.app.GlideApp;
import com.yayangyang.lib_common.utils.GlideUtil;
import com.yayangyang.lib_common.utils.LogUtils;

import java.util.List;

public class HomeLiveAdapter extends HomeLiveMultiItemQuickAdapter<HomeLive.DataBean.ModuleListBean.ListBean,BaseViewHolder> {
    public HomeLiveAdapter(List<HomeLive.DataBean.ModuleListBean.ListBean> data) {
        super(data);
        addItemType(HomeLiveMultiItemQuickAdapter.HOME_LIVE, R.layout.item_home_live);
        addItemType(HomeLiveMultiItemQuickAdapter.HOME_LIVE_RANK, R.layout.item_home_live_rank);
    }

    @Override
    protected void convert(BaseViewHolder helper, final HomeLive.DataBean.ModuleListBean.ListBean item) {
        LogUtils.e("ccccccccccccccc");
        ImageView iv_cover=helper.getView(R.id.iv_cover);
        if(helper.getItemViewType()==HomeLiveMultiItemQuickAdapter.HOME_LIVE){
            helper.setText(R.id.tv_title,item.getTitle());
        }

//        float density = mContext.getResources().getDisplayMetrics().density;
//        RoundedCornersTransformation roundedCornersTransformation =
//                new RoundedCornersTransformation((int) (density*20), 0);
        LogUtils.e("CompleteUrl:"+MainStringUtil.parseUrl(item.getCover()));
        GlideUrl cookie = new GlideUrl(MainStringUtil.parseUrl(item.getCover()),
                new LazyHeaders.Builder()
//                .addHeader("Referer", MainConstant.IMG_BASE_URL)
                .addHeader("Accept-Encoding","gzip").build());
        GlideApp.with(mContext).load(cookie)
//                .placeholder(R.drawable.bg_common_place_holder)
                .apply(GlideUtil.getRoundCornerRequestOptions().transform(GlideUtil.getRoundTopTransformation()))
//                .apply(RequestOptions.bitmapTransform(roundedCornersTransformation))
                .into(iv_cover);
        helper.addOnClickListener(R.id.fr_normal);
        helper.setText(R.id.tv_name,item.getUname());
        helper.setText(R.id.tv_type,item.getArea_v2_name());
    }

}
