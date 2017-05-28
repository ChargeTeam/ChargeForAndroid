package com.example.zhangqi.charge.ui.user.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zhangqi.charge.R;
import com.example.zhangqi.charge.mvp.activity.SimpleBaseActivity;
import com.example.zhangqi.charge.ui.user.login.LoginActivity;

import at.markushi.ui.CircleButton;
import butterknife.Bind;

/**
 * Created by Laiyin on 2017/5/25.
 */

public class PhoneInputActivity extends SimpleBaseActivity implements View.OnClickListener {

    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.bt_next)
    CircleButton btNext;
    @Bind(R.id.tv_terms)
    TextView tvTerms;
    @Bind(R.id.bt_sign_in)
    Button btSignIn;

    Bundle bundle;

    @Override
    protected void initData() {
        bundle=new Bundle();
    }

    @Override
    protected void attachPre() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.register_layout_activity;
    }

    @Override
    protected void initListener() {
        btNext.setOnClickListener(this);
        tvTerms.setOnClickListener(this);
        btSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_next:
                if(etPhone.getText().toString().equals("")){
                    Message("手机号不能为空!");
                }else {
                    bundle.putString("phone", etPhone.getText().toString());
                    startActivityWithData(this,VeriCodeActivity.class,bundle);
                }
                break;
            case R.id.tv_terms:
                break;
            case R.id.bt_sign_in:
                startActivity(this, LoginActivity.class);
                break;
            default:
                break;
        }
    }
}
