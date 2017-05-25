package com.example.zhangqi.charge.mvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.zhangqi.charge.global.AppCenter;

import java.io.Serializable;
import java.util.Map;


/**
 * Created by zhangqi on 2016/11/8.
 */

public abstract class ConfigBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCenter.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppCenter.removeActivity(this);
    }


    protected <T extends Serializable> void startActivity(Class<? extends Activity> clazz, boolean isFinsh, Map<String,T> data){

        Intent intent = new Intent(this,clazz);
        if(data != null){
            for (Map.Entry<String, T> entry : data.entrySet()) {
                intent.putExtra(entry.getKey(),entry.getValue());
            }
        }

        this.startActivity(intent);

        /**
         * 是否关闭activity
         */
        if(isFinsh)
            finish();
    }


}
