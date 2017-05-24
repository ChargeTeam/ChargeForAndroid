package com.example.zhangqi.charge.server;


import com.example.zhangqi.charge.bean.User;
import com.example.zhangqi.charge.listener.HttpResultListener;

import rx.Subscription;

/**
 * Created by zhangqi on 2017/5/3.
 */

public interface IUserService {

     Subscription login(String phone, String password, HttpResultListener<User> sub);

}
