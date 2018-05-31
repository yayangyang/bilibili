package com.yayangyang.bilibili.ui.presenter;

import android.util.Log;
import android.widget.Toast;

import com.yayangyang.bilibili.Bean.ComicRecommend;
import com.yayangyang.bilibili.Bean.HomeLive;
import com.yayangyang.bilibili.Bean.base.Constant;
import com.yayangyang.bilibili.api.MainService;
import com.yayangyang.bilibili.base.MainConstant;
import com.yayangyang.bilibili.ui.contract.HomeLiveContract;
import com.yayangyang.lib_common.api.BilibiliApi;
import com.yayangyang.lib_common.base.RxPresenter;
import com.yayangyang.lib_common.utils.AppUtils;
import com.yayangyang.lib_common.utils.LogUtils;
import com.yayangyang.lib_common.utils.NetworkUtils;
import com.yayangyang.lib_common.utils.ToastUtils;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeLivePresenter extends RxPresenter<HomeLiveContract.View>
        implements HomeLiveContract.Presenter {
    private BilibiliApi bilibiliApi;

    @Inject
    public HomeLivePresenter(BilibiliApi bilibiliApi) {
        this.bilibiliApi = bilibiliApi;
    }

    @Override
    public void getHomeLive() {
        Disposable rxDisposable = bilibiliApi.getService(MainService.class)
        .getHomeLive(Constant.ACTIONKEY,Constant.APPKEY,Constant.BUILD,Constant.DEVICE,
                Constant.MOBI_APP,Constant.PLATFORM,Constant.SCALE).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<HomeLive>() {
                            @Override
                            public void accept(HomeLive homeLive) throws Exception {
                                LogUtils.e("getHomeLive-accept");
                                if (homeLive != null) {
                                    if (mView != null) {
                                        LogUtils.e("eeeeeeeeeeeee");
                                        mView.showHomeLive(homeLive);
                                    }
                                }else{
                                    Log.e("recommend","为空");
                                }
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable e) throws Exception {
                                LogUtils.e("getHomeLive"+e.toString());
                                if(mView!=null){
                                    mView.showError();
                                }
                            }
                        },
                        new Action() {
                            @Override
                            public void run() throws Exception {
                                LogUtils.e("complete");
                                if(mView!=null){
                                    mView.complete();
                                }
                            }
                        }
                );
        addDisposable(rxDisposable);
    }

    @Override
    public void getComicRecommendList() {
        LogUtils.e("getComicRecommendList");
//        String key = StringUtils.creatAcacheKey("recommend-list",channel,version);
//        Observable<List<ComicRecommend>> fromNetWork = comicApi.getRecommend(channel,version)
//                .compose(RxUtil.<List<ComicRecommend>>rxCacheListHelper(key));

        //依次检查disk、network
//        Observable.concat(RxUtil.rxCreateDiskObservable(key,ComicRecommend.class),fromNetWork)
        Disposable rxDisposable = bilibiliApi.getService(MainService.class).getComicRecommend(MainConstant.CHANNEL,MainConstant.VERSION).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<List<ComicRecommend>>() {
                            @Override
                            public void accept(List<ComicRecommend> list) throws Exception {
                                LogUtils.e("getRecommend-accept");
                                if (list != null) {
                                    if (mView != null) {
                                        LogUtils.e("eeeeeeeeeeeee");
                                        mView.showComicRecommendList(list);
                                    }
                                }else{
                                    Log.e("recommend","为空");
                                }
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable e) throws Exception {
                                LogUtils.e("getRecommendList"+e.toString());
                                if(mView!=null){
                                    mView.showError();
                                }
                            }
                        },
                        new Action() {
                            @Override
                            public void run() throws Exception {
                                LogUtils.e("complete");
                                if(mView!=null){
                                    mView.complete();
                                }
                            }
                        }
                );
        addDisposable(rxDisposable);
    }

}
