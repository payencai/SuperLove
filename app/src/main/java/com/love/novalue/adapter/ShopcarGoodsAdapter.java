package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.Shopcar;
import com.love.novalue.bean.ShopcarGoods;

import java.util.List;

public class ShopcarGoodsAdapter extends BaseQuickAdapter<ShopcarGoods, BaseViewHolder> {
    public ShopcarGoodsAdapter(@Nullable List<ShopcarGoods> data) {
        super(R.layout.item_shopcar_goods, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopcarGoods item) {

    }
}
