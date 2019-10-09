package com.love.novalue.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coorchice.library.SuperTextView;
import com.love.novalue.R;
import com.love.novalue.bean.Category;

import java.util.List;

public class CategoryAdapter extends BaseQuickAdapter<Category, BaseViewHolder> {
    int pos=0;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public CategoryAdapter(@Nullable List<Category> data) {
        super(R.layout.item_category_left, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Category item) {
        SuperTextView tvName=helper.getView(R.id.tv_name);
        if(pos==helper.getAdapterPosition()){
            tvName.setSolid(mContext.getResources().getColor(R.color.color_red));
            tvName.setTextColor(mContext.getResources().getColor(R.color.white));
        }else{
            tvName.setSolid(mContext.getResources().getColor(R.color.white));
            tvName.setTextColor(mContext.getResources().getColor(R.color.color_333));
        }
    }
}
