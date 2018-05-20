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
import com.yayangyang.lib_common.utils.NetworkUtils;
import com.yayangyang.lib_common.utils.ToastUtils;
import com.yayangyang.lib_common.utils.ViewUtils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public abstract class BaseRVActivity<T,K extends BaseViewHolder> extends BaseActivity
        implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R2.id.recyclerview)
    protected RecyclerView mRecyclerView;
    @BindView(R2.id.sw)
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected View inflate,network,loddingView;
    protected BaseQuickAdapter<T,K> mAdapter;

    protected int start = 0;
    protected int limit = 20;

    protected int page=0;

    protected float y=0;
    protected int screenHeight = 0;
    protected int keyHeight = 0;

    protected void initAdapter(boolean refreshable, boolean loadmoreable) {
        Log.e("initAdapter","initAdapter");
        if (mAdapter != null) {
            mAdapter.setHeaderAndEmpty(true);//设置空布局时头布局和脚布局都显示
            mAdapter.setOnItemClickListener(this);
            inflate = View.inflate(this, R.layout.common_empty_view, null);
            inflate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRefresh();
                }
            });
            network = View.inflate(this, R.layout.common_net_error_view, null);
            network.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRefresh();
                }
            });
            loddingView=View.inflate(this,R.layout.activity_lodding,null);
            mAdapter.setEmptyView(loddingView);
            if (loadmoreable) {
                mAdapter.setOnLoadMoreListener(this,mRecyclerView);
            }
            if (refreshable && mSwipeRefreshLayout != null) {
                mSwipeRefreshLayout.setOnRefreshListener(this);
            }else if(mSwipeRefreshLayout!=null){
                mSwipeRefreshLayout.setEnabled(false);
            }
        }
        if (mRecyclerView != null) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//            mRecyclerView.setItemDecoration(ContextCompat.getColor(this, R.color.common_divider_narrow), 1, 0, 0);
            mRecyclerView.setAdapter(mAdapter);
        }else{
            Log.e("mRecyclerView","null");
        }
    }

    protected void initAdapter(Class<? extends BaseQuickAdapter<T,K>> clazz, int id, ArrayList arrayList, boolean refreshable, boolean loadmoreable) {
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
    public void onLoadMoreRequested() {

    }

    @Override
    public void onRefresh() {
        Log.e("onRefresh","onRefresh");
        page = 0;
    }

    protected void loaddingError(){
        if(mAdapter.getData().size()==0){
            mAdapter.setEmptyView(network);
            ViewUtils.setEmptyViewLayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.MATCH_PARENT,mAdapter.getEmptyView());
        }
        if(!NetworkUtils.isAvailable(this)){
            ToastUtils.showToast("网络异常");
            mAdapter.loadMoreFail();
        }
        mSwipeRefreshLayout.setRefreshing(false);
        dismissDialog();
    }

}
