package com.example.zhangqi.charge.ui.user.vericode;

import android.content.Context;

import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Laiyin on 2017/5/26.
 */

public class VeriCodePresenter implements VeriCodeContract.Presenter{

    private VeriCodeContract.View mView;
    private Context mContext;
    public CompositeSubscription mCompositeSubscription;

    public VeriCodePresenter(Context context, CompositeSubscription compositeSubscription) {
        this.mContext = context;
        mView = (VeriCodeContract.View) context;
        mView.setPresenter(this);
        mCompositeSubscription = compositeSubscription;
    }

    /**
     * 请求发送验证码
     * @param country 国家代码
     * @param phone 电话号码
     * @param listener
     */
    @Override
    public void submitVerificationCode(String country, String phone, OnSendMessageHandler listener) {
        SMSSDK.getVerificationCode("86",phone,listener);
    }

    /**
     * 提交验证码
     * @param veriCode 验证码
     * @param phone 电话号码
     */
    @Override
    public void submit(String veriCode,String phone) {
        SMSSDK.submitVerificationCode("86", phone, veriCode);
    }
}
