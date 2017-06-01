package com.example.zhangqi.charge.ui.user.login;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dd.CircularProgressButton;
import com.example.zhangqi.charge.MainActivity;
import com.example.zhangqi.charge.R;
import com.example.zhangqi.charge.bean.User;
import com.example.zhangqi.charge.constant.UserConstant;
import com.example.zhangqi.charge.global.AppCenter;
import com.example.zhangqi.charge.mvp.activity.SimpleBaseActivity;
import com.example.zhangqi.charge.ui.user.forgot_password.UserIsExistActivity;
import com.example.zhangqi.charge.ui.user.register.PhoneInputActivity;

import butterknife.Bind;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Laiyin on 2017/5/25.
 */

public class LoginActivity extends SimpleBaseActivity implements LoginContract.View ,View.OnClickListener{

    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.iv_qq)
    ImageView ivQq;
    @Bind(R.id.iv_weixin)
    ImageView ivWeixin;
    @Bind(R.id.iv_xinlang)
    ImageView ivXinlang;
    @Bind(R.id.bt_sign_in)
    CircularProgressButton btSignIn;
    @Bind(R.id.tv_terms)
    TextView tvTerms;
    @Bind(R.id.ll)
    LinearLayout ll;
    @Bind(R.id.tv_forgot_password)
    TextView tvForgotPassword;

    private LoginContract.Presenter mPresenter;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_sign_in:
                mPresenter.login(etUsername.getText().toString(), etPassword.getText().toString());
                break;
            case R.id.iv_qq:
                break;
            case R.id.iv_weixin:
                break;
            case R.id.iv_xinlang:
                break;
            case R.id.tv_terms:
                break;
            case R.id.ll:
                startActivity(this, PhoneInputActivity.class);
                break;
            case R.id.tv_forgot_password:
                startActivity(this, UserIsExistActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initListener() {
        ll.setOnClickListener(this);
        ivQq.setOnClickListener(this);
        ivWeixin.setOnClickListener(this);
        ivXinlang.setOnClickListener(this);
        tvTerms.setOnClickListener(this);
        btSignIn.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
    }

    @Override
    protected void attachPre() {
        new LoginPresenter(this, mCompositeSubscription);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_layout_activity;
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void loginSuccess(User user) {
        AppCenter.sSpUtil.putString(UserConstant.USER_ID,user.getUserId());
        Message("登录成功");
        startActivity(this, MainActivity.class);
    }

}
