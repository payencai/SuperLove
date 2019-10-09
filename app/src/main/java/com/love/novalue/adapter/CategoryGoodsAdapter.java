package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.CategoryGoods;

import java.util.List;

public class CategoryGoodsAdapter extends BaseQuickAdapter<CategoryGoods, BaseViewHolder> {
    public CategoryGoodsAdapter( @Nullable List<CategoryGoods> data) {
        super(R.layout.item_category_right, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryGoods item) {

    }
}
