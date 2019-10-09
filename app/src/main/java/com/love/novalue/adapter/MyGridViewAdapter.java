package com.love.novalue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.love.novalue.R;
import com.love.novalue.bean.ItemInfo;

import java.util.ArrayList;
import java.util.List;

public class MyGridViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<ItemInfo> itemData = new ArrayList<>();

    public MyGridViewAdapter(Context mContext, List<ItemInfo> itemData) {
        this.mContext = mContext;
        this.itemData = itemData;
    }

    @Override
    public int getCount() {
        return itemData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        //加载gridView中的子布局
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.grid_item, null);

        //初始化控件
        ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_icon);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_name);

        //为gridView子布局布局中添加图片
        imageView.setImageResource(itemData.get(i).getIconId());

        //为gridView子布局中添加文字
        textView.setText(itemData.get(i).getIconName());

        return inflate;
    }
}

