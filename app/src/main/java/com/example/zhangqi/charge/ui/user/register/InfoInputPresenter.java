package com.example.zhangqi.charge.ui.user.register;

import android.content.Context;

import com.example.zhangqi.charge.api.RetrofitUtils;
import com.example.zhangqi.charge.bean.HttpWrapper;
import com.example.zhangqi.charge.bean.User;
import com.example.zhangqi.charge.manager.Result;
import com.example.zhangqi.charge.manager.TransformerUtil;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Laiyin on 2017/5/26.
 */

public class InfoInputPresenter implements InfoInputContract.Presenter {

    private InfoInputContract.View mView;
    private Context mContext;
    public CompositeSubscription mCompositeSubscription;

    public InfoInputPresenter(Context context, CompositeSubscription compositeSubscription) {
        this.mContext = context;
        mView = (InfoInputContract.View) context;
        mView.setPresenter(this);
        mCompositeSubscription = compositeSubscription;
    }

    @Override
    public void register(String phone, String password) {
        mView.showDialog("注册...");
        /**
         * 请求
         */
        mCompositeSubscription.add(RetrofitUtils.getInstance().build()
                .register(phone, password)
                .compose(TransformerUtil.defaultScheduler())
                .subscribe(new Result<HttpWrapper<User>>() {
                    @Override
                    protected void onSuccess(HttpWrapper<User> o) {
                        mView.registerSuccess(o.getData());
                        mView.dissDialog();
                    }

                    @Override
                    protected void onFaild(HttpWrapper<User> o) {
                        mView.dissDialog();
                    }
                }));
    }
}
