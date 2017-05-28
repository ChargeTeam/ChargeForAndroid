package com.example.zhangqi.charge.ui.user.register;

import com.example.zhangqi.charge.bean.User;
import com.example.zhangqi.charge.mvp.presenter.BasePresenter;
import com.example.zhangqi.charge.mvp.view.BaseView;

/**
 * Created by Laiyin on 2017/5/26.
 */

public class InfoInputContract {

    interface View extends BaseView<Presenter> {

        void registerSuccess(User user);

    }

    interface Presenter extends BasePresenter {

        void register(String phone,String password);

    }
}
