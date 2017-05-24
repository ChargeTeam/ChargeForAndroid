package com.example.zhangqi.charge.manager;


import com.example.zhangqi.charge.bean.HttpWrapper;
import com.example.zhangqi.charge.listener.HttpResultListener;
import com.example.zhangqi.charge.logger.Logger;

import rx.Subscriber;


/**
 * 统一处理error  complete页面
 * 并提供回调
 * Created by zhangqi on 2016/9/23.
 */

public class MyComplexSub<T extends HttpWrapper> extends Subscriber<T> {

    private static final String SUCCESS = "200";
    private static final String ERROR = "201";
    private static final String MD5 = "202";

    public HttpResultListener mHttpResultListener;

    public MyComplexSub(HttpResultListener listener){
        mHttpResultListener = listener;
    }

    @Override
    public void onError(Throwable e) {
        Logger.i(e.toString());
        onMyError(e);
    }

    @Override
    public void onCompleted() {
        onBegin();
    }

    @Override
    public void onNext(T wrapper) {

        Logger.i(wrapper.getCode());
        if(MD5.equals(wrapper.code)){

        }

        /**
         * 单独的适配
         * SuccessCode  --  onSuccess()
         * 其它错误 --  onFaild()
         */
        if(!SUCCESS.equals(wrapper.code)){
            if(ERROR.equals(wrapper.code)){

            }

            onFaild(wrapper);
        }else if(SUCCESS.equals(wrapper.code)){
            onSuccess(wrapper);
            mHttpResultListener.onSuccess(wrapper);
        }
    }

    /**
     * 返回的结果 是successCode 的情况下
     * @param o
     */
    protected void onSuccess(T o){}

    /**
     * 返回的code 不是成功的情况下
     * @param o
     */
    protected void onFaild(T o){}

    protected void onMyError(Throwable e){}
    /**
     * 在开始的时候
     */
    protected void onBegin(){}

}
