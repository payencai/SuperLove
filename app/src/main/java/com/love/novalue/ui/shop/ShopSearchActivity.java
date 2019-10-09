package com.love.novalue.ui.shop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.love.novalue.R;
import com.love.novalue.adapter.HotTagAdapter;
import com.love.novalue.adapter.SearchTagAdapter;
import com.love.novalue.bean.HotWord;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopSearchActivity extends AppCompatActivity {
    @BindView(R.id.fl_history)
    TagFlowLayout flHistory;
    @BindView(R.id.fl_hot)
    TagFlowLayout fl_hot;
    List<String> historyList;
    List<HotWord> hotWords;
    HotTagAdapter hotTagAdapter;
    SearchTagAdapter searchTagAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_search);
        ButterKnife.bind(this);
        ImmersionBar.with(this).autoDarkModeEnable(true).statusBarColor(R.color.white).fitsSystemWindows(true).init();
        initView();
    }

    private void initView() {
        historyList=new ArrayList<>();
        hotWords=new ArrayList<>();
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
        hotTagAdapter=new HotTagAdapter(hotWords);
        searchTagAdapter=new SearchTagAdapter(historyList);
        flHistory.setAdapter(searchTagAdapter);
        fl_hot.setAdapter(hotTagAdapter);
    }
    @OnClick({R.id.tv_search})
    void OnClcik(View view){
        switch (view.getId()){
            case R.id.tv_search:
                startActivity(new Intent(ShopSearchActivity.this,ShopSearchResultActivity.class));
                break;
        }
    }

}
