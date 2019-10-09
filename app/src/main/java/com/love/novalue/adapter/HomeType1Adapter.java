package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.ShopGoods;

import java.util.List;

public class HomeType1Adapter extends BaseQuickAdapter<HomeTypeGoods, BaseViewHolder> {
    public HomeType1Adapter(@Nullable List<HomeTypeGoods> data) {
        super(R.layout.item_home_type1, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeTypeGoods item) {

    }
}
