package com.yayangyang.lib_common.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yayangyang.lib_common.Receiver.NetworkChangeReceiver;
import com.yayangyang.lib_common.Receiver.TimeChangeReceiver;
import com.yayangyang.lib_common.base.CrashHandler;
import com.yayangyang.lib_common.component.AppComponent;
import com.yayangyang.lib_common.delegate.AppDelegate;
import com.yayangyang.lib_common.delegate.AppLifecycles;
import com.yayangyang.lib_common.utils.AppUtils;
import com.yayangyang.lib_common.utils.SharedPreferencesUtil;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

public class BaseApplication extends Application implements App {

    private static AppLifecycles mAppDelegate;
    private static BaseApplication sInstance;

    public static BaseApplication getsInstance() {
        return sInstance;
    }

    /**
     * 这里会在 {@link BaseApplication#onCreate} 之前被调用,可以做一些较早的初始化
     * 常用于 MultiDex 以及插件化框架的初始化
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        if (mAppDelegate == null)
            mAppDelegate = new AppDelegate(base);
            mAppDelegate.attachBaseContext(base);

        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance=this;
        if (mAppDelegate != null)
            mAppDelegate.onCreate(this);

        if (true) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化

        AppUtils.init(this);
        CrashHandler.getInstance().init(this);
        initPrefs();
        initReceiver();
        initRetrofitUrl();
    }

    /**
     * 在模拟环境中程序终止时会被调用
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mAppDelegate != null)
            mAppDelegate.onTerminate(this);
    }

    //--------------------------------------------------------------------
    public AppComponent getAppComponent() {
        return ((App) mAppDelegate).getAppComponent();
    }

    private void initReceiver() {
        //注册监听网络状态改变广播
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filter.addAction("android.net.wifi.STATE_CHANGE");
        registerReceiver(NetworkChangeReceiver.getInstance(), filter);

        //注册监听系统时间广播
        filter = new IntentFilter();
        filter.addAction(Intent.ACTION_TIME_TICK);
        registerReceiver(TimeChangeReceiver.getInstance(), filter);
    }

    /**
     * 初始化SharedPreference
     */
    protected void initPrefs() {
        SharedPreferencesUtil.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
    }

    private void initRetrofitUrl() {
        RetrofitUrlManager.getInstance().setDebug(true);
        //将每个 BaseUrl 进行初始化,运行时可以随时改变 DOMAIN_NAME 对应的值,从而达到切换 BaseUrl 的效果
//        RetrofitUrlManager.getInstance().putDomain(GITHUB_DOMAIN_NAME, APP_GITHUB_DOMAIN);
    }

}
