package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.HomeCategoryGoods;

import java.util.List;

public class HomeTodayHot1Adapter extends BaseQuickAdapter<HomeCategoryGoods, BaseViewHolder> {
    public HomeTodayHot1Adapter(@Nullable List<HomeCategoryGoods> data) {
        super(R.layout.item_today_hot1, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeCategoryGoods item) {

    }
}
