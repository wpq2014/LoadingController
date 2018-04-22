package com.wpq.loadingcontroller.demo;

import android.app.Application;

/**
 * @author wupuquan
 * @version 1.0
 * @since 2018/4/7 18:42
 */
public class MyApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        LoadingControllerUtil.init();
    }
}
