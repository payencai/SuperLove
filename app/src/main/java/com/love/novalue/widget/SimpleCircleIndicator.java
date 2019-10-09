package com.love.novalue.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class SimpleCircleIndicator extends View {
    Paint circlePaint;

    private int pageNum;
    private float scrollPercent = 0f;
    private int currentPosition;
    private int gapSize;


    private float radius;
    private int colorOn;
    private int colorOff;

    public SimpleCircleIndicator(Context context) {
        super(context);
        init();
    }

    public SimpleCircleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimpleCircleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        radius = DensityUtil.dip2px(getContext(),3);
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        colorOn = Color.WHITE;
        colorOff = Color.parseColor("#888888");
        gapSize =DensityUtil.dip2px(getContext(),10);
    }

    public void setSelectDotColor(int colorOn) {
        this.colorOn = colorOn;
    }

    public void setUnSelectDotColor(int colorOff) {
        this.colorOff = colorOff;
    }


    public void onPageScrolled(int position, float percent, int pixels) {
        scrollPercent = percent;
        currentPosition = position;
        invalidate();
    }

    private ViewPager viewPager;

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        if (null != viewPager) {
            pageNum = viewPager.getAdapter().getCount();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (pageNum <= 0) {
            return;
        }
        float left = (getWidth() - (pageNum - 1) * gapSize) * 0.5f;
        float height = getHeight() * 0.5f;
        circlePaint.setColor(colorOff);
        for (int i = 0; i < pageNum; i++) {
            canvas.drawCircle(left + i * gapSize, height, radius, circlePaint);
        }
        circlePaint.setColor(colorOn);
        canvas.drawCircle(left + currentPosition * gapSize + gapSize * scrollPercent, height, radius, circlePaint);
    }

    public void setPageNum(int nums){
        pageNum = nums;
    }

}

