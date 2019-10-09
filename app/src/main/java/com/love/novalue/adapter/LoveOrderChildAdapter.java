package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.LoveOrder;

import java.util.List;

public class LoveOrderChildAdapter extends BaseQuickAdapter<LoveOrder.Child, BaseViewHolder> {
    public LoveOrderChildAdapter( @Nullable List<LoveOrder.Child> data) {
        super(R.layout.item_order_child, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LoveOrder.Child item) {

    }
}
