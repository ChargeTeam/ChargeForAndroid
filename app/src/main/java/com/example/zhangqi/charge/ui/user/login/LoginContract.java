package com.example.zhangqi.charge.ui.user.login;

import com.example.zhangqi.charge.bean.User;
import com.example.zhangqi.charge.mvp.presenter.BasePresenter;
import com.example.zhangqi.charge.mvp.view.BaseView;

/**
 * Created by Laiyin on 2017/5/25.
 */

public class LoginContract {

    interface View extends BaseView<Presenter>{

        void loginSuccess(User user);
    }

    interface Presenter extends BasePresenter{

        void login(String phone,String password);

    }

}
