package com.example.zhangqi.charge.listener;

import com.example.zhangqi.charge.bean.HttpWrapper;

/**
 * Created by zhangqi on 2017/5/3.
 */

public abstract class HttpResultListener<T> implements IHttpResultListener<T>{

    @Override
    public void onBegin() {

    }

    @Override
    public void onFaild(HttpWrapper<T> o) {

    }

    @Override
    public void onSuccess(HttpWrapper<T> o) {

    }

    @Override
    public void onError(Throwable e) {

    }
}
