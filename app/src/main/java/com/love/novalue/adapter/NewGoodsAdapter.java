package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.NewGoods;

import java.util.List;

public class NewGoodsAdapter extends BaseQuickAdapter<NewGoods, BaseViewHolder> {
    public NewGoodsAdapter( @Nullable List<NewGoods> data) {
        super(R.layout.item_new_goods, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewGoods item) {

    }
}
