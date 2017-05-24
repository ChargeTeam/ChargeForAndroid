package com.example.zhangqi.charge.mvp.presenter.impl;

import android.content.Context;

import com.example.zhangqi.charge.mvp.view.BaseView;

import rx.subscriptions.CompositeSubscription;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by zhangqi on 2016/9/28.
 */

public abstract class BasePresenterImpl<V extends BaseView>{

    public Context mContext;

    public V mView;

    public CompositeSubscription mCompositeSubscription;

    public BasePresenterImpl(Context context, V view, CompositeSubscription compositeSubscription){
        mContext = context.getApplicationContext();
        mView = checkNotNull(view);
        mView.setPresenter(this);
        mCompositeSubscription = compositeSubscription;
    }
}
