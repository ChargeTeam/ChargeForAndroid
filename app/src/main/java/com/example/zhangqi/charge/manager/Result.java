package com.example.zhangqi.charge.manager;


import com.example.zhangqi.charge.R;
import com.example.zhangqi.charge.bean.HttpWrapper;
import com.example.zhangqi.charge.constant.Constant;
import com.example.zhangqi.charge.global.AppCenter;
import com.example.zhangqi.charge.logger.Logger;
import com.example.zhangqi.charge.util.ToastUtil;
import com.example.zhangqi.charge.util.UtilsCollection;
import com.google.gson.Gson;

import rx.Subscriber;


/**
 * 统一处理error  complete页面
 * 并提供回调
 * Created by zhangqi on 2016/9/23.
 */

public abstract class Result<T extends HttpWrapper> extends Subscriber<T> {

    @Override
    public void onError(Throwable e) {
        Logger.i(e.toString());
        UtilsCollection.errorUtil(e);
        onMyError(e);
        onFaildOrError(null,e,false);
    }

    @Override
    public void onCompleted() {
        onBegin();
    }

    @Override
    public void onNext(T wrapper) {
        Logger.i(new Gson().toJson(wrapper));
        if(Constant.MD5ERROR.equals(wrapper.code)){
            ToastUtil.showShort(AppCenter.sContext, AppCenter.sContext.getString(R.string.md5error));
        }

        /**
         * 单独的适配
         * SuccessCode  --  onSuccess()
         * 其它错误 --  onFaild()
         */
        if(!Constant.SUCCESS_CODE.equals(wrapper.code)){
            if(Constant.ERROR_CODE.equals(wrapper.code)){
                ToastUtil.showShort(AppCenter.sContext,wrapper.info);
            }

            onFaildOrError(wrapper,null,true);
            onFaild(wrapper);
        }else if(Constant.SUCCESS_CODE.equals(wrapper.code)){
            onSuccess(wrapper);
        }
    }

    /**
     * 返回的结果 是successCode 的情况下
     * @param o
     */
    protected abstract void onSuccess(T o);

    /**
     * 返回的code 不是成功的情况下
     * @param o
     */
    protected void onFaild(T o){}

    protected void onMyError(Throwable e){}

    protected void onFaildOrError(T o,Throwable e,boolean status){}
    /**
     * 在开始的时候
     */
    protected void onBegin(){}

}
