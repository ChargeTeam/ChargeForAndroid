package com.example.zhangqi.charge.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.zhangqi.charge.R;


/**
 * Created by zhangqi on 2017/5/8.
 */

public class PullView extends ViewGroup {

    private static final String TAG = "swipe";
    private View mHead;
    private int mLayoutContentHeight;

    public PullView(Context context) {
        this(context,null);
    }

    public PullView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PullView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        
        initView();
    }

    private void initView() {
        mHead = LayoutInflater.from(getContext()).inflate(R.layout.head,null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++){
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        mHead.setLayoutParams(params);
        addView(mHead);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mLayoutContentHeight = 0;
        for (int i = 0; i < getChildCount(); i++){
            View child = getChildAt(i);
            if (child == mHead) { // 头视图隐藏在顶端
                child.layout(0, 0 - child.getMeasuredHeight(), child.getMeasuredWidth(), 0);
            }
//            else if (child == mFooter) { // 尾视图隐藏在layout所有内容视图之后
//                child.layout(0, mLayoutContentHeight, child.getMeasuredWidth(), mLayoutContentHeight + child.getMeasuredHeight());
//            }
            else { // 内容视图根据定义(插入)顺序,按由上到下的顺序在垂直方向进行排列
                child.layout(0, mLayoutContentHeight, child.getMeasuredWidth(), mLayoutContentHeight + child.getMeasuredHeight());
                mLayoutContentHeight += child.getMeasuredHeight();
            }
        }
    }

    private int mLastMoveY;
    private int mScrollY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastMoveY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int dy = mLastMoveY - y;
                mScrollY += dy;
                scrollBy(0, Math.abs(mScrollY));
                break;
        }

        mLastMoveY = y;
        return true;
    }
}
