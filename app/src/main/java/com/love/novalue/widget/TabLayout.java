package com.love.novalue.widget;

import android.animation.Animator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.love.novalue.R;

public class TabLayout extends HorizontalScrollView implements View.OnClickListener,
        ViewPager.OnPageChangeListener, Animator.AnimatorListener {
    private static final int TRANSLATION_DURATION = 250;
    private static final int SCALE_DURATION = 100;
    private RelativeLayout mContent;
    private LinearLayout mTabContainer;
    private ImageView mIndicator;

    //当tab在屏幕居中显示时的X坐标
    private int mTabCenterLocationX;
    //当前view左右滚动的最大距离
    private int mViewScrollMaxDistance;

    //tab的相关参数
    private int mTabWidth;
    private int mTabTextSize;
    private int mTabTextPaddingTop;
    private int mTabTextPaddingLeft;
    private int mTabTextPaddingRight;
    private int mTabTextPaddingBottom;
    private int mTabBackgroundResId;
    private ColorStateList mTabTextColorSelector;
    private int mTabSelectedPos;

    //指示器相关的参数
    private int mIndicatorWidth;
    private int mIndicatorHeight;
    private int mIndicatorImageId;
    private int mIndicatorMarginBottom;

    //tab选中的监听事件
    private OnTabSelectedListener mListener;

    //显示的数据源
    private String[] mItems;

    //关联的viewpager
    private ViewPager mViewPager;

    public TabLayout(Context context) {
        super(context);
        init();
    }

    public TabLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //初始化所有的view
    private void init() {
        setHorizontalScrollBarEnabled(false);
        setOverScrollMode(OVER_SCROLL_NEVER);
        setBackgroundColor(Color.GREEN);

        initParam();

        mContent = new RelativeLayout(getContext());
        mTabContainer = new LinearLayout(getContext());
        mIndicator = new ImageView(getContext());

        mTabContainer.setOrientation(LinearLayout.HORIZONTAL);

        mContent.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mTabContainer.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));


        mContent.addView(mTabContainer);
        mContent.addView(mIndicator);

        addView(mContent);
    }

    //初始化默认的参数
    private void initParam() {
        mTabTextSize = (int)getResources().getDimension(R.dimen.tab_layout_tab_text_default_text_size);
        mTabTextPaddingLeft = (int)getResources().getDimension(R.dimen.tab_layout_tab_text_default_padding_left);
        mTabTextPaddingRight = (int)getResources().getDimension(R.dimen.tab_layout_tab_text_default_padding_right);
        mIndicatorWidth = (int)getResources().getDimension(R.dimen.tab_layout_indicator_default_width);
        mIndicatorHeight = (int)getResources().getDimension(R.dimen.tab_layout_indicator_default_height);

        mIndicatorMarginBottom = (int)getResources().getDimension(R.dimen.tab_layout_indicator_default_margin_bottom);
        mIndicatorImageId = R.drawable.tab_layout_indicator_default_image;
    }

    //tab的点击事件
    @Override
    public void onClick(View v) {
        int index = (int)v.getTag();
        if(index != mTabSelectedPos) {
            setTabSelected(index);
            tabCenterScroll();
            updateIndicatorLocation(TRANSLATION_DURATION);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //viewPager的page选中事件
    @Override
    public void onPageSelected(int position) {
        if(position != mTabSelectedPos) {
            setTabSelected(position);
            tabCenterScroll();
            updateIndicatorLocation(TRANSLATION_DURATION);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        mIndicator.animate().scaleX(1f).setDuration(SCALE_DURATION).start();
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    //初始化view滚动是需要的一些参数
    private void initScrollParam() {
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        mTabCenterLocationX = (metrics.widthPixels - mTabWidth) >> 1;
        int viewWidth = 0;
        if(mItems != null && mItems.length > 0 || mTabWidth != 0) {
            viewWidth = mItems.length * mTabWidth;
        }
        mViewScrollMaxDistance = viewWidth - metrics.widthPixels > 0 ? viewWidth - metrics.widthPixels : 0;
    }

    //更新整个view
    private void updateView() {
        updateTabView();
        initScrollParam();
        updateIndicatorView();
        setIndicatorMarginBottom();
        //不延时没有效果
        postDelayed(new Runnable() {
            @Override
            public void run() {
                tabCenterScroll();
            }
        }, 200);
        updateIndicatorLocation(0);
    }

    //更新下方指示器view
    private void updateIndicatorView() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(mIndicatorWidth, mIndicatorHeight);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mIndicator.setLayoutParams(layoutParams);
        mIndicator.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mIndicator.setImageResource(mIndicatorImageId);
    }

    //更新指示器的位置（
    private void updateIndicatorLocation(final int duration) {
        int[] startLocation = new int[2];
        int[] endLocation = new int[2];
        mIndicator.getLocationInWindow(startLocation);
        mTabContainer.getChildAt(mTabSelectedPos).getLocationInWindow(endLocation);
        float distance = endLocation[0] - startLocation[0] + (mTabWidth - mIndicatorWidth) / 2;
        mIndicator.animate()
                .translationXBy(distance)
                .setDuration(duration)
                .start();
        mIndicator.animate()
                .scaleX(2f)
                .setDuration(SCALE_DURATION)
                .setListener(this)
                .start();
    }

    //设置指示器和底部的间距
    private void setIndicatorMarginBottom() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)mIndicator.getLayoutParams();
        layoutParams.setMargins(0, 0, 0, mIndicatorMarginBottom);
    }

    //将选中的tab尽量的居中显示
    private void tabCenterScroll() {
        int tabCurrLocationX = mTabSelectedPos * mTabWidth;
        int scrollDistance = mTabCenterLocationX - tabCurrLocationX;
        int[] viewCurrentLocation = new int[2];
        getLocationOnScreen(viewCurrentLocation);
        int viewScrollToX = viewCurrentLocation[0] - scrollDistance;
        if(viewScrollToX < 0) {
            viewScrollToX = 0;
        } else if(viewScrollToX > mViewScrollMaxDistance) {
            viewScrollToX = mViewScrollMaxDistance;
        }
        smoothScrollTo(viewScrollToX, viewCurrentLocation[1]);
    }

    //更新所有的Tab
    private void updateTabView() {
        if(mViewPager != null) {
            relevanceViewPager();
        }
        if(mItems == null || mItems.length == 0) return;
        initTabWidth();
        addNewTabs();
        setTabPadding();
        setTabTextColor();
        setTabTextSize();
        setTabBackground();
        setTabSelected(mTabSelectedPos);
    }

    //关联viewPager
    private void relevanceViewPager() {
        PagerAdapter adapter = mViewPager.getAdapter();
        if(adapter == null) {
            throw new NullPointerException("viewPager adapter is null");
        }
        int length = adapter.getCount();
        mItems = new String[length];
        for(int i = 0; i < length; i++) {
            mItems[i] = adapter.getPageTitle(i).toString();
        }
        mViewPager.addOnPageChangeListener(this);
        mViewPager.getAdapter().registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                updateView();
            }
        });
    }

    //初始化Tab的宽度
    private void initTabWidth() {
        mTabWidth = 0;
        TextView view = new TextView(getContext());
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view.setPadding(mTabTextPaddingLeft, mTabTextPaddingTop, mTabTextPaddingRight, mTabTextPaddingBottom);
        if(mTabBackgroundResId != 0) {
            view.setBackgroundResource(mTabBackgroundResId);
        }
        for(String item : mItems) {
            view.setText(item);
            view.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTabTextSize);
            view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int width = view.getMeasuredWidth();
            if(width > mTabWidth) {
                mTabWidth = width;
            }
        }
    }

    //添加新的tab
    private void addNewTabs() {
        mTabContainer.removeAllViews();
        int index = 0;
        for(String item : mItems) {
            TextView view = new TextView(getContext());
            view.setText(item);
            view.setGravity(Gravity.CENTER);
            view.setTag(index);
            view.setLayoutParams(new LinearLayout.LayoutParams(mTabWidth, ViewGroup.LayoutParams.MATCH_PARENT));
            view.setOnClickListener(this);
            mTabContainer.addView(view);
            index++;
        }
    }

    //设置所有tab的文字大小
    private void setTabTextSize() {
        int tabNum = mTabContainer.getChildCount();
        for(int i = 0; i < tabNum; i++) {
            TextView view = (TextView)mTabContainer.getChildAt(i);
            view.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTabTextSize);
        }
    }

    //设置所有tab的内边距
    private void setTabPadding() {
        int tabNum = mTabContainer.getChildCount();
        for(int i = 0; i < tabNum; i++) {
            View view = mTabContainer.getChildAt(i);
            view.setPadding(mTabTextPaddingLeft, mTabTextPaddingTop, mTabTextPaddingRight, mTabTextPaddingBottom);
        }
    }

    //设置所有tab的字体颜色
    private void setTabTextColor() {
        int tabNum = mTabContainer.getChildCount();
        if(mTabTextColorSelector != null) {
            for(int i = 0; i < tabNum; i++) {
                TextView view = (TextView)mTabContainer.getChildAt(i);
                view.setTextColor(mTabTextColorSelector);
            }
        } else {
            mTabTextColorSelector = getResources().getColorStateList(R.color.color_333);
            for(int i = 0; i < tabNum; i++) {
                TextView view = (TextView)mTabContainer.getChildAt(i);
                view.setTextColor(mTabTextColorSelector);
            }
        }
    }

    //设置tab选中
    private void setTabSelected(int pos) {
        if(pos >= mTabContainer.getChildCount()) {
            throw new IllegalArgumentException("the index of crossing the line; max index:" + (mTabContainer.getChildCount() - 1) + " you index;" + pos);
        }
        mTabContainer.getChildAt(mTabSelectedPos).setSelected(false);
        mTabContainer.getChildAt(pos).setSelected(true);
        mTabSelectedPos = pos;
        if(mViewPager != null) {
            mViewPager.setCurrentItem(mTabSelectedPos);
        }
        if(mListener != null) {
            mListener.onTabSelected(mTabContainer.getChildAt(mTabSelectedPos), mTabSelectedPos);
        }
    }

    //设置所有tab的背景
    private void setTabBackground() {
        if(mTabBackgroundResId != 0) {
            for(int i = 0; i < mTabContainer.getChildCount(); i++) {
                View view = mTabContainer.getChildAt(i);
                view.setBackgroundResource(mTabBackgroundResId);
            }
        } else {
            for(int i = 0; i < mTabContainer.getChildCount(); i++) {
                View view = mTabContainer.getChildAt(i);
                int[] attrs = new int[]{
                        android.R.attr.selectableItemBackground
                };
                TypedArray ta = getContext().obtainStyledAttributes(attrs);
                Drawable drawable = ta.getDrawable(0);
                ta.recycle();
                view.setBackground(drawable);
            }
        }
    }

    /**
     * 设置数据源（关联ViewPager和该方法二选一）
     *
     * @param itemData 数据源
     */
    public void setItemData(String[] itemData) {
        mItems = itemData;
        updateView();
    }

    /**
     * 设置选中的tab
     *
     * @param position 对应的位置
     */
    public void setSelection(int position) {
        setTabSelected(position);
        tabCenterScroll();
        updateIndicatorLocation(TRANSLATION_DURATION);
    }

    /**
     * 设置指示器的图片文件
     *
     * @param imageResId 图片文件的资源ID
     */
    public void setIndicatorImageRes(int imageResId) {
        mIndicatorImageId = imageResId;
        updateIndicatorView();
    }

    /**
     * 设置指示器的下边距
     *
     * @param marginBottom 下边距
     */
    public void setIndicatorMarginBottom(int marginBottom) {
        mIndicatorMarginBottom = marginBottom;
        setIndicatorMarginBottom();
    }

    /**
     * 设置tab的内边距
     *
     * @param left   左边
     * @param top    顶部
     * @param right  右边
     * @param bottom 底部
     */
    public void setTabPadding(int left, int top, int right, int bottom) {
        mTabTextPaddingLeft = left;
        mTabTextPaddingTop = top;
        mTabTextPaddingRight = right;
        mTabTextPaddingBottom = bottom;
        setTabPadding();
    }

    /**
     * 设置tab的文字大小
     *
     * @param textSize 字体大小（单位px）
     */
    public void setTabTextSize(int textSize) {
        mTabTextSize = textSize;
        setTabTextSize();
    }

    /**
     * 设置tab选中时的监听
     *
     * @param listener 回调方法
     */
    public void setOnTabSelectedListener(OnTabSelectedListener listener) {
        mListener = listener;
    }

    /**
     * 设置tab字体的颜色（需要一个选择器来区分选中和未选中）
     *
     * @param colorStateList 颜色选择器
     */
    public void setTextColor(ColorStateList colorStateList) {
        mTabTextColorSelector = colorStateList;
        setTabTextColor();
    }

    /**
     * 设置tab的背景
     *
     * @param resId 背景的资源id
     */
    public void setTabBackground(int resId) {
        mTabBackgroundResId = resId;
        setTabBackground();
    }

    /**
     * 关联viewPager,需实现ViewPager的Adapter中getPageTitle方法
     *
     * @param view 要关联的viewPager
     */
    public void setWithViewPager(ViewPager view) {
        mViewPager = view;
        updateView();
    }

    public interface OnTabSelectedListener {
        void onTabSelected(View view, int position);
    }
}

