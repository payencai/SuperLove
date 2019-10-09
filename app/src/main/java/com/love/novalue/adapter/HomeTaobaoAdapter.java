package com.love.novalue.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.HomeCategory;
import com.love.novalue.bean.HomeCategoryGoods;

import java.util.List;

public class HomeTaobaoAdapter extends BaseQuickAdapter<HomeCategoryGoods, BaseViewHolder> {
    public HomeTaobaoAdapter(@Nullable List<HomeCategoryGoods> data) {
        super(R.layout.item_taobao_like, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeCategoryGoods item) {

    }
}
