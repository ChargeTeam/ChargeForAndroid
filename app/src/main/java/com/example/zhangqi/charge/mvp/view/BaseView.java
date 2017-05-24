package com.example.zhangqi.charge.mvp.view;

/**
 * Created by zhangqi on 2016/9/28.
 */

public interface BaseView<P> {

    void setPresenter(P presenter);

    void showDialog(String tips);

    void dissDialog();
}
