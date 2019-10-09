package com.love.novalue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.love.novalue.R;
import com.love.novalue.bean.ShopChannel;
import com.love.novalue.tools.StringUtils;

import java.util.List;

public class ShopHotAdapter extends BaseAdapter {
    Context context;
    List<ShopChannel> shopChannels;

    public ShopHotAdapter(Context context, List<ShopChannel> shopChannels) {
        this.context = context;
        this.shopChannels = shopChannels;
    }

    @Override
    public int getCount() {
        return shopChannels.size();
    }

    @Override
    public Object getItem(int position) {
        return shopChannels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.item_shop_hot,null);
        ImageView ivLogo=convertView.findViewById(R.id.iv_logo);
        ImageView ivImg1=convertView.findViewById(R.id.iv_img1);
        ImageView ivImg2=convertView.findViewById(R.id.iv_img2);
        TextView tvName=convertView.findViewById(R.id.tv_name);
        TextView tvLabel=convertView.findViewById(R.id.tv_label);
        Glide.with(context).load(shopChannels.get(position).getLinkUrl()).into(ivLogo);
        List<String> imgs= StringUtils.StringToArrayList(shopChannels.get(position).getImgUrl(),",");
        if(imgs.size()>0){
            Glide.with(context).load(imgs.get(0)).into(ivImg1);
        }
        if(imgs.size()>1){
            Glide.with(context).load(imgs.get(1)).into(ivImg2);
        }
        tvName.setText(shopChannels.get(position).getName());
        tvLabel.setText(shopChannels.get(position).getCharactersText());
        return convertView;
    }
}
