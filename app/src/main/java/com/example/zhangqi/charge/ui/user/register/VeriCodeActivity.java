package com.example.zhangqi.charge.ui.user.register;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.example.zhangqi.charge.R;
import com.example.zhangqi.charge.mvp.activity.SimpleBaseActivity;
import com.example.zhangqi.charge.ui.user.login.LoginActivity;
import com.example.zhangqi.charge.util.VerificationCodeInputUtil;

import butterknife.Bind;
import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Laiyin on 2017/5/25.
 */

public class VeriCodeActivity extends SimpleBaseActivity implements View.OnClickListener, VeriCodeContract.View {

    String phone;
    String veriCode;

    @Bind(R.id.vericode)
    VerificationCodeInputUtil vericode;
    @Bind(R.id.bt_submit)
    CircularProgressButton btSubmit;
    @Bind(R.id.tv_terms_resend)
    TextView tvTermsResend;
    @Bind(R.id.tv_terms)
    TextView tvTerms;
    @Bind(R.id.bt_sign_in)
    Button btSignIn;

    private VeriCodeContract.Presenter mPresenter;

    @Override
    protected void initView() {
        phone = this.getIntent().getExtras().getString("phone");
        /**
         * 请求发送验证码
         */
        mPresenter.submitVerificationCode("86", phone, new OnSendMessageHandler() {
            @Override
            public boolean onSendMessage(String s, String s1) {
//                Handler handler = new Handler() {
//                    @Override
//                    public void handleMessage(Message msg) {
//                        super.handleMessage(msg);
//                        if (msg.what == 1)
//                            Message("回调完成");
//                        else if (msg.what == 2)
//                            Message("提交验证码成功");
//                        else if (msg.what == 3)
//                            Message("获取验证码成功");
//                        else if (msg.what == 4)
//                            Message("返回支持发送国家验证码");
//                    }
//                };
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SMSSDK.registerEventHandler(eh);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SMSSDK.unregisterEventHandler(eh);
    }

    @Override
    protected void initListener() {
        vericode.setOnClickListener(this);
        btSubmit.setOnClickListener(this);
        tvTermsResend.setOnClickListener(this);
        tvTerms.setOnClickListener(this);
        btSignIn.setOnClickListener(this);

        vericode.setOnCompleteListener(new VerificationCodeInputUtil.Listener() {
            @Override
            public void onComplete(String content) {
                veriCode = content;
            }
        });
    }

    @Override
    protected void attachPre() {
        new VeriCodePresenter(this, mCompositeSubscription);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.vericode_layout_activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_submit:
                mPresenter.submit(veriCode, phone);
                break;
            case R.id.tv_terms_resend:
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
    public void setPresenter(VeriCodeContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void submitSuccess() {
        Message("验证成功");
        startActivity(this, InfoInputActivity.class);
    }

    EventHandler eh = new EventHandler() {

        @Override
        public void afterEvent(int event, int result, Object data) {

                        Observable.just(event)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(observer);

                        if (result == SMSSDK.RESULT_COMPLETE) {
                            //回调完成
                         //   handler.sendEmptyMessage(1);
                            if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                                //提交验证码成功
                           //     handler.sendEmptyMessage(2);
//                                Message("获取验证码成功");
                            } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                                //获取验证码成功
                            //    handler.sendEmptyMessage(3);
//                                Message("获取验证码成功");
                            //    Toast.makeText(VeriCodeActivity.this,"QQQQ",Toast.LENGTH_SHORT).show();
                            } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                                //返回支持发送验证码的国家列表
                            //    handler.sendEmptyMessage(4);
                            }
                        } else {
                            ((Throwable) data).printStackTrace();
                        }
        }
    };

    private Observer<Integer> observer = new Observer<Integer>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Integer s) {
            Toast.makeText(VeriCodeActivity.this,""+s,Toast.LENGTH_SHORT).show();
            if (s == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                //提交验证码成功
                Message("获取验证码成功");
            } else if (s == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                //获取验证码成功
                Message("获取验证码成功");
            } else if (s == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                //返回支持发送验证码的国家列表
            }
        }
    };

}
