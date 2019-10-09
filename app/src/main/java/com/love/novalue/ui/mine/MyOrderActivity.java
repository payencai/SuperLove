package com.love.novalue.ui.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.love.novalue.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.iv_back,R.id.rl_love})
    void OnClick(View view){
        switch (view.getId()) {
            case R.id.rl_love:
                startActivity(new Intent(MyOrderActivity.this,LoveOrderActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
