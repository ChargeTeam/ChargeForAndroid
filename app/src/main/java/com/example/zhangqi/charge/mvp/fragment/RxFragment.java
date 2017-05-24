package com.example.zhangqi.charge.mvp.fragment;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhangqi on 2016/10/13.
 */

public class RxFragment extends MessageFragment{

    public CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public CompositeSubscription getCompositeSubscription() {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        return this.mCompositeSubscription;
    }

    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        this.mCompositeSubscription.add(s);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mCompositeSubscription != null)
            mCompositeSubscription.unsubscribe();
    }

}
