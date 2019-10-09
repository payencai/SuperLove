package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.HomeSearchGoods;
import com.love.novalue.bean.ShopSearchGoods;

import java.util.List;

public class HomeSearchGoodsAdapter extends BaseQuickAdapter<HomeSearchGoods, BaseViewHolder> {
    public HomeSearchGoodsAdapter(@Nullable List<HomeSearchGoods> data) {
        super(R.layout.item_category_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeSearchGoods item) {

    }
}
