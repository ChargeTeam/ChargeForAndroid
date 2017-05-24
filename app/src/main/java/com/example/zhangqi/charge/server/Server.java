package com.example.zhangqi.charge.server;

import com.example.zhangqi.charge.api.Api;
import com.example.zhangqi.charge.api.RetrofitUtils;
import com.example.zhangqi.charge.manager.TransformerUtil;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by zhangqi on 2017/5/3.
 */

public abstract class Server {

    protected static final Api mApi = RetrofitUtils.getInstance().build();

    protected <T> Subscription transformer(Observable<T> ob, Subscriber<T> sub){
        return ob.compose(TransformerUtil.timeSchedulers(10)).subscribe(sub);
    }
}
