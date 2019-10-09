package com.love.novalue.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.LoveOrder;

import java.util.ArrayList;
import java.util.List;

public class LoveOrderAdapter extends BaseQuickAdapter<LoveOrder, BaseViewHolder> {
    public LoveOrderAdapter( @Nullable List<LoveOrder> data) {
        super(R.layout.item_my_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LoveOrder item) {
        RecyclerView rv_Child=helper.getView(R.id.rv_child);
        List<LoveOrder.Child> children=new ArrayList<>();
        for (int i = 0; i <2 ; i++) {
            children.add(new LoveOrder.Child());
        }
        LoveOrderChildAdapter loveOrderChildAdapter=new LoveOrderChildAdapter(children);
        rv_Child.setLayoutManager(new LinearLayoutManager(mContext));
        rv_Child.setAdapter(loveOrderChildAdapter);
    }
}
