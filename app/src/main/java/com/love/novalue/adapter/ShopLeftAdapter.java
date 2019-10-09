package com.love.novalue.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.ShopCategory;
import com.love.novalue.bean.ShopLeft;

import java.util.List;

public class ShopLeftAdapter extends BaseAdapter {
    Context context;
    List<ShopCategory.ChildListBean> shopLefts;
    int select=0;

    public ShopLeftAdapter(Context context, List<ShopCategory.ChildListBean> shopLefts) {
        this.context = context;
        this.shopLefts = shopLefts;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<ShopCategory.ChildListBean> getShopLefts() {
        return shopLefts;
    }

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public void setShopLefts(List<ShopCategory.ChildListBean> shopLefts) {
        this.shopLefts = shopLefts;
    }

    @Override
    public int getCount() {
        return shopLefts.size();
    }

    @Override
    public Object getItem(int position) {
        return shopLefts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.item_shop_left,null);
        LinearLayout rl_item=convertView.findViewById(R.id.rl_item);
        View view=convertView.findViewById(R.id.view);
        TextView tvName=convertView.findViewById(R.id.tv_name);
        if(select==position){
            rl_item.setBackgroundColor(context.getResources().getColor(R.color.white));
            view.setBackgroundColor(context.getResources().getColor(R.color.color_red));
            tvName.setTextColor(context.getResources().getColor(R.color.color_red));
        }else{
            rl_item.setBackgroundColor(context.getResources().getColor(R.color.color_f5));
            tvName.setTextColor(context.getResources().getColor(R.color.color_47));
            view.setBackgroundColor(context.getResources().getColor(R.color.white));
        }
        tvName.setText(shopLefts.get(position).getName());

        return convertView;
    }
}
