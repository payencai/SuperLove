package com.love.novalue.ui.home.category;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.love.novalue.R;
import com.love.novalue.adapter.HomeNineAdapter;
import com.love.novalue.bean.HomeCategoryGoods;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NineMoneyActivity extends AppCompatActivity {
    @BindView(R.id.rv_goods)
    RecyclerView rv_goods;
    HomeNineAdapter homeNineAdapter;
    List<HomeCategoryGoods> homeCategoryGoods;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine_money);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        homeCategoryGoods=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            homeCategoryGoods.add(new HomeCategoryGoods());
        }
        homeNineAdapter=new HomeNineAdapter(homeCategoryGoods);
        rv_goods.setLayoutManager(new LinearLayoutManager(this));
        rv_goods.setAdapter(homeNineAdapter);
    }
}
