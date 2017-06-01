package com.example.zhangqi.charge.ui.user.forgot_password;

import android.content.Context;

import com.example.zhangqi.charge.api.RetrofitUtils;
import com.example.zhangqi.charge.bean.HttpWrapper;
import com.example.zhangqi.charge.bean.SingleParams;
import com.example.zhangqi.charge.manager.Result;
import com.example.zhangqi.charge.manager.TransformerUtil;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Laiyin on 2017/6/1.
 */

public class ModifyPwdPresenter implements ModifyPwdContract.Presenter {

    private Context mContext;
    private ModifyPwdContract.View mView;
    private CompositeSubscription mCompositeSubscription;

    public ModifyPwdPresenter(Context context,CompositeSubscription compositeSubscription){
        this.mContext=context;
        mView=(ModifyPwdContract.View)context;
        mView.setPresenter(this);
        mCompositeSubscription=compositeSubscription;
    }

    @Override
    public void modifyPwd(String phone, String newPwd) {
        mView.showDialog("正在修改密码...");
        mCompositeSubscription.add(RetrofitUtils.getInstance().build()
                .modifyPassword(phone,newPwd)
                .compose(TransformerUtil.defaultScheduler())
                .subscribe(new Result<HttpWrapper<SingleParams>>() {
                    @Override
                    protected void onSuccess(HttpWrapper<SingleParams> o) {
                        mView.modifySussecc();
                        mView.dissDialog();
                    }

                    @Override
                    protected void onFaild(HttpWrapper<SingleParams> o) {
                        mView.dissDialog();
                    }
                }));
    }
}
