package com.love.novalue.ui.mine;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.flyco.tablayout.SlidingTabLayout;
import com.love.novalue.R;
import com.love.novalue.widget.CustomScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoveOrderActivity extends AppCompatActivity {
    ArrayList<Fragment> fragments;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.tab_type)
    SlidingTabLayout tabType;
    String [] titles={"全部","待付款","待发货","待收货","售后","已完成"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love_order);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        fragments=new ArrayList<>();
        for (int i = 0; i <6 ; i++) {
            fragments.add(MyOrderFragment.newInstance(i));
        }
        tabType.setViewPager(mViewPager,titles,this,fragments);
    }
}
