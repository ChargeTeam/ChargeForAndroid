package com.example.zhangqi.charge.api;


import com.example.zhangqi.charge.constant.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by zhangqi on 2016/9/12.
 */
public class RetrofitUtils {

    private static Retrofit sRetrofit;
    private OkHttpClient mOkHttpClient;
    private int defaultTime = 30;
    private static Api mApi;

    private RetrofitUtils(){}

    public static class Hide{
        public static RetrofitUtils sRetrofitUtil = new RetrofitUtils();
    }

    public static RetrofitUtils getInstance() {
        return Hide.sRetrofitUtil;
    }

    public Api build(){
        if(mOkHttpClient == null){
            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(defaultTime, TimeUnit.SECONDS)
                    .readTimeout(defaultTime, TimeUnit.SECONDS)
                    .writeTimeout(defaultTime, TimeUnit.SECONDS)
                    .build();
        }

        if(sRetrofit == null){
            sRetrofit = new Retrofit.Builder().baseUrl(Constant.BASEURL)
                    .client(mOkHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }

        if(mApi == null){
            mApi = sRetrofit.create(Api.class);
        }
        return mApi;
    }

    public Api build(int timeOut){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(timeOut, TimeUnit.SECONDS)
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.BASEURL)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(Api.class);
    }

}
