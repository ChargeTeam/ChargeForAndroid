package com.example.zhangqi.charge.mvp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;

/**
 * Created by zhangqi on 2016/9/28.
 */

public abstract class SimpleBaseActivity extends RxBaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachPre();
        
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        initView();
        
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initDataOnStart();
        initListener();
    }

    protected abstract void attachPre();

    protected abstract int getLayoutId();

    protected void initView() {}

    protected void initData() {}

    protected void initDataOnStart() {}

    protected void initListener() {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);

    }

    protected void startActivity(Context context,Class<?> cls){
        Intent intent=new Intent(context,cls);
        startActivity(intent);
        finish();
    }

    protected void startActivityWithData(Context context,Class<?> cls,Bundle bundle){
        Intent intent=new Intent(context,cls);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

}
