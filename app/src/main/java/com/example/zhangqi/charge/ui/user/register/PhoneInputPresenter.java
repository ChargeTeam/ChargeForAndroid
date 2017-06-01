package com.example.zhangqi.charge.ui.user.register;

import android.content.Context;

import com.example.zhangqi.charge.api.RetrofitUtils;
import com.example.zhangqi.charge.bean.HttpWrapper;
import com.example.zhangqi.charge.bean.SingleParams;
import com.example.zhangqi.charge.manager.Result;
import com.example.zhangqi.charge.manager.TransformerUtil;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Laiyin on 2017/5/31.
 */

public class PhoneInputPresenter implements PhoneInputContract.Presenter {

    private PhoneInputContract.View mView;
    private Context mContext;
    private CompositeSubscription mCompositeSubscription;

    public PhoneInputPresenter(Context context, CompositeSubscription compositeSubscription) {
        this.mContext = context;
        mView = (PhoneInputContract.View) context;
        mView.setPresenter(this);
        mCompositeSubscription = compositeSubscription;
    }

    @Override
    public void userIsExist(String phone) {
        mView.showDialog("正在验证手机是否已注册...");
        mCompositeSubscription.add(RetrofitUtils.getInstance().build()
                .userIsExist(phone)
                .compose(TransformerUtil.defaultScheduler())
                .subscribe(new Result<HttpWrapper<SingleParams>>() {
                    @Override
                    protected void onSuccess(HttpWrapper<SingleParams> o) {
                        mView.registerState();
                        mView.dissDialog();
                    }

                    @Override
                    protected void onFaild(HttpWrapper<SingleParams> o) {
                        mView.dissDialog();
                    }
                }));
    }
}
