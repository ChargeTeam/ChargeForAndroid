package com.example.zhangqi.charge.mvp.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhangqi.charge.R;

import butterknife.ButterKnife;

/**
 * Created by zhangqi on 2016/10/13.
 */

public abstract class SimpleBaseFragment extends RxFragment{

    public ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        attachPre();
        View view = inflater.inflate(getLayoutId(), null);
        ButterKnife.bind(this,view);

        mProgressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("未知操作");
        mProgressDialog.setCancelable(false);

        initView(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initDataOnStart();

        initListener();
    }

    protected void initDataOnStart() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected void initView(View view) {}

    protected void initListener() {}

    protected void initData() {}

    protected void attachPre(){}

    protected abstract int getLayoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}
