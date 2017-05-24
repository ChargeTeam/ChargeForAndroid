package com.example.zhangqi.charge.mvp.activity;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.zhangqi.charge.R;
import com.example.zhangqi.charge.mvp.presenter.BasePresenter;

import butterknife.ButterKnife;

/**
 * Created by zhangqi on 2016/9/28.
 */

public abstract class BaseActivity<P extends BasePresenter> extends RxBaseActivity{

    protected P mPresenter;
//    @Bind(R.id.toolbar)
//    public Toolbar mToolbar;
    protected ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachPre();


        setContentView(getLayoutId());
        ButterKnife.bind(this);
        /**
         * 5.0以下 状态栏着色
         */
        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT){
//            UtilsCollection.setColor(this, Color.parseColor(Constant.TOOLBAR_COLOR),0);
        }

        mProgressDialog = new ProgressDialog(this,
                R.style.AppTheme_Dark_Dialog);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("未知操作");
        mProgressDialog.setCancelable(false);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
        initListener();
    }

    protected void initView() {
//        mToolbar.setTitle(getString(setTitle()));
//        mToolbar.setNavigationIcon(R.drawable.back_select);
//        mToolbar.setTitleTextColor(Color.WHITE);
//        setSupportActionBar(mToolbar);
//        //关键下面两句话，设置了回退按钮，及点击事件的效果
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    protected abstract void attachPre();

    protected abstract int getLayoutId();

    /**
     * 设置toolbar的标题
     * @return
     */
    public int setTitle(){
        return R.string.unknow;
    }

    protected void initData() {}

    protected void initListener() {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
