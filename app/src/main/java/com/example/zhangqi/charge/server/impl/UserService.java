package com.example.zhangqi.charge.server.impl;

import com.example.zhangqi.charge.bean.User;
import com.example.zhangqi.charge.listener.HttpResultListener;
import com.example.zhangqi.charge.manager.MyComplexSub;
import com.example.zhangqi.charge.server.IUserService;
import com.example.zhangqi.charge.server.Server;

import rx.Subscription;


/**
 * Created by zhangqi on 2017/5/3.
 */

public class UserService extends Server implements IUserService{

    @Override
    public Subscription login(String phone, String password, HttpResultListener<User> sub) {
        return transformer(mApi.login(phone, password), new MyComplexSub<>(sub));
    }

}
