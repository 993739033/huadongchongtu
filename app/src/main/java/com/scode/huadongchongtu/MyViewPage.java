package com.scode.huadongchongtu;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by 知らないのセカイ on 2017/5/11.
 */

public class MyViewPage extends LinearLayout {
    public MyViewPage(Context context) {
        super(context);
    }

    public MyViewPage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewPage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
//    private View view1,view2,view3;
    private Scroller mscroller;
    private int slop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mscroller = new Scroller(getContext());
        smoothScrollTo(2000,0);
        myVelocity=VelocityTracker.obtain();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i=0;i<getChildCount();i++) {
            View view = getChildAt(i);
            view.measure(widthMeasureSpec,heightMeasureSpec);
            childWidth=getMeasuredWidth();
        }
    }
    private int childWidth;
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mscroller.computeScrollOffset()){
            scrollTo(mscroller.getCurrX(), mscroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            for (int i=0;i<getChildCount();i++) {
                View view = getChildAt(i);
                view.layout(i * r, 0, (i + 1) * r, b);
            }
        }
    }
    private void smoothScrollTo(int destx,int desty){
      int x=getScrollX();
        int delta =destx-x;
//        mscroller.startScroll(x,0,delta,0,5000);
        invalidate();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                index = (getScrollX() + childWidth / 2) / childWidth;
                oldX=ev.getRawX();
                oldY=ev.getRawY();
                if (!mscroller.isFinished()) {
                    mscroller.abortAnimation();
                    return true;
                }
                else{
                    return false;
                }
            case MotionEvent.ACTION_MOVE:
                int x= (int) ev.getRawX();
                int y= (int) ev.getRawY();
                if (Math.abs(x-oldX)>slop&&Math.abs(y-oldY)<Math.abs(x-oldX)){
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }
    private  float oldX=0,oldY=0,velocityX=0;
    private int index=0;
    private  VelocityTracker myVelocity;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float deltaX;
        myVelocity.addMovement(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:

               float x=event.getRawX();
                deltaX= (oldX-x);
                oldX=x;
                int newDis = (int) (getScrollX() +deltaX);
                if (deltaX<0){
                    newDis = Math.max(0, newDis);
                }else{
                    newDis = Math.min((getChildCount()-1) * childWidth, newDis);
                }
                scrollBy(newDis-getScrollX(), 0);

                break;
            case MotionEvent.ACTION_UP:
                myVelocity.computeCurrentVelocity(1000);
                velocityX =  myVelocity.getXVelocity();
                myVelocity.clear();
                if (Math.abs(velocityX) > 50) {
                    index=velocityX>0?index-1:index+1;
                } else {
                    index= (getScrollX()+childWidth/2)/childWidth;
                }
                index = Math.max(Math.min(index, getChildCount() - 1), 0);
                int del=index*childWidth-getScrollX();
                mscroller.startScroll(getScrollX(),0,del,0,400);
                invalidate();
                break;
        }
        return true;
    }

}
