package com.example.zhangqi.charge.global;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.example.zhangqi.charge.logger.Logger;
import com.example.zhangqi.charge.logger.MyLogger;
import com.example.zhangqi.charge.util.SpUtil;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by zhangqi on 2017/5/3.
 */

public class AppCenter extends Application{

    public static Context sContext;
    public static SpUtil sSpUtil;

    private static LinkedList<Activity> mActivities;
    @Override
    public void onCreate() {
        super.onCreate();

        sContext = getApplicationContext();
        sSpUtil = new SpUtil(this,"config");
        mActivities = new LinkedList<>();
        /**
         * 百度推送
         */
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY,"zh5UdmpBtOIaqt8DcHL5WP8G");

        /**
         * logger初始化
         */
        MyLogger.init("charge");

        /**
         * 腾讯bugly集成
         */
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        CrashReport.initCrashReport(getApplicationContext(), "15c60b6324", false);
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    public static void addActivity(Activity activity){
        mActivities.addFirst(activity);
        Logger.i("进入"+activity.getLocalClassName());
    }

    public static void removeActivity(Activity activity){
        mActivities.remove(activity);
    }
}
