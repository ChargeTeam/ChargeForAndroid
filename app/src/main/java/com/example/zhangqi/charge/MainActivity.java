package com.example.zhangqi.charge;

import com.example.zhangqi.charge.api.RetrofitUtils;
import com.example.zhangqi.charge.bean.HttpWrapper;
import com.example.zhangqi.charge.bean.User;
import com.example.zhangqi.charge.manager.Result;
import com.example.zhangqi.charge.manager.TransformerUtil;
import com.example.zhangqi.charge.mvp.activity.SimpleBaseActivity;

public class MainActivity extends SimpleBaseActivity {

    @Override
    protected void attachPre() {
        RetrofitUtils.getInstance().build()
                .login("13270665702","123")
                .compose(TransformerUtil.defaultScheduler())
                .subscribe(new Result<HttpWrapper<User>>() {
                    @Override
                    protected void onSuccess(HttpWrapper<User> o) {

                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
