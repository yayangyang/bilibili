package com.yayangyang.lib_common.utils;

import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.BitmapTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Administrator on 2017/12/8.
 */

public class GlideUtil {

    private static BitmapTransformation sRoundCommonBitmapTransformation;
    private static BitmapTransformation sRoundTopBitmapTransformation;
    private static BitmapTransformation sRoundBottomBitmapTransformation;

    private static RequestOptions sDefaultRequestOptions;
    private static RequestOptions sRoundCornerRequestOptions;
    private static RequestOptions sCircleCornerRequestOptions;

    public static BitmapTransformation getRoundCommonTransformation(){
        if(sRoundCommonBitmapTransformation==null){
            sRoundCommonBitmapTransformation = new RoundedCornersTransformation(ScreenUtils.dpToPxInt(8), 0);
        }
        return sRoundCommonBitmapTransformation;
    }

    public static BitmapTransformation getRoundTopTransformation(){
        if(sRoundTopBitmapTransformation==null){
            sRoundTopBitmapTransformation = new RoundedCornersTransformation(ScreenUtils.dpToPxInt(6), 0,
                    RoundedCornersTransformation.CornerType.TOP);
        }
        return sRoundTopBitmapTransformation;
    }

    public static BitmapTransformation getRoundBottomTransformation(){
        if(sRoundBottomBitmapTransformation==null){
            sRoundBottomBitmapTransformation = new RoundedCornersTransformation(ScreenUtils.dpToPxInt(6), 0,
                    RoundedCornersTransformation.CornerType.BOTTOM);
        }
        return sRoundBottomBitmapTransformation;
    }

    public static RequestOptions getDefaultRequestOptions(){
        if(sDefaultRequestOptions==null){
            sDefaultRequestOptions = new RequestOptions()
                    .format(DecodeFormat.PREFER_RGB_565)
//                    .disallowHardwareConfig()
                    .skipMemoryCache(true);
        }
        return sDefaultRequestOptions;
    }

    public static RequestOptions getRoundCornerRequestOptions(){
        if(sRoundCornerRequestOptions==null){
            sRoundCornerRequestOptions = new RequestOptions()
                    .format(DecodeFormat.PREFER_RGB_565)
//                    .disallowHardwareConfig()
                    .transform(GlideUtil.getRoundCommonTransformation())
                    .skipMemoryCache(true);
        }
        return sRoundCornerRequestOptions;
    }

    public static RequestOptions getCircleCornerRequestOptions(){
        if(sCircleCornerRequestOptions==null){
            sCircleCornerRequestOptions = new RequestOptions()
                    .format(DecodeFormat.PREFER_RGB_565)
//                    .disallowHardwareConfig()
                    .skipMemoryCache(true)
                    .circleCrop();
        }
        return sCircleCornerRequestOptions;
    }

}
