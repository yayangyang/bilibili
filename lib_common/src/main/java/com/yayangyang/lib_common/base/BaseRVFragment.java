package com.yayangyang.lib_common.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yayangyang.lib_common.R;
import com.yayangyang.lib_common.R2;
import com.yayangyang.lib_common.utils.LogUtils;
import com.yayangyang.lib_common.utils.NetworkUtils;
import com.yayangyang.lib_common.utils.ToastUtils;
import com.yayangyang.lib_common.view.CustomLoadMoreView;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public abstract class BaseRVFragment<T1 extends BaseContract.BasePresenter, T2,K extends BaseViewHolder> extends BaseFragment
        implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener {

    @Inject
    protected T1 mPresenter;

    @BindView(R2.id.recyclerview)
    protected RecyclerView mRecyclerView;
    @BindView(R2.id.sw)
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected View inflate,network,footView;
    protected BaseQuickAdapter<T2,K> mAdapter;

//    protected int start = 0;
//    protected int limit = 20;

    protected int page=0;

    /**
     * [此方法不可再重写]
     */
    @Override
    public void attachView() {
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    protected void initAdapter(boolean refreshable, boolean loadmoreable) {
        Log.e("initAdapter","initAdapter");
        if (mAdapter != null) {
            mAdapter.setOnItemClickListener(this);
            inflate = View.inflate(mContext, R.layout.common_empty_view, null);
            inflate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRefresh();
                }
            });
            network = View.inflate(mContext, R.layout.common_net_error_view, null);
            network.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRefresh();
                }
            });
//            mAdapter.setEmptyView(inflate);
            if (loadmoreable&&mRecyclerView!=null) {
                LogUtils.e("loadmoreable"+loadmoreable);
                mAdapter.setOnLoadMoreListener(this,mRecyclerView);
                mAdapter.setLoadMoreView(new CustomLoadMoreView());
                mRecyclerView.setAdapter(mAdapter);
//                mAdapter.setMore(R.layout.common_more_view, this);
//                mAdapter.setNoMore(R.layout.common_nomore_view);
            }else{
                mAdapter.bindToRecyclerView(mRecyclerView);//这里mRecyclerView可能为空
            }
            if (refreshable && mSwipeRefreshLayout != null) {
                mSwipeRefreshLayout.setOnRefreshListener(this);
            }
        }
        if (mRecyclerView != null) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//            mRecyclerView.setItemDecoration(ContextCompat.getColor(this, R.color.common_divider_narrow), 1, 0, 0);
            Log.e("bindToRecyclerView","bindToRecyclerView");
//            mAdapter.disableLoadMoreIfNotFullPage();//使用后有时不能加载更多(目前对这个方法还不大理解)
        }else{
            Log.e("mRecyclerView","null");
        }
    }

    protected void initAdapter(Class<? extends BaseQuickAdapter<T2,K>> clazz, int id, ArrayList arrayList, boolean refreshable, boolean loadmoreable) {
        mAdapter = (BaseQuickAdapter) createInstance(clazz,id,arrayList);
        initAdapter(refreshable, loadmoreable);
    }

    public Object createInstance(Class<?> cls,int id, ArrayList arrayList) {
        Object obj;
        try {
            Constructor c1 = cls.getDeclaredConstructor(int.class, List.class);
            c1.setAccessible(true);
            obj = c1.newInstance(id,arrayList);
        } catch (Exception e) {
            obj = null;
        }
        return obj;
    }

    @Override
    public void onRefresh() {
        Log.e("onRefresh","onRefresh");
        page=0;
    }

    @Override
    public void onLoadMoreRequested() {

    }

    protected void loaddingError() {
        LogUtils.e("wwwwwwwwwww");
        if(mAdapter.getData().size()==0){
            LogUtils.e("getItemCount:"+mAdapter.getItemCount());
            mAdapter.setEmptyView(network);
        }
        if(!NetworkUtils.isAvailable(mContext)){//之前模拟器没网络也返回true,应该是当时模拟器有点问题
            ToastUtils.showToast("网络异常");
            mAdapter.loadMoreFail();
        }
        mSwipeRefreshLayout.setRefreshing(false);
        dismissDialog();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) mPresenter.detachView();
        //view(应该是onCreateView方法我们返回的view)销毁时置空,防止内存泄漏
        if (mAdapter!=null) mAdapter=null;
    }
}
