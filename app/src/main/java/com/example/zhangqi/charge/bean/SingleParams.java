package com.example.zhangqi.charge.bean;

/**
 * Created by zhangqi on 2017/2/13.
 */

public class SingleParams {

    /**
     * 验证码
     */
    private String verify;
    private String userId;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

}
