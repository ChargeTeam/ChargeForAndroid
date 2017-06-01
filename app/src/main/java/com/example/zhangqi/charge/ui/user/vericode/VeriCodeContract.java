package com.example.zhangqi.charge.ui.user.vericode;

import com.example.zhangqi.charge.mvp.presenter.BasePresenter;
import com.example.zhangqi.charge.mvp.view.BaseView;

import cn.smssdk.OnSendMessageHandler;

/**
 * Created by Laiyin on 2017/5/26.
 */

public class VeriCodeContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        void submit(String veriCode,String phone);

        void submitVerificationCode(String country, String phone, OnSendMessageHandler listener);

    }
}
