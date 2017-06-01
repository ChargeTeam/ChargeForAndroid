package com.example.zhangqi.charge.server.forgot_password;

import com.example.zhangqi.charge.mvp.presenter.BasePresenter;
import com.example.zhangqi.charge.mvp.view.BaseView;

/**
 * Created by Laiyin on 2017/6/1.
 */

public class ModifyPwdContract {

    interface View extends BaseView<Presenter> {
        void modifySussecc();
    }

    interface Presenter extends BasePresenter {
        void modifyPwd(String phone, String newPwd);
    }

}
