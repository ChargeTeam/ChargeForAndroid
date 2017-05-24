package com.example.zhangqi.charge.push;

import android.content.Context;

import com.baidu.android.pushservice.PushMessageReceiver;
import com.example.zhangqi.charge.logger.Logger;

import java.util.List;

/**
 * Created by zhangqi on 2017/5/17.
 */

public class MyPushReceiver extends PushMessageReceiver {

    @Override
    public void onBind(Context context, int i, String s, String s1, String s2, String s3) {

    }

    @Override
    public void onUnbind(Context context, int i, String s) {

    }

    @Override
    public void onSetTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onDelTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onListTags(Context context, int i, List<String> list, String s) {

    }

    @Override
    public void onMessage(Context context, String s, String s1) {
        Logger.i(s);
    }

    @Override
    public void onNotificationClicked(Context context, String s, String s1, String s2) {
        Logger.i(s+"asdad");
    }

    @Override
    public void onNotificationArrived(Context context, String s, String s1, String s2) {

    }
}
