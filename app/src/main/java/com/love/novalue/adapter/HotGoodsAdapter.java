package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.HotGoods;
import com.love.novalue.bean.NewGoods;

import java.util.List;

public class HotGoodsAdapter extends BaseQuickAdapter<HotGoods, BaseViewHolder> {
    public HotGoodsAdapter(@Nullable List<HotGoods> data) {
        super(R.layout.item_new_goods, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotGoods item) {

    }
}
