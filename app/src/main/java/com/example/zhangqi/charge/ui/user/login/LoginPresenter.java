package com.example.zhangqi.charge.ui.user.login;

import android.content.Context;

import com.example.zhangqi.charge.api.RetrofitUtils;
import com.example.zhangqi.charge.bean.HttpWrapper;
import com.example.zhangqi.charge.bean.User;
import com.example.zhangqi.charge.manager.Result;
import com.example.zhangqi.charge.manager.TransformerUtil;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Laiyin on 2017/5/25.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;
    private Context mContext;
    public CompositeSubscription mCompositeSubscription;

    public LoginPresenter(Context context, CompositeSubscription compositeSubscription) {
        this.mContext = context;
        mView = (LoginContract.View) context;
        mView.setPresenter(this);
        mCompositeSubscription = compositeSubscription;
    }

    @Override
    public void login(String phone, String password) {

        mView.showDialog("登陆...");
        /**
         * 请求
         */
        mCompositeSubscription.add(RetrofitUtils.getInstance().build()
                .login(phone, password)
                .compose(TransformerUtil.defaultScheduler())
                .subscribe(new Result<HttpWrapper<User>>() {
                    @Override
                    protected void onSuccess(HttpWrapper<User> o) {
                        mView.loginSuccess(o.getData());
                        mView.dissDialog();
                    }

                    @Override
                    protected void onFaild(HttpWrapper<User> o) {
                        if(o.getCode().equals("201")){
                            if(o.getInfo().equals("账户不存在")){
                                mView.phoneError();
                            }else if(o.getInfo().equals("密码错误")){
                                mView.pwdError();
                            }
                        }
                        mView.dissDialog();
                    }
                }));
    }
}




