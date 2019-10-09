package com.love.novalue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.love.novalue.R;
import com.love.novalue.bean.Youka;

import java.util.List;

public class YoukaAdapter extends BaseAdapter {
    Context context;
    List<Youka> youkaList;
    int select=0;
    public YoukaAdapter(Context context, List<Youka> youkaList) {
        this.context = context;
        this.youkaList = youkaList;
    }

    @Override
    public int getCount() {
        return youkaList.size();
    }

    @Override
    public Object getItem(int position) {
        return youkaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.item_youka,null);
        if(select==position){
            convertView.setBackgroundResource(R.drawable.shape_youka_select);
        }else{
            convertView.setBackgroundResource(R.drawable.shape_youka_unselect);
        }
        return convertView;
    }
}
