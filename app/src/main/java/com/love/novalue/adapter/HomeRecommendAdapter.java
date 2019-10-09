package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.RecommendGoods;

import java.util.List;

public class HomeRecommendAdapter extends BaseQuickAdapter<RecommendGoods, BaseViewHolder> {
    public HomeRecommendAdapter(@Nullable List<RecommendGoods> data) {
        super(R.layout.item_shop_recommend_goods, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendGoods item) {

    }
}
