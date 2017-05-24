package com.example.zhangqi.charge.mvp.activity;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhangqi on 2016/9/28.
 */

public abstract class RxBaseActivity extends MessageActivity{

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
    protected void onDestroy() {
        super.onDestroy();
        if(mCompositeSubscription != null)
        mCompositeSubscription.unsubscribe();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
            dissDialog();
        }

    }
}
