package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.Shopcar;

import java.util.List;

public class ShopcarAdapter extends BaseQuickAdapter<Shopcar, BaseViewHolder> {
    public ShopcarAdapter( @Nullable List<Shopcar> data) {
        super(R.layout.item_shopcar, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Shopcar item) {

    }
}
