package com.example.administrator.demo.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/12/28.
 */
public class HomeViewPaper extends ViewPager {

    public boolean isTouchVp=false;//是否触摸了vp

    public HomeViewPaper(Context context) {
        super(context);
    }

    public HomeViewPaper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isTouchVp=true;
                break;
            case MotionEvent.ACTION_UP:
                isTouchVp=false;
                break;
        }
        return super.dispatchTouchEvent(event);
    }

}
