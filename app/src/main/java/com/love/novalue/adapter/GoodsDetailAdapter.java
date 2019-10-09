package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;

import java.util.List;

public class GoodsDetailAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public GoodsDetailAdapter( @Nullable List<String> data) {
        super(R.layout.item_detail_image, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
