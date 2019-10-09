package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;

import java.util.List;

public class HomeType2Adapter extends BaseQuickAdapter<HomeTypeGoods, BaseViewHolder> {
    public HomeType2Adapter(@Nullable List<HomeTypeGoods> data) {
        super(R.layout.item_home_type2, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeTypeGoods item) {

    }
}
