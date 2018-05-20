package com.yayangyang.lib_common.integration;

import android.content.Context;

/**
 * 用来管理网络请求层,以及数据缓存层,以后可能添加数据库请求层
 */
public interface IRepositoryManager {

    /**
     * 根据传入的 Class 获取对应的 Retrofit service
     *
     * @param service
     * @param <T>
     * @return
     */
    <T> T getService(Class<T> service);

}
