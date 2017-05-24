package com.example.zhangqi.charge.bean;

import java.io.Serializable;

/**
 * 核心包装类 封装数据集合
 * Created by zhangqi on 2016/10/8.
 */

public class HttpWrapper<T> implements Serializable {

    /**
     * code 响应码
     */
    public String code;

    /**
     * info 对于的响应信息
     */
    public String info;

    /**
     * 返回的数据类型 不确定
     */
    public T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
