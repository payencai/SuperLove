package com.love.novalue.ui.shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.love.novalue.R;
import com.love.novalue.adapter.RecommendGoodsAdapter;
import com.love.novalue.adapter.ShopcarAdapter;
import com.love.novalue.adapter.ShopcarGoodsAdapter;
import com.love.novalue.bean.RecommendGoods;
import com.love.novalue.bean.Shopcar;
import com.love.novalue.bean.ShopcarGoods;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaySuccessActivity extends AppCompatActivity {
    @BindView(R.id.rv_goods)
    RecyclerView rv_Goods;
    List<RecommendGoods> recommendGoods;
    RecommendGoodsAdapter recommendGoodsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
        recommendGoods=new ArrayList<>();

        for (int i = 0; i <10 ; i++) {
            recommendGoods.add(new RecommendGoods());
        }
        recommendGoodsAdapter=new RecommendGoodsAdapter(recommendGoods);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        rv_Goods.setLayoutManager(gridLayoutManager);
        rv_Goods.setAdapter(recommendGoodsAdapter);
    }
}
