package com.example.zhangqi.charge.global;

import android.app.Application;
import android.content.Context;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.example.zhangqi.charge.logger.MyLogger;

/**
 * Created by zhangqi on 2017/5/3.
 */

public class AppCenter extends Application{

    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;

        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY,"zh5UdmpBtOIaqt8DcHL5WP8G");
        MyLogger.init("charge");
    }
}
