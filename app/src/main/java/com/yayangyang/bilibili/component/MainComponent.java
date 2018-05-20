package com.yayangyang.bilibili.component;

import com.yayangyang.bilibili.ui.activity.MainActivity;
import com.yayangyang.lib_common.PerTest;
import com.yayangyang.lib_common.component.AppComponent;

import dagger.Component;

@PerTest
@Component(dependencies = AppComponent.class)
public interface MainComponent {

    MainActivity inject(MainActivity mainActivity);

}