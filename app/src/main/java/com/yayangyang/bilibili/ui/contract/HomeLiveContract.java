package com.yayangyang.bilibili.ui.contract;

import com.yayangyang.bilibili.Bean.ComicRecommend;
import com.yayangyang.bilibili.Bean.HomeLive;
import com.yayangyang.lib_common.base.BaseContract;

import java.util.List;
import java.util.Map;

public interface HomeLiveContract {

    interface View extends BaseContract.BaseView {
        void showHomeLive(HomeLive homeLive);

        void showComicRecommendList(List<ComicRecommend> comicRecommend);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getHomeLive();

        void getComicRecommendList();
    }

}
