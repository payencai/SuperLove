package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.HomeCategoryGoods;

import java.util.List;

public class HomeJuhuasuanAdapter extends BaseQuickAdapter<HomeCategoryGoods, BaseViewHolder> {
    public HomeJuhuasuanAdapter(@Nullable List<HomeCategoryGoods> data) {
        super(R.layout.item_taobao_like, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeCategoryGoods item) {

    }
}
