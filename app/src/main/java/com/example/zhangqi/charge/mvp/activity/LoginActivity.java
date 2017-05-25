package com.example.zhangqi.charge.mvp.activity;

import android.os.Bundle;

import static com.example.zhangqi.charge.R.layout.login_layout_activity;

/**
 * Created by Laiyin on 2017/5/25.
 */

public class LoginActivity extends SimpleBaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(login_layout_activity);
    }

    @Override
    protected void attachPre() {

    }

    @Override
    protected int getLayoutId() {
        return login_layout_activity;
    }
}
