package com.example.zhangqi.charge.ui.user.register;

import com.example.zhangqi.charge.mvp.presenter.BasePresenter;
import com.example.zhangqi.charge.mvp.view.BaseView;

/**
 * Created by Laiyin on 2017/5/31.
 */

public class PhoneInputContract {

    interface View extends BaseView<Presenter>{
        /**
         * 判断该手机号是否已注册
         */
        void registerState();
    }

    interface Presenter extends BasePresenter{

        void userIsExist(String phone);
    }

}
