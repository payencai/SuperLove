package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.ShopSearchGoods;

import java.util.List;

public class ShopSearchGoodsAdapter extends BaseQuickAdapter<ShopSearchGoods, BaseViewHolder> {
    public ShopSearchGoodsAdapter( @Nullable List<ShopSearchGoods> data) {
        super(R.layout.item_category_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopSearchGoods item) {

    }
}
