package com.example.zhangqi.charge.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhangqi on 2017/5/3.
 */

public class MyMarker extends View{

    private Paint mPaint;
    private float mLineWidth = 1;

    public MyMarker(Context context) {
        super(context,null);
    }

    public MyMarker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public MyMarker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    public void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mLineWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#8cc350"));
    }

    private void initPath() {

    }
}
