package com.love.novalue.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.gyf.immersionbar.ImmersionBar;
import com.liang.widget.JTabLayout;
import com.love.novalue.R;
import com.love.novalue.adapter.HomeSearchGoodsAdapter;
import com.love.novalue.adapter.HotTagAdapter;
import com.love.novalue.adapter.SearchTagAdapter;
import com.love.novalue.bean.HomeSearchGoods;
import com.love.novalue.bean.HotWord;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;




import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeSearchActivity extends AppCompatActivity {
    HomeSearchGoodsAdapter homeSearchGoodsAdapter;
    @BindView(R.id.fl_history)
    TagFlowLayout flHistory;
    @BindView(R.id.fl_hot)
    TagFlowLayout fl_hot;
    List<String> historyList;
    List<HotWord> hotWords;
    HotTagAdapter hotTagAdapter;
    SearchTagAdapter searchTagAdapter;
    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.ll_empty)
    RelativeLayout ll_empty;
    @BindView(R.id.ll_history)
    LinearLayout ll_history;
    @BindView(R.id.jtab)
    JTabLayout jTabLayout;
    @BindView(R.id.rv_goods)
    RecyclerView rv_goods;
    List<HomeSearchGoods> homeSearchGoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_search);
        ButterKnife.bind(this);
        ImmersionBar.with(this).autoDarkModeEnable(true).statusBarColor(R.color.color_f5).fitsSystemWindows(true).init();
        initView();

    }

    private void initTab() {
        jTabLayout.addTab(jTabLayout.newTab().setTitle("淘宝"));
        jTabLayout.addTab(jTabLayout.newTab().setTitle("拼多多"));
        jTabLayout.addTab(jTabLayout.newTab().setTitle("天猫"));
        jTabLayout.addTab(jTabLayout.newTab().setTitle("京东"));
        jTabLayout.addTab(jTabLayout.newTab().setTitle("美团"));
        jTabLayout.addTab(jTabLayout.newTab().setTitle("聚划算"));
    }
    private void initGoods(){
        homeSearchGoods=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            homeSearchGoods.add(new HomeSearchGoods());
        }
        homeSearchGoodsAdapter=new HomeSearchGoodsAdapter(homeSearchGoods);
        rv_goods.setLayoutManager(new LinearLayoutManager(this));
        rv_goods.setAdapter(homeSearchGoodsAdapter);
    }
    private void intiHistory(){
        historyList = new ArrayList<>();
        hotWords = new ArrayList<>();
        historyList.add("电视");
        historyList.add("转换线");
        historyList.add("电动剃须刀");
        historyList.add("洗衣夜");
        historyList.add("高品质耳机");
        historyList.add("户外运动装备");
        historyList.add("手机");
        hotWords.add(new HotWord("洗护用品"));
        hotWords.add(new HotWord("儿童服装"));
        hotWords.add(new HotWord("洗衣夜"));
        hotWords.add(new HotWord("洗护用品"));
        hotWords.add(new HotWord("电动剃须刀"));
        hotWords.add(new HotWord("户外运动装备"));
        hotWords.add(new HotWord("手机"));
        hotTagAdapter = new HotTagAdapter(hotWords);
        searchTagAdapter = new SearchTagAdapter(historyList);
        flHistory.setAdapter(searchTagAdapter);
        fl_hot.setAdapter(hotTagAdapter);
        fl_hot.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                ll_content.setVisibility(View.VISIBLE);
                ll_history.setVisibility(View.GONE);
                return false;
            }
        });
        flHistory.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                ll_content.setVisibility(View.VISIBLE);
                ll_history.setVisibility(View.GONE);
                return false;
            }
        });
    }
    private void initView() {
        initTab();
        intiHistory();
        initGoods();

    }

    @OnClick({R.id.tv_search})
    void OnClcik(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                ll_content.setVisibility(View.VISIBLE);
                ll_history.setVisibility(View.GONE);
                break;
        }
    }
}
