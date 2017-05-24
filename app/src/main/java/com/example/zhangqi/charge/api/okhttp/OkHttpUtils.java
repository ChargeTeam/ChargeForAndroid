package com.example.zhangqi.charge.api.okhttp;

import com.example.zhangqi.charge.global.AppCenter;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by zhangqi on 2017/2/13.
 */

public class OkHttpUtils {

    private OkHttpClient mOkHttpClient;
    private int defaultTime = 30;
    private File httpCacheDirectory = new File(AppCenter.sContext.getCacheDir(), "responses");
    public static class Hide{
        public static OkHttpUtils mOkHttpUtils = new OkHttpUtils();
    }

    public static OkHttpUtils getInstance() {
        return Hide.mOkHttpUtils;
    }
    /**
     * 配置okhttp
     */
    public OkHttpClient setOkHttp(int time){
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        if(time != -1){
            defaultTime = time;
        }

        mOkHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(REWRITE_RESPONSE_INTERCEPTOR)
                //没网络时的拦截器
                .addInterceptor(REWRITE_RESPONSE_INTERCEPTOR_OFFLINE)
                .connectTimeout(defaultTime, TimeUnit.SECONDS)
                .readTimeout(defaultTime, TimeUnit.SECONDS)
                .writeTimeout(defaultTime, TimeUnit.SECONDS)
//                .cache(cache)
                .build();
        return mOkHttpClient;
    }

    private static final int TIMEOUT_CONNECT = 60 * 60 * 24 * 7; //5秒
    private static final int TIMEOUT_DISCONNECT = 60 * 60 * 24 * 7; //7天

    private final Interceptor REWRITE_RESPONSE_INTERCEPTOR = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            //获取retrofit @headers里面的参数，参数可以自己定义，在本例我自己定义的是cache，跟@headers里面对应就可以了
            String cache = chain.request().header("cache");
            okhttp3.Response originalResponse = chain.proceed(chain.request());
            String cacheControl = originalResponse.header("Cache-Control");
            //如果cacheControl为空，就让他TIMEOUT_CONNECT秒的缓存，本例是5秒，方便观察。注意这里的cacheControl是服务器返回的
            if (cacheControl == null) {
                //如果cache没值，缓存时间为TIMEOUT_CONNECT，有的话就为cache的值
                if (cache == null || "".equals(cache)) {
                    cache = TIMEOUT_CONNECT + "";
                }
                originalResponse = originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=" + cache)
                        .build();
                return originalResponse;
            } else {
                return originalResponse;
            }
        }
    };

    private final Interceptor REWRITE_RESPONSE_INTERCEPTOR_OFFLINE = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            //离线的时候为7天的缓存。
            if (true) { //TODO
                request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale="+TIMEOUT_DISCONNECT)
                        .build();
            }
            return chain.proceed(request);
        }
    };
}
