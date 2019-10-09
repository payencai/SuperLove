package com.love.novalue.ui.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.love.novalue.R;
import com.love.novalue.adapter.CategoryDetailAdapter;
import com.love.novalue.bean.CategoryDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryDetailActivity extends AppCompatActivity {
    @BindView(R.id.rv_goods)
    RecyclerView rv_goods;
    CategoryDetailAdapter categoryDetailAdapter;
    List<CategoryDetail> categoryDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        categoryDetails=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            categoryDetails.add(new CategoryDetail());
        }
        categoryDetailAdapter=new CategoryDetailAdapter(categoryDetails);
        rv_goods.setLayoutManager(new LinearLayoutManager(this));
        rv_goods.setAdapter(categoryDetailAdapter);
    }
}
