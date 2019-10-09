package com.love.novalue.ui.home.category;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.love.novalue.R;
import com.love.novalue.adapter.YoukaAdapter;
import com.love.novalue.bean.Youka;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YoukaActivity extends AppCompatActivity {
    YoukaAdapter youkaAdapter;
    List<Youka> youkaList;
    @BindView(R.id.gv_price)
    GridView gv_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youka);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        youkaList=new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            youkaList.add(new Youka());
        }
        youkaAdapter=new YoukaAdapter(this,youkaList);
        gv_price.setAdapter(youkaAdapter);
    }
}
