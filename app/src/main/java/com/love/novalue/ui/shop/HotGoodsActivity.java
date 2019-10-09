package com.love.novalue.ui.shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gyf.immersionbar.ImmersionBar;
import com.love.novalue.R;
import com.love.novalue.adapter.HotGoodsAdapter;
import com.love.novalue.adapter.NewGoodsAdapter;
import com.love.novalue.bean.HotGoods;
import com.love.novalue.bean.NewGoods;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotGoodsActivity extends AppCompatActivity {

    @BindView(R.id.rv_goods)
    RecyclerView rv_goods;
    List<HotGoods> newGoods;
    HotGoodsAdapter newGoodsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_goods);
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarColor(R.color.color_hot).navigationBarColor(R.color.color_hot).fitsSystemWindows(true).init();
        initView();
    }

    private void initView() {
        newGoods=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            newGoods.add(new HotGoods());
        }
        newGoodsAdapter=new HotGoodsAdapter(newGoods);
        rv_goods.setLayoutManager(new LinearLayoutManager(this));
        rv_goods.setAdapter(newGoodsAdapter);
        rv_goods.setNestedScrollingEnabled(false);
        rv_goods.setFocusable(false);
    }
}
