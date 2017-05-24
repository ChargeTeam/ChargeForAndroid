package com.example.zhangqi.charge;

import android.os.Bundle;

import com.example.zhangqi.charge.bean.HttpWrapper;
import com.example.zhangqi.charge.bean.User;
import com.example.zhangqi.charge.listener.HttpResultListener;
import com.example.zhangqi.charge.logger.Logger;
import com.example.zhangqi.charge.mvp.activity.SimpleBaseActivity;
import com.example.zhangqi.charge.server.IUserService;
import com.example.zhangqi.charge.server.impl.UserService;

import static com.example.zhangqi.charge.R.layout.activity_main;

public class MainActivity extends SimpleBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        IUserService userService = new UserService();
        mCompositeSubscription.add(userService.login("13270665700", "123", new HttpResultListener<User>() {
            @Override
            public void onSuccess(HttpWrapper<User> o) {
                Logger.i(o.getData().getPassword());
            }
        }));
    }

    @Override
    protected void attachPre() {

    }

    @Override
    protected int getLayoutId() {
        return activity_main;
    }
}
