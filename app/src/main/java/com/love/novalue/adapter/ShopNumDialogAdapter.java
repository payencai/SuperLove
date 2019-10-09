package com.love.novalue.adapter;

import android.content.Context;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.love.novalue.R;
import com.love.novalue.bean.ShopSku;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

public class ShopNumDialogAdapter extends BaseAdapter {
    Context context;
    List<ShopSku> shopSkus;

    public ShopNumDialogAdapter(Context context, List<ShopSku> shopSkus) {
        this.context = context;
        this.shopSkus = shopSkus;
    }

    @Override
    public int getCount() {
        return shopSkus.size();
    }

    @Override
    public Object getItem(int i) {
        return shopSkus.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(context).inflate(R.layout.item_dialog_num,null);
        DialogTagAdapter dialogTagAdapter=new DialogTagAdapter(context,shopSkus.get(i).getChildList());
        TagFlowLayout tagFlowLayout=view.findViewById(R.id.fl_tag);
        TextView tvName=view.findViewById(R.id.tv_name);
        tagFlowLayout.setAdapter(dialogTagAdapter);
        tvName.setText(shopSkus.get(i).getAttributeName());
        return view;
    }
}
