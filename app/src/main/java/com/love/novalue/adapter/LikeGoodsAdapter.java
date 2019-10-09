package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.LikeGoods;
import com.love.novalue.bean.RecommendGoods;

import java.util.List;

public class LikeGoodsAdapter extends BaseQuickAdapter<LikeGoods, BaseViewHolder> {
    public LikeGoodsAdapter(@Nullable List<LikeGoods> data) {
        super(R.layout.item_taobao_like, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LikeGoods item) {

    }
}
