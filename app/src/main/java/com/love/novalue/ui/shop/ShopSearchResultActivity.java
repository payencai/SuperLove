package com.love.novalue.ui.shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gyf.immersionbar.ImmersionBar;
import com.love.novalue.R;
import com.love.novalue.adapter.ShopSearchGoodsAdapter;
import com.love.novalue.bean.ShopSearchGoods;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopSearchResultActivity extends AppCompatActivity {
    @BindView(R.id.rv_goods)
    RecyclerView rv_goods;
    ShopSearchGoodsAdapter shopSearchGoodsAdapter;
    List<ShopSearchGoods> shopSearchGoods;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_search_result);
        ButterKnife.bind(this);
        ImmersionBar.with(this).autoDarkModeEnable(true).statusBarColor(R.color.color_f5).fitsSystemWindows(true).init();
        initView();
    }

    private void initView() {
        shopSearchGoods=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            shopSearchGoods.add(new ShopSearchGoods());
        }
        shopSearchGoodsAdapter=new ShopSearchGoodsAdapter(shopSearchGoods);
        rv_goods.setLayoutManager(new LinearLayoutManager(this));
        rv_goods.setAdapter(shopSearchGoodsAdapter);
    }
}
