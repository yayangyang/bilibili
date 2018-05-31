package com.yayangyang.bilibili.base;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import com.yayangyang.lib_common.base.Constant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface MainConstant {

    String LIVE_HOME_IMG_Suffix="@192w_120h_1e_1c.webp";

    String CHANNEL="Android";
    String VERSION="2.7.003";

    @StringDef({
            //定义限定值
            LiveUpdateTagType.LIVE_RECOMMEND,
            LiveUpdateTagType.HOUR_RANK,
            LiveUpdateTagType.GAME,
            LiveUpdateTagType.MOBILE_GAME,
            LiveUpdateTagType.FUN,
            LiveUpdateTagType.PAINT
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface LiveUpdateTagType {
        String LIVE_RECOMMEND="live_recommend";
        String HOUR_RANK="hour_rank";
        String GAME="game";
        String MOBILE_GAME="mobile_game";
        String FUN="fun";
        String PAINT="paint";
    }
}
