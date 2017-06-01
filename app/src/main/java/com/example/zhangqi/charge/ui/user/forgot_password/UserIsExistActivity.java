package com.example.zhangqi.charge.ui.user.forgot_password;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zhangqi.charge.R;
import com.example.zhangqi.charge.mvp.activity.SimpleBaseActivity;
import com.example.zhangqi.charge.ui.user.login.LoginActivity;
import com.example.zhangqi.charge.ui.user.vericode.VeriCodeActivity;

import at.markushi.ui.CircleButton;
import butterknife.Bind;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Laiyin on 2017/5/31.
 */

public class UserIsExistActivity extends SimpleBaseActivity implements UserIsExistContract.View,View.OnClickListener{

    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.bt_next)
    CircleButton btNext;
    @Bind(R.id.tv_terms)
    TextView tvTerms;
    @Bind(R.id.bt_sign_in)
    Button btSignIn;

    Bundle bundle;

    private UserIsExistContract.Presenter mPresenter;

    @Override
    protected void initData() {
        bundle = new Bundle();
    }

    @Override
    protected void initListener() {
        btNext.setOnClickListener(this);
        tvTerms.setOnClickListener(this);
        btSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_next:
                if (etPhone.getText().toString().equals("")) {
                    Message("手机号不能为空!");
                } else {
                    mPresenter.userIsExist(etPhone.getText().toString());
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

    @Override
    public void setPresenter(UserIsExistContract.Presenter presenter) {
        mPresenter=checkNotNull(presenter);
    }

    @Override
    public void userIsExist() {
        bundle.putString("phone", etPhone.getText().toString());
        bundle.putString("context","UserIsExistActivity");
        startActivityWithData(this, VeriCodeActivity.class, bundle);
    }

    @Override
    public void userIsNotExist() {
        Message("用户不存在");
    }

    @Override
    protected void attachPre() {
        new UserIsExistPresenter(this,mCompositeSubscription);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.register_layout_activity;
    }
}
