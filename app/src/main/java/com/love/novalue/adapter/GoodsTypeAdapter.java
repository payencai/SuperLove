package com.love.novalue.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coorchice.library.SuperTextView;
import com.love.novalue.R;
import com.love.novalue.bean.GoodsType;
import com.love.novalue.bean.ShopCategory;

import java.util.List;

public class GoodsTypeAdapter extends BaseQuickAdapter<ShopCategory, BaseViewHolder> {
    public GoodsTypeAdapter(@Nullable List<ShopCategory> data) {
        super(R.layout.item_goods_type, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCategory item) {
        SuperTextView tv_name = helper.getView(R.id.tv_name);
        ImageView iv_goods=helper.getView(R.id.iv_goods);
        tv_name.setText(item.getName());
        Glide.with(mContext).load(item.getImgUrl()).into(iv_goods);
        switch (helper.getAdapterPosition()) {
            case 0:

                tv_name.setSolid(mContext.getResources().getColor(R.color.color_category1));
                break;
            case 1:

                tv_name.setSolid(mContext.getResources().getColor(R.color.color_category2));
                break;
            case 2:

                tv_name.setSolid(mContext.getResources().getColor(R.color.color_category3));
                break;
            case 3:

                tv_name.setSolid(mContext.getResources().getColor(R.color.color_category4));
                break;
            case 4:

                tv_name.setSolid(mContext.getResources().getColor(R.color.color_category5));
                break;
            case 5:

                tv_name.setSolid(mContext.getResources().getColor(R.color.color_category6));
                break;
            case 6:

                tv_name.setSolid(mContext.getResources().getColor(R.color.color_category7));
                break;
            case 7:

                tv_name.setSolid(mContext.getResources().getColor(R.color.color_category8));
                break;
        }
    }
}
