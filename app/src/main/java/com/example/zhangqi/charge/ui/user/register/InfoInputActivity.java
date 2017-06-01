package com.example.zhangqi.charge.ui.user.register;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zhangqi.charge.MainActivity;
import com.example.zhangqi.charge.R;
import com.example.zhangqi.charge.bean.User;
import com.example.zhangqi.charge.constant.UserConstant;
import com.example.zhangqi.charge.global.AppCenter;
import com.example.zhangqi.charge.mvp.activity.SimpleBaseActivity;
import com.example.zhangqi.charge.ui.user.login.LoginActivity;

import at.markushi.ui.CircleButton;
import butterknife.Bind;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Laiyin on 2017/5/25.
 */

public class InfoInputActivity extends SimpleBaseActivity implements InfoInputContract.View, View.OnClickListener {

    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @Bind(R.id.bt_next)
    CircleButton btNext;
    @Bind(R.id.tv_terms)
    TextView tvTerms;
    @Bind(R.id.bt_sign_in)
    Button btSignIn;

    private String phone;

    private InfoInputContract.Presenter mPresenter;

    @Override
    protected void initView() {
        phone = this.getIntent().getExtras().getString("phone");
    }

    @Override
    protected void initListener() {
        btNext.setOnClickListener(this);
        tvTerms.setOnClickListener(this);
        btSignIn.setOnClickListener(this);
    }

    @Override
    protected void attachPre() {
        new InfoInputPresenter(this, mCompositeSubscription);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.register_info_layout_activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_next:
                if(etPassword.getText().toString().equals(etConfirmPassword.getText().toString())){
                    mPresenter.register(phone,etPassword.getText().toString());
                }else {
                    Message("两次密码输入不相同！");
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
    public void setPresenter(InfoInputContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void registerSuccess(User user) {
        AppCenter.sSpUtil.putString(UserConstant.USER_ID, user.getUserId());
        Message("注册成功");
        startActivity(this, MainActivity.class);
    }
}
