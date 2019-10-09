package com.love.novalue.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.love.novalue.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeMsgActivity extends AppCompatActivity {



    @BindView(R.id.tab_msg)
    SlidingTabLayout tab_order;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    ArrayList<Fragment> mFragments;
    String []mTitles={"系统通知","订单通知"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_msg);
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarColor(R.color.white).autoDarkModeEnable(true).fitsSystemWindows(true).init();
        initView();
    }

    private void initView() {
        mFragments=new ArrayList<>();
        for (int i = 0; i <2 ; i++) {
            HomeMsgFragment orderListFragment=HomeMsgFragment.newInstance(i);
            mFragments.add(orderListFragment);
        }
        tab_order.setViewPager(viewpager,mTitles,this,mFragments);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
