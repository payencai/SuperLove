package com.love.novalue.ui.home.category;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.gyf.immersionbar.ImmersionBar;
import com.liang.widget.JTabLayout;
import com.love.novalue.R;
import com.love.novalue.adapter.HomeTodayHot1Adapter;
import com.love.novalue.adapter.HomeTodayHot2Adapter;
import com.love.novalue.adapter.HomeTodayHot3Adapter;
import com.love.novalue.adapter.HomeType1Adapter;
import com.love.novalue.bean.HomeCategoryGoods;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodayHotActivity extends AppCompatActivity {
    @BindView(R.id.rv_hot1)
    RecyclerView rv_hot1;
    @BindView(R.id.rv_hot2)
    RecyclerView rv_hot2;
    @BindView(R.id.rv_hot3)
    RecyclerView rv_hot3;
    HomeTodayHot1Adapter homeTodayHot1Adapter;
    HomeTodayHot2Adapter homeTodayHot2Adapter;
    HomeTodayHot3Adapter homeTodayHot3Adapter;
    List<HomeCategoryGoods> homeCategoryGoods1;
    List<HomeCategoryGoods> homeCategoryGoods2;
    List<HomeCategoryGoods> homeCategoryGoods3;
    @BindView(R.id.jtab)
    JTabLayout jTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_hot);
        ButterKnife.bind(this);
        ImmersionBar.with(this).init();
        initView();
    }
    private void initTab() {
        jTabLayout.addTab(jTabLayout.newTab().setTitle("优选"));
        jTabLayout.addTab(jTabLayout.newTab().setTitle("拼多多"));
        jTabLayout.addTab(jTabLayout.newTab().setTitle("天猫"));
        jTabLayout.addTab(jTabLayout.newTab().setTitle("京东"));
        jTabLayout.addTab(jTabLayout.newTab().setTitle("美团"));
        jTabLayout.addTab(jTabLayout.newTab().setTitle("聚划算"));
    }
    private void initView() {
        initTab();
        homeCategoryGoods1=new ArrayList<>();
        homeCategoryGoods2=new ArrayList<>();
        homeCategoryGoods3=new ArrayList<>();
        for (int i = 0; i <3 ; i++) {
            homeCategoryGoods1.add(new HomeCategoryGoods());
        }
        for (int i = 0; i <10 ; i++) {
            homeCategoryGoods2.add(new HomeCategoryGoods());
        }
        for (int i = 0; i <10 ; i++) {
            homeCategoryGoods3.add(new HomeCategoryGoods());
        }
        homeTodayHot1Adapter=new HomeTodayHot1Adapter(homeCategoryGoods1);
        rv_hot1.setLayoutManager(new LinearLayoutManager(this));
        rv_hot1.setAdapter(homeTodayHot1Adapter);
        homeTodayHot3Adapter=new HomeTodayHot3Adapter(homeCategoryGoods3);
        rv_hot3.setLayoutManager(new LinearLayoutManager(this));
        rv_hot3.setAdapter(homeTodayHot3Adapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        homeTodayHot2Adapter=new HomeTodayHot2Adapter(homeCategoryGoods2);
        rv_hot2.setLayoutManager(linearLayoutManager);
        rv_hot2.setAdapter(homeTodayHot2Adapter);
        rv_hot1.setFocusable(false);
        rv_hot2.setFocusable(false);
        rv_hot3.setFocusable(false);
        rv_hot1.setNestedScrollingEnabled(false);
        rv_hot2.setNestedScrollingEnabled(false);
        rv_hot3.setNestedScrollingEnabled(false);
    }
}
