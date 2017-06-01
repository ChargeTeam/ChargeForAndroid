package com.example.zhangqi.charge.ui.user.forgot_password;

import com.example.zhangqi.charge.mvp.presenter.BasePresenter;
import com.example.zhangqi.charge.mvp.view.BaseView;

/**
 * Created by Laiyin on 2017/5/31.
 */

public class UserIsExistContract {

    interface View extends BaseView<Presenter>{
        /**
         * 用户已存在
         */
        void userIsExist();

        /**
         * 用户不存在
         */
        void userIsNotExist();

    }

    interface Presenter extends BasePresenter{

        void userIsExist(String phone);

    }

}
