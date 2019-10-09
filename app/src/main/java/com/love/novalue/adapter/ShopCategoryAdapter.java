package com.love.novalue.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coorchice.library.SuperTextView;
import com.love.novalue.R;
import com.love.novalue.bean.ShopCategory;

import java.util.List;

public class ShopCategoryAdapter extends BaseQuickAdapter<ShopCategory, BaseViewHolder> {
    int select=0;

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public ShopCategoryAdapter(@Nullable List<ShopCategory> data) {
        super(R.layout.item_category_shop, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCategory item) {
        SuperTextView tvName=helper.getView(R.id.tv_name);
        tvName.setText(item.getName());
        if(select==helper.getAdapterPosition()){
            tvName.setSolid(Color.parseColor("#F92732"));
            tvName.setTextColor(Color.parseColor("#ffffff"));
        }else{
            tvName.setSolid(Color.parseColor("#ffffff"));
            tvName.setTextColor(Color.parseColor("#1F1F1F"));
        }
    }
}
