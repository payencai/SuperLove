package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;

import java.util.List;

public class HomeType3Adapter extends BaseQuickAdapter<HomeTypeGoods, BaseViewHolder> {
    public HomeType3Adapter(@Nullable List<HomeTypeGoods> data) {
        super(R.layout.item_home_type3, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeTypeGoods item) {

    }
}
