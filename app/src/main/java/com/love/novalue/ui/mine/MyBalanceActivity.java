package com.love.novalue.ui.mine;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.love.novalue.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyBalanceActivity extends AppCompatActivity {
    @BindView(R.id.tab_type)
    SlidingTabLayout tab_type;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    String [] titles={"全部收益","佣金","平台分红"};
    ArrayList<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_balance);
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarColor(R.color.color_balance_bg).fitsSystemWindows(true).init();
        initView();
    }

    private void initView() {
        fragments=new ArrayList<>();
        fragments.add(new Balance1Fragment());
        fragments.add(new Balance2Fragment());
        fragments.add(new Balance3Fragment());
        tab_type.setViewPager(viewPager,titles,this,fragments);
    }
}
