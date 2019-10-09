package com.love.novalue.ui.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.love.novalue.R;
import com.love.novalue.adapter.CategoryAdapter;
import com.love.novalue.adapter.CategoryGoodsAdapter;
import com.love.novalue.bean.Category;
import com.love.novalue.bean.CategoryGoods;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsCategoryActivity extends AppCompatActivity {
    @BindView(R.id.rv_left)
    RecyclerView rv_left;
    @BindView(R.id.rv_right)
    RecyclerView rv_right;
    CategoryAdapter categoryAdapter;
    CategoryGoodsAdapter categoryGoodsAdapter;
    List<Category> categories;
    List<CategoryGoods> categoryGoods;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_category);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        categories=new ArrayList<>();
        categoryGoods=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            categories.add(new Category());
        }
        for (int i = 0; i <10 ; i++) {
            categoryGoods.add(new CategoryGoods());
        }
        categoryGoodsAdapter=new CategoryGoodsAdapter(categoryGoods);
        categoryGoodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(GoodsCategoryActivity.this,CategoryDetailActivity.class));
            }
        });
        categoryAdapter=new CategoryAdapter(categories);
        categoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                categoryAdapter.setPos(position);
                categoryAdapter.notifyDataSetChanged();
            }
        });
        rv_left.setLayoutManager(new LinearLayoutManager(this));
        rv_left.setAdapter(categoryAdapter);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
        rv_right.setLayoutManager(gridLayoutManager);
        rv_right.setAdapter(categoryGoodsAdapter);
    }
}
