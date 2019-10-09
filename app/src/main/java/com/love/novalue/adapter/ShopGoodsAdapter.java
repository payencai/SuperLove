package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.HomeGoods;
import com.love.novalue.bean.ShopGoods;

import java.util.List;

public class ShopGoodsAdapter extends BaseQuickAdapter<ShopGoods, BaseViewHolder> {
    public ShopGoodsAdapter(@Nullable List<ShopGoods> data) {
        super(R.layout.item_shop_goods, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopGoods item) {

    }
}
