package com.yayangyang.bilibili.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.yayangyang.bilibili.R;
import com.yayangyang.bilibili.ui.fragment.detail.HomeLiveFragment;
import com.yayangyang.lib_common.base.BaseFragment;
import com.yayangyang.lib_common.component.AppComponent;
import com.yayangyang.lib_common.utils.DeviceUtils;
import com.yayangyang.lib_common.utils.LogUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment {

    private String[] mTitles={"直播","推荐","追番","RNG夺冠"};
    private ArrayList<Fragment> mFragments=new ArrayList<>();

    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void attachView() {

    }

    @Override
    public void initDatas() {
        LogUtils.e("initDataswwwwwwwwwwwwwwwwwwwwww");
        mFragments.add(new HomeLiveFragment());
//        mFragments.add(new HomeLiveFragment());
//        mFragments.add(new HomeLiveFragment());
//        mFragments.add(new HomeLiveFragment());
    }

    @Override
    public void configViews() {
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                LogUtils.e("HomeFragment-getItem"+position);
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                LogUtils.e("position:"+position);
                return mTitles[position];
            }
        });
        LogUtils.e("测试");
        mTabLayout.setupWithViewPager(mViewPager);
        LogUtils.e("测试");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mFragments.clear();
    }

}
