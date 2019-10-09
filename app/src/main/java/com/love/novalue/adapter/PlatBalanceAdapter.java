package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.MyBalance;

import java.util.List;

public class PlatBalanceAdapter extends BaseQuickAdapter<MyBalance, BaseViewHolder> {
    public PlatBalanceAdapter(@Nullable List<MyBalance> data) {
        super(R.layout.item_balance_plat, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyBalance item) {

    }
}
