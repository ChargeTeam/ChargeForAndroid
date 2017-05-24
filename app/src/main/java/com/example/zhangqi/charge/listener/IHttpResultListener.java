package com.example.zhangqi.charge.listener;

import com.example.zhangqi.charge.bean.HttpWrapper;

/**
 * Created by zhangqi on 2017/5/3.
 */

public interface IHttpResultListener<T> {

    void onSuccess(HttpWrapper<T> o);

    void onFaild(HttpWrapper<T> o);

    void onError(Throwable e);

    void onBegin();
}
