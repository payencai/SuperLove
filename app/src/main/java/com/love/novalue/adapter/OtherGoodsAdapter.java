package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.love.novalue.R;
import com.love.novalue.bean.OtherGoods;

import java.util.List;

public class OtherGoodsAdapter extends BaseQuickAdapter<OtherGoods, BaseViewHolder> {
    public OtherGoodsAdapter( @Nullable List<OtherGoods> data) {
        super(R.layout.item_other_goods, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OtherGoods item) {

    }
}
