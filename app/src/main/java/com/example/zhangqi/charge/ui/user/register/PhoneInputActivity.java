package com.example.zhangqi.charge.server.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zhangqi.charge.R;
import com.example.zhangqi.charge.mvp.activity.SimpleBaseActivity;
import com.example.zhangqi.charge.server.login.LoginActivity;
import com.example.zhangqi.charge.server.vericode.VeriCodeActivity;

import at.markushi.ui.CircleButton;
import butterknife.Bind;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Laiyin on 2017/5/25.
 */

public class PhoneInputActivity extends SimpleBaseActivity implements View.OnClickListener, PhoneInputContract.View {

    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.bt_next)
    CircleButton btNext;
    @Bind(R.id.tv_terms)
    TextView tvTerms;
    @Bind(R.id.bt_sign_in)
    Button btSignIn;

    Bundle bundle;

    private PhoneInputContract.Presenter mPresenter;

    @Override
    protected void initData() {
        bundle = new Bundle();
    }

    @Override
    protected void attachPre() {
        new PhoneInputPresenter(this, mCompositeSubscription);
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
    public void setPresenter(PhoneInputContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void registerState() {
        bundle.putString("phone", etPhone.getText().toString());
        bundle.putString("context","PhoneInputActivity");

        startActivityWithData(this, VeriCodeActivity.class,bundle);
    }
}
