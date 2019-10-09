package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.CategoryGoods;
import com.love.novalue.bean.HomeMsg;

import java.util.List;

public class HomeMsgAdapter extends BaseQuickAdapter<HomeMsg, BaseViewHolder> {
    public HomeMsgAdapter(@Nullable List<HomeMsg> data) {
        super(R.layout.item_home_msg, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeMsg item) {

    }
}
