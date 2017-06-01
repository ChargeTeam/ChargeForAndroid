package com.example.zhangqi.charge.ui.user.forgot_password;

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

public class UserIsExistPresenter implements UserIsExistContract.Presenter {

    private Context mContext;
    private UserIsExistContract.View mView;
    private CompositeSubscription mCompositeSubscription;

    public UserIsExistPresenter(Context context, CompositeSubscription compositeSubscription) {
        this.mContext = context;
        mView = (UserIsExistContract.View) context;
        mView.setPresenter(this);
        mCompositeSubscription = compositeSubscription;
    }

    @Override
    public void userIsExist(String phone) {
        mView.showDialog("正在验证用户是否存在...");

        mCompositeSubscription.add(RetrofitUtils.getInstance().build()
                .userIsExist(phone)
                .compose(TransformerUtil.defaultScheduler())
                .subscribe(new Result<HttpWrapper<SingleParams>>() {
                    @Override
                    protected void onSuccess(HttpWrapper<SingleParams> o) {
                        /**
                         * 用户不存在
                         */
                        mView.userIsNotExist();
                        mView.dissDialog();
                    }

                    @Override
                    protected void onFaild(HttpWrapper<SingleParams> o) {
                        if (o.getCode().equals("201")) {
                            /**
                             * 用户存在
                             */
                            mView.userIsExist();
                            mView.dissDialog();
                        }
                    }
                }));
    }
}
