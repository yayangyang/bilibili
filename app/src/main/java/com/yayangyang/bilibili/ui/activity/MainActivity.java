package com.yayangyang.bilibili.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.yayangyang.bilibili.Bean.HomeLive;
import com.yayangyang.bilibili.R;
import com.yayangyang.bilibili.component.DaggerMainComponent;
import com.yayangyang.bilibili.ui.adapter.HomeLiveAdapter;
import com.yayangyang.bilibili.ui.adapter.HomeLiveMultiItemQuickAdapter;
import com.yayangyang.bilibili.ui.fragment.HomeFragment;
import com.yayangyang.lib_common.app.BaseApplication;
import com.yayangyang.lib_common.base.BaseActivity;
import com.yayangyang.lib_common.component.AppComponent;
import com.yayangyang.lib_common.component.DaggerAppComponent;
import com.yayangyang.lib_common.decoration.SpaceItemDecoration;
import com.yayangyang.lib_common.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    private Fragment[] mFragments=new Fragment[4];

    @BindView(R.id.fl_content)
    FrameLayout fl_content;
    @BindView(R.id.rg_group)
    RadioGroup rg_group;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initToolBar() {

    }



    @Override
    public void initDatas() {
        changeFragment(0);
    }

    @Override
    public void configViews() {

        rg_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.home){
                    ToastUtils.showToast("comic");
                    changeFragment(0);
                }else if(checkedId==R.id.zone){
                    ToastUtils.showToast("news");
                    changeFragment(1);
                }else if(checkedId==R.id.dynamic){
                    ToastUtils.showToast("light_novel");
                    changeFragment(2);
                }else if(checkedId==R.id.vip_purchase){
                    ToastUtils.showToast("mine");
                    changeFragment(3);
                }
            }
        });
    }

    public void changeFragment(int num){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(mFragments[num]==null){
            if(num==0){
                mFragments[num]=new HomeFragment();
                transaction.add(R.id.fl_content,mFragments[num]);
            }else if(num==1){
//                mFragments[num]=new ZoneFragment();
            }else if(num==2){
//                mFragments[num]=new DynamicFragment();
            }else if(num==3){
//                mFragments[num]=new MessageFragment();
            }
//            transaction.add(R.id.fl_content,mFragments[num]);
        }
        for(int i=0;i<mFragments.length;i++){
            if(mFragments[i]!=null&&i!=num){
                //若不隐藏其他fragment时后显示的fragment在上面(才可交互),
                //白色部分(不是view的部分)变透明可看到之前的fragment动画在执行
                transaction.hide(mFragments[i]);
            }else if(i==num){
                transaction.show(mFragments[i]);
            }
        }
        transaction.commit();
    }

}
