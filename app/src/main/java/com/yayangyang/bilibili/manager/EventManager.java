package com.yayangyang.bilibili.manager;

import com.yayangyang.bilibili.Bean.support.SubEvent;

import org.greenrobot.eventbus.EventBus;

public class EventManager {

//    public static void refreshCollectionList() {
//        EventBus.getDefault().post(new RefreshCollectionListEvent());
//    }
//
//    public static void refreshCollectionIcon() {
//        EventBus.getDefault().post(new RefreshCollectionIconEvent());
//    }

    public static void refreshSubCategory(String minor, String type) {
        EventBus.getDefault().post(new SubEvent(minor, type));
    }

}
