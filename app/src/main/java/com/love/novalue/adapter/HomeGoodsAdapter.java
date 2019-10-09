package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.HomeGoods;

import java.util.List;

public class HomeGoodsAdapter extends BaseQuickAdapter<HomeGoods, BaseViewHolder> {
    public HomeGoodsAdapter( @Nullable List<HomeGoods> data) {
        super(R.layout.item_kill_goods, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeGoods item) {

    }
}
