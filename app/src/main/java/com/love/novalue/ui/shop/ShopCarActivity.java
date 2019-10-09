package com.love.novalue.ui.shop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.love.novalue.R;
import com.love.novalue.adapter.ShopcarAdapter;
import com.love.novalue.adapter.ShopcarGoodsAdapter;
import com.love.novalue.bean.Shopcar;
import com.love.novalue.bean.ShopcarGoods;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopCarActivity extends AppCompatActivity {
    @BindView(R.id.rv_goods)
    RecyclerView rv_Goods;
    @BindView(R.id.rv_shopcar)
    RecyclerView rv_shopcar;
    List<Shopcar> shopcars;
    List<ShopcarGoods> shopcarGoods;
    ShopcarAdapter shopcarAdapter;
    ShopcarGoodsAdapter shopcarGoodsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car);
        ButterKnife.bind(this);
        initView();
    }
    @OnClick({R.id.iv_back,R.id.tv_submit})
    void OnClick(View view){
        switch (view.getId()){

            case R.id.tv_submit:
                startActivity(new Intent(ShopCarActivity.this,OrderConfirmActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;

        }
    }
    private void initView() {
        shopcarGoods=new ArrayList<>();
        shopcars=new ArrayList<>();
        for (int i = 0; i <3 ; i++) {
            shopcars.add(new Shopcar());
        }
        for (int i = 0; i <10 ; i++) {
            shopcarGoods.add(new ShopcarGoods());
        }
        shopcarAdapter=new ShopcarAdapter(shopcars);
        shopcarGoodsAdapter=new ShopcarGoodsAdapter(shopcarGoods);
        rv_shopcar.setLayoutManager(new LinearLayoutManager(this));
        rv_shopcar.setAdapter(shopcarAdapter);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        rv_Goods.setLayoutManager(gridLayoutManager);
        rv_Goods.setAdapter(shopcarGoodsAdapter);
    }
}
