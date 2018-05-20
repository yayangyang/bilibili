package com.yayangyang.lib_common.component;

import android.app.Application;
import android.content.Context;

import com.yayangyang.lib_common.api.BilibiliApi;
import com.yayangyang.lib_common.module.AppModule;
import com.yayangyang.lib_common.module.ClientModule;
import com.yayangyang.lib_common.module.GlobalConfigModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ClientModule.class, GlobalConfigModule.class})
public interface AppComponent {

    Context getContext();

    BilibiliApi getBilibiliApi();

//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        Builder application(Application application);
//        Builder globalConfigModule(GlobalConfigModule globalConfigModule);
//        AppComponent build();
//    }
}