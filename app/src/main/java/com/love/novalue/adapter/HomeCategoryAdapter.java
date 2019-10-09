package com.love.novalue.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.HomeCategory;

import java.util.List;

public class HomeCategoryAdapter extends BaseQuickAdapter<HomeCategory, BaseViewHolder> {
    public HomeCategoryAdapter( @Nullable List<HomeCategory> data) {
        super(R.layout.grid_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeCategory item) {
        //初始化控件
        ImageView imageView = (ImageView) helper.getView(R.id.iv_icon);
        TextView textView = (TextView) helper.getView(R.id.tv_name);

        //为gridView子布局布局中添加图片
        imageView.setImageResource(item.getIconId());

        //为gridView子布局中添加文字
        textView.setText(item.getIconName());
    }
}
