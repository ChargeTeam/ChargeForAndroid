package com.example.zhangqi.charge.ui.user.forgot_password;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zhangqi.charge.R;
import com.example.zhangqi.charge.mvp.activity.SimpleBaseActivity;
import com.example.zhangqi.charge.ui.user.login.LoginActivity;

import at.markushi.ui.CircleButton;
import butterknife.Bind;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Laiyin on 2017/6/1.
 */

public class ModifyPwdActivity extends SimpleBaseActivity implements View.OnClickListener,ModifyPwdContract.View {

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

    private ModifyPwdContract.Presenter mPresenter;

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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_next:
                if(etPassword.getText().toString().equals(etConfirmPassword.getText().toString())){
                    mPresenter.modifyPwd(phone,etPassword.getText().toString());
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
    public void setPresenter(ModifyPwdContract.Presenter presenter) {
        mPresenter=checkNotNull(presenter);
    }

    @Override
    public void modifySussecc() {
        Message("密码修改成功");
        startActivity(this, LoginActivity.class);
    }

    @Override
    protected void attachPre() {
        new ModifyPwdPresenter(this,mCompositeSubscription);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.register_info_layout_activity;
    }
}
