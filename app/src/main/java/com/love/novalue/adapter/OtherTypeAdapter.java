package com.love.novalue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.love.novalue.R;
import com.love.novalue.bean.OtherType;

import java.util.List;

public class OtherTypeAdapter extends BaseAdapter {
    List<OtherType> otherTypes;
    Context context;

    public OtherTypeAdapter(List<OtherType> otherTypes, Context context) {
        this.otherTypes = otherTypes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return otherTypes.size();
    }

    @Override
    public Object getItem(int position) {
        return otherTypes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_other_type,null);
        return view;
    }
}
