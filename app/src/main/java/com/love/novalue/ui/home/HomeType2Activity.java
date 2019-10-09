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

public class HomeType2Activity extends AppCompatActivity {


    @BindView(R.id.tab_order)
    SlidingTabLayout tab_order;
    @BindView(R.id.vp_order)
    ViewPager vp_order;
    ArrayList<Fragment> mFragments;
    String []mTitles={"今日上新","大家在买","女装内衣","家居百货","童玩母婴","电器百科"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_type2);
        ButterKnife.bind(this);
        ImmersionBar.with(this).init();
        initView();
    }

    private void initView() {
        mFragments=new ArrayList<>();
        for (int i = 0; i <6 ; i++) {
            HomeType2Fragment orderListFragment=HomeType2Fragment.newInstance(i);
            mFragments.add(orderListFragment);
        }
        tab_order.setViewPager(vp_order,mTitles,this,mFragments);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
