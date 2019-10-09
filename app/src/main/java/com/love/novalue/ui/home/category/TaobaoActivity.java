package com.love.novalue.ui.home.category;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.love.novalue.R;
import com.love.novalue.adapter.HomeTaobaoAdapter;
import com.love.novalue.bean.HomeCategoryGoods;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaobaoActivity extends AppCompatActivity {
    HomeTaobaoAdapter homeTaobaoAdapter;
    List<HomeCategoryGoods> homeCategoryGoods;
    @BindView(R.id.rv_goods)
    RecyclerView rv_goods;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taobao);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        homeCategoryGoods=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            homeCategoryGoods.add(new HomeCategoryGoods());
        }
        homeTaobaoAdapter=new HomeTaobaoAdapter(homeCategoryGoods);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        rv_goods.setLayoutManager(gridLayoutManager);
        rv_goods.setAdapter(homeTaobaoAdapter);
        rv_goods.setFocusable(false);
        rv_goods.setNestedScrollingEnabled(false);
    }
}
