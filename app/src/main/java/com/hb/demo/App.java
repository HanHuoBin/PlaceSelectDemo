package com.hb.demo;

import android.app.Application;

import com.hb.demo.utils.STUtils;

/**
 * Created by tech60 on 2017/8/10.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 工具类初始化
        STUtils.init(this);
    }
}
