package com.love.novalue.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.coorchice.library.SuperTextView;
import com.love.novalue.R;
import com.love.novalue.bean.ShopCategory;

import java.util.List;

public class GridShopCategoryAdapter extends BaseAdapter {
    Context context;
    List<ShopCategory> shopCategories;
    int select=0;
    public GridShopCategoryAdapter(Context context, List<ShopCategory> shopCategories) {
        this.context = context;
        this.shopCategories = shopCategories;
    }

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return shopCategories.size();
    }

    @Override
    public Object getItem(int position) {
        return shopCategories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_category_popup,null);
        SuperTextView tvName=view.findViewById(R.id.tv_name);
        tvName.setText(shopCategories.get(position).getName());
        if(select==position){
            tvName.setSolid(Color.parseColor("#F92732"));
            tvName.setTextColor(Color.parseColor("#ffffff"));
        }else{
            tvName.setSolid(Color.parseColor("#ffffff"));
            tvName.setTextColor(Color.parseColor("#0C0C0C"));
        }
        return view;
    }
}
