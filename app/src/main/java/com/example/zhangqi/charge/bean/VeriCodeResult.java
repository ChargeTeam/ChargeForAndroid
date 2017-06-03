package com.example.zhangqi.charge.bean;

/**
 * Created by Laiyin on 2017/6/1.
 */

public class VeriCodeResult {

    private int event;
    private int result;
    private Object data;


    public VeriCodeResult(int event, int result, Object data) {
        this.event = event;
        this.result = result;
        this.data = data;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
