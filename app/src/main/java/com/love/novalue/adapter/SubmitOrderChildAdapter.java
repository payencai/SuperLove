package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.SubmitOrder;

import java.util.List;

public class SubmitOrderChildAdapter extends BaseQuickAdapter<SubmitOrder.Child, BaseViewHolder> {
    public SubmitOrderChildAdapter(@Nullable List<SubmitOrder.Child> data) {
        super(R.layout.item_confirm_order_child, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubmitOrder.Child item) {

    }
}
