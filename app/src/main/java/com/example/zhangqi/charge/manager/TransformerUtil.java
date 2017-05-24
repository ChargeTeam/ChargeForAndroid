package com.example.zhangqi.charge.manager;

import com.example.zhangqi.charge.bean.HttpWrapper;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhangqi on 2017/5/3.
 */

public class TransformerUtil {
    public static <T> Observable.Transformer<HttpWrapper<T>, HttpWrapper<T>> defaultSchedulers() {
        return tObservable -> tObservable
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Observable.Transformer<T, T> defaultScheduler() {
        return tObservable -> tObservable
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Observable.Transformer<T, T> timeSchedulers(int time) {
        return tObservable -> tObservable
                .delay(time, TimeUnit.SECONDS)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
