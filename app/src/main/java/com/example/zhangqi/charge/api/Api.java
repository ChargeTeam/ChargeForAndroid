package com.example.zhangqi.charge.api;


import com.example.zhangqi.charge.bean.HttpWrapper;
import com.example.zhangqi.charge.bean.SingleParams;
import com.example.zhangqi.charge.bean.User;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by zhangqi on 2016/9/12.
 */
public interface Api {

    /**
     * 登录
     * @param phone
     * @param pwd
     * @return
     */
    @FormUrlEncoded
    @POST("user/login.action")
    Observable<HttpWrapper<User>> login(@Field("phone") String phone, @Field("password") String pwd);

    /**
     * 注册
     * @param phone
     * @param pwd
     * @return
     */
    @FormUrlEncoded
    @POST("user/register.action")
    Observable<HttpWrapper<User>> register(@Field("phone") String phone, @Field("password") String pwd);

    /**
     * 判断该手机号是否已注册
     * @param phone
     * @return
     */
    @FormUrlEncoded
    @POST("user/userIsExist.action")
    Observable<HttpWrapper<SingleParams>> userIsExist(@Field("phone") String phone);

    /**
     * 修改密码
     * @param phone
     * @param newPwd
     * @return
     */
    @FormUrlEncoded
    @POST("user/forget.action")
    Observable<HttpWrapper<SingleParams>> modifyPassword(@Field("phone") String phone, @Field("newPwd") String newPwd);

}

