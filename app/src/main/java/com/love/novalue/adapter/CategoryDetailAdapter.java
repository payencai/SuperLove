package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.CategoryDetail;

import java.util.List;

public class CategoryDetailAdapter extends BaseQuickAdapter<CategoryDetail, BaseViewHolder> {
    public CategoryDetailAdapter(@Nullable List<CategoryDetail> data) {
        super(R.layout.item_category_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryDetail item) {

    }
}
