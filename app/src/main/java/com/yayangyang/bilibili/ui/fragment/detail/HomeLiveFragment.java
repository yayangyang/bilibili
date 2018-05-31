package com.yayangyang.bilibili.ui.fragment.detail;

import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yayangyang.bilibili.Bean.BannerBean;
import com.yayangyang.bilibili.Bean.ComicRecommend;
import com.yayangyang.bilibili.Bean.HomeLive;
import com.yayangyang.bilibili.MainUtils.MainStringUtil;
import com.yayangyang.bilibili.R;
import com.yayangyang.bilibili.base.MainConstant;
import com.yayangyang.bilibili.component.DaggerMainComponent;
import com.yayangyang.bilibili.loader.GlideImageLoader;
import com.yayangyang.bilibili.ui.adapter.HomeLiveAdapter;
import com.yayangyang.bilibili.ui.adapter.HomeLiveMultiItemQuickAdapter;
import com.yayangyang.bilibili.ui.contract.HomeLiveContract;
import com.yayangyang.bilibili.ui.presenter.HomeLivePresenter;
import com.yayangyang.lib_common.app.GlideApp;
import com.yayangyang.lib_common.base.BaseFragment;
import com.yayangyang.lib_common.component.AppComponent;
import com.yayangyang.lib_common.decoration.CommonSpaceItemDecoration;
import com.yayangyang.lib_common.decoration.GridSpacingItemDecoration;
import com.yayangyang.lib_common.decoration.SpaceItemDecoration;
import com.yayangyang.lib_common.utils.DeviceUtils;
import com.yayangyang.lib_common.utils.GlideUtil;
import com.yayangyang.lib_common.utils.LogUtils;
import com.yayangyang.lib_common.utils.ScreenUtils;
import com.yayangyang.lib_common.utils.ToastUtils;
import com.yayangyang.lib_common.view.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class HomeLiveFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,
        HomeLiveContract.View,BaseQuickAdapter.OnItemChildClickListener,View.OnClickListener,OnBannerListener{

    private String strz[]={"推荐直播","小时榜","游戏","手游","娱乐","绘画 "};

    private List<HeaderViewHolder> mHeaderViewHolderArrayList;

    @BindView(R.id.sw)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.ll)
    LinearLayout ll;

    @BindView(R.id.banner)
    Banner banner;

    @BindViews({R.id.iv_game,R.id.iv_mobile_game,R.id.iv_fun,R.id.iv_commonly_used})
    ImageView ivz[];

    @BindViews({R.id.rv_live_recommend,R.id.rv_hour_rank,R.id.rv_game,
            R.id.rv_mobile_game,R.id.rv_fun,R.id.rv_paint})
    RecyclerView rvz[];

    private List<HomeLiveAdapter> mAdapterList;

    @Inject
    HomeLivePresenter mPresenter;

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onClick(View v) {
        if(v.getTag()!=null){
            String tag= (String) v.getTag();
            if(MainConstant.LiveUpdateTagType.LIVE_RECOMMEND.equals(tag)){

            }else if(MainConstant.LiveUpdateTagType.HOUR_RANK.equals(tag)){

            }else if(MainConstant.LiveUpdateTagType.GAME.equals(tag)){

            }else if(MainConstant.LiveUpdateTagType.MOBILE_GAME.equals(tag)){

            }else if(MainConstant.LiveUpdateTagType.FUN.equals(tag)){

            }else if(MainConstant.LiveUpdateTagType.PAINT.equals(tag)){

            }
        }
    }

    @Override
    public void OnBannerClick(int position) {
        List<BannerBean> list = banner.getImages();
        BannerBean dataBean = list.get(position);

        DeviceUtils.printDisplayInfo(getActivity());
    }

    @Override
    public int getLayoutResId() {
        LogUtils.e("fragemnet-getLayoutResId");
        return R.layout.fragment_home_live;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .build().inject(this);
    }

    @Override
    public void attachView() {
        mPresenter.attachView(this);
    }

    @Override
    public void onRefresh() {
        mPresenter.getHomeLive();
//        if(LoginUtil.isLogin()){
//            getData(true);
//            mPresenter.getSubscriptionComic(subscriptionParams);
//        }else{
//            rvz[0].setVisibility(View.GONE);
//        }
    }

    @Override
    public void initDatas() {
        LogUtils.e("initDatas-rvz.length"+rvz.length);
        mHeaderViewHolderArrayList=new ArrayList<>();
        for(int i=0;i<rvz.length;i++){
            View view = View.inflate(getActivity(), R.layout.header_home_live, null);
            HeaderViewHolder holder = new HeaderViewHolder(view);

//            holder.tv_more.setTag(MainConstant.UPDATE,);
            holder.tv_more.setOnClickListener(this);
            if(i==1){
                Drawable drawable = getResources().getDrawable(R.drawable.ic_live_im_arrow_right);
                holder.tv_more.setCompoundDrawablesWithIntrinsicBounds(null,null,drawable,null);
            }

            holder.tv_title.setText(strz[i]);
            mHeaderViewHolderArrayList.add(holder);
        }
        LogUtils.e("mHeaderViewHolderArrayList.size"+mHeaderViewHolderArrayList.size());
    }

    @Override
    public void configViews() {
        LogUtils.e("configViews");

        mAdapterList=new ArrayList<>();
        for(int i=0;i<rvz.length;i++){
            HomeLiveAdapter adapter = new HomeLiveAdapter(null);
            adapter.setOnItemChildClickListener(this);
            mAdapterList.add(adapter);
        }
        for(int i=0;i<rvz.length;i++){
            HomeLiveAdapter adapter=mAdapterList.get(i);
            rvz[i].setNestedScrollingEnabled(false);
            if(i==1){
                rvz[i].setLayoutManager(new GridLayoutManager(getActivity(),3));
            }else{
                rvz[i].setLayoutManager(new GridLayoutManager(getActivity(),2));
                int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.dp_13);
                rvz[i].addItemDecoration(new GridSpacingItemDecoration(2,dimensionPixelSize,true));
            }
//            rvz[i].removeItemDecoration(rvz[i].getItemDecorationAt(0));
//            rvz[i].addItemDecoration(new CommonSpaceItemDecoration(ScreenUtils.dpToPxInt(5)));
            //使用了brvah框架bindToRecyclerView方法里面执行了setAdapter,
            //执行onAttachedToRecyclerView方法绑定spanCount,所以应该在setLayoutManager之后才能绑定spancount
            adapter.bindToRecyclerView(rvz[i]);
        }
        LogUtils.e("mAdapterList.size"+mAdapterList.size());

        mSwipeRefreshLayout.setOnRefreshListener(this);
        onRefresh();
    }

    @Override
    public void showError() {
        LogUtils.e("showError");
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void complete() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showHomeLive(HomeLive homeLive) {
        LogUtils.e("showHomeLive");
        ll.setVisibility(View.VISIBLE);

        List<HomeLive.DataBean.ModuleListBean> list = homeLive.getData().getModule_list();
        List<HomeLive.DataBean.ModuleListBean> carouselList=new ArrayList<>();
        List<HomeLive.DataBean.ModuleListBean> mainlList=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            if(i<3){
                carouselList.add(list.get(i));
            }else{
                mainlList.add(list.get(i));
            }
        }
        List<HomeLive.DataBean.ModuleListBean.ListBean> dataBeans = carouselList.get(0).getList();
        ArrayList<BannerBean> dataList=new ArrayList();
        ArrayList<String> titles=new ArrayList();
        for(int i=0;i<dataBeans.size();i++){
            BannerBean dataBean = new BannerBean();
            dataBean.setId(dataBeans.get(i).getId());
            dataBean.setLink(dataBeans.get(i).getLink());
            dataBean.setPic(dataBeans.get(i).getPic());
            dataList.add(dataBean);
            LogUtils.e("cover:"+dataBeans.get(i).getPic());
            titles.add(dataBeans.get(i).getTitle());
        }

        initBaner(dataList,titles);
        initLabel(carouselList.get(1).getList());

        for(int i=0;i<mainlList.size();i++){
            List<HomeLive.DataBean.ModuleListBean.ListBean> list1 = mainlList.get(i).getList();
            if(list1.size()>=6){
                mainlList.get(i).setList(list1.subList(0,6));
            }
        }
        int size=mainlList.size();
        if(size==5){
            rvz[1].setVisibility(View.GONE);
        }else{
            rvz[1].setVisibility(View.VISIBLE);
        }
        for(int i=0;i<mainlList.size();i++){
            LogUtils.e("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww"+i);
            int type = mainlList.get(i).getModule_info().getType() != 5 ? HomeLiveMultiItemQuickAdapter.HOME_LIVE
                    : HomeLiveMultiItemQuickAdapter.HOME_LIVE_RANK;
            if(size==6){
                if(mAdapterList.get(i).getHeaderLayout()==null){
                    mAdapterList.get(i).setHeaderView(mHeaderViewHolderArrayList.get(i).view);
                }
                mAdapterList.get(i).setType(type);
                mAdapterList.get(i).setNewData(mainlList.get(i).getList());
            }else{
                if(i>0){
                    if(mAdapterList.get(i+1).getHeaderLayout()==null){
                        mAdapterList.get(i+1).setHeaderView(mHeaderViewHolderArrayList.get(i+1).view);
                    }
                    mAdapterList.get(i+1).setType(type);
                    mAdapterList.get(i+1).setNewData(mainlList.get(i).getList());
                }else{
                    if(mAdapterList.get(i).getHeaderLayout()==null){
                        mAdapterList.get(i).setHeaderView(mHeaderViewHolderArrayList.get(i).view);
                    }
                    mAdapterList.get(i).setType(type);
                    mAdapterList.get(i).setNewData(mainlList.get(i).getList());
                }
            }
        }
    }

    @Override
    public void showComicRecommendList(List<ComicRecommend> comicRecommend) {

    }

    private void initLabel(List<HomeLive.DataBean.ModuleListBean.ListBean> dataBeans) {
        LogUtils.e("initLabel:"+ivz.length);
        for(int i=0;i<ivz.length;i++){
            LogUtils.e("initLabel:"+dataBeans.get(i).getPic());
            GlideUrl cookie = new GlideUrl(dataBeans.get(i).getPic(),
                    new LazyHeaders.Builder().addHeader("Accept-Encoding","gzip").build());
            GlideApp.with(mContext).load(cookie)
                    .apply(GlideUtil.getCircleCornerRequestOptions())
                    .into(ivz[i]);
        }
    }

    private void initBaner(ArrayList images,ArrayList<String> titles) {
//        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams
//                (ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.dpToPxInt(200));
//        banner.setLayoutParams(params);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
//        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(5000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置page监听器
        banner.setOnBannerListener(this);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public void onDestroyView() {
        LogUtils.e("HomeLiveFragment-onDestroyView");
        mPresenter.detachView();

        mAdapterList=null;
        mHeaderViewHolderArrayList=null;
        //stopAutoPlay方法放在onDestroyView方法之前,
        //BaseFragment的onDestroyView方法里unbind.unbind方法应该是将这些它赋值的变量置为空了
        banner.stopAutoPlay();
        super.onDestroyView();
    }

    static class HeaderViewHolder {
        @BindView(R.id.iv_cover)
        ImageView iv_cover;
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.tv_description)
        TextView tv_description;
        @BindView(R.id.tv_more)
        TextView tv_more;

        public View view;

        HeaderViewHolder(View view) {
            this.view=view;
            ButterKnife.bind(this, view);
        }
    }

}
