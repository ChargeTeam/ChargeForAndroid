package com.example.zhangqi.charge.mvp.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.zhangqi.charge.R;
import com.example.zhangqi.charge.mvp.ConfigBaseActivity;
import com.example.zhangqi.charge.util.ToastUtil;


/**
 * Created by zhangqi on 2016/10/12.
 */

public abstract class MessageActivity extends ConfigBaseActivity {
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        
        initDialog();
    }

    protected void initDialog() {
        mProgressDialog = new ProgressDialog(this,
                R.style.AppTheme_Dark_Dialog);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("未知操作");
        mProgressDialog.setCancelable(false);
    }

    protected void Message(String msg){
        ToastUtil.showShort(this,msg);
    }

    protected void Message(int strId){
        ToastUtil.showShort(this,getString(strId));
    }

    protected void MessageLong(String msg){
        ToastUtil.showLong(this,msg);
    }

    protected void MessageLong(int strId){
        ToastUtil.showLong(this,getString(strId));
    }

    public String getText(TextView tv){
        return tv.getText().toString().trim() == ""?null:tv.getText().toString().trim();
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
