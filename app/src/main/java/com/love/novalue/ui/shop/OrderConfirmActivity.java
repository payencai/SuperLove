package com.love.novalue.ui.shop;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.love.novalue.R;
import com.love.novalue.adapter.SubmitOrderAdapter;
import com.love.novalue.bean.SubmitOrder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderConfirmActivity extends AppCompatActivity {
    int payType = 1;
    ImageView iv_alipay;
    ImageView iv_wechat;
    SubmitOrderAdapter submitOrderAdapter;
    @BindView(R.id.rv_order)
    RecyclerView rv_order;
    List<SubmitOrder> submitOrders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        submitOrders=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            submitOrders.add(new SubmitOrder());
        }
        submitOrderAdapter=new SubmitOrderAdapter(submitOrders);
        rv_order.setLayoutManager(new LinearLayoutManager(this));
        rv_order.setAdapter(submitOrderAdapter);
    }

    @OnClick({R.id.rl_pay,R.id.iv_back,R.id.rl_address,R.id.tv_pay})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.rl_pay:
                showPayMethodDialog();
                break;
            case R.id.tv_pay:
                startActivity(new Intent(OrderConfirmActivity.this,PaySuccessActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_address:

                break;
        }
    }
    private void showPayMethodDialog() {
        payType = 1;
        final Dialog dialog = new Dialog(this, R.style.BottomDialog);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_pay_method, null);
        TextView tv_confirm = view.findViewById(R.id.tv_confirm);

        RelativeLayout rlAliPay = view.findViewById(R.id.rl_alipay);
        RelativeLayout rlWechat = view.findViewById(R.id.rl_wechat);
        iv_alipay = view.findViewById(R.id.iv_alipay);
        iv_wechat = view.findViewById(R.id.iv_wechat);

        rlWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payType = 2;
                iv_wechat.setImageResource(R.mipmap.sl_shopcar_choose);
                iv_alipay.setImageResource(R.mipmap.sl_shopcar_unchoose);
            }
        });
        rlAliPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payType = 1;
                iv_alipay.setImageResource(R.mipmap.sl_shopcar_choose);
                iv_wechat.setImageResource(R.mipmap.sl_shopcar_unchoose);
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (payType == 1) {

                } else {

                }
                //showPayFinishDialog();
            }
        });
        dialog.setContentView(view);
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
    }
}
