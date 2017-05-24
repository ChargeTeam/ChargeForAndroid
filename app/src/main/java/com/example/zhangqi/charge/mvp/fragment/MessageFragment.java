package com.example.zhangqi.charge.mvp.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.zhangqi.charge.R;
import com.example.zhangqi.charge.util.ToastUtil;


/**
 * Created by zhangqi on 2016/10/13.
 */

public class MessageFragment extends ConfigFragment {

    public Context mContext;
    private ProgressDialog mProgressDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mProgressDialog = new ProgressDialog(mContext,
                R.style.AppTheme_Dark_Dialog);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("未知操作");
        mProgressDialog.setCancelable(false);
    }

    protected void showMessage(String msg){
        ToastUtil.showShort(mContext,msg);
    }

    protected void showMessageLong(String msg){
        ToastUtil.showShort(mContext,msg);
    }

    protected void showMessage(int strId){
        ToastUtil.showShort(mContext,getString(strId));
    }

    public String getText(TextView tv){
        return tv.getText().toString().trim() == ""?null:tv.getText().toString().trim();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void showDialog(String tips) {
        mProgressDialog.setMessage(tips);
        if(!mProgressDialog.isShowing())
            mProgressDialog.show();
    }

    public void dissDialog() {
        if(mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }
}
