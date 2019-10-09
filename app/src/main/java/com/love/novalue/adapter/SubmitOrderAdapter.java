package com.love.novalue.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.allen.library.SuperButton;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.SubmitOrder;

import java.util.ArrayList;
import java.util.List;

public class SubmitOrderAdapter extends BaseQuickAdapter<SubmitOrder, BaseViewHolder> {
    public SubmitOrderAdapter( @Nullable List<SubmitOrder> data) {
        super(R.layout.item_confirm_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubmitOrder item) {
        RecyclerView rv_child=helper.getView(R.id.rv_child);
        List<SubmitOrder.Child> childList=new ArrayList<>();
        for (int i = 0; i <2 ; i++) {
            childList.add(new SubmitOrder.Child());
        }
        SubmitOrderChildAdapter submitOrderChildAdapter=new SubmitOrderChildAdapter(childList);
        rv_child.setLayoutManager(new LinearLayoutManager(mContext));
        rv_child.setAdapter(submitOrderChildAdapter);
    }
}
