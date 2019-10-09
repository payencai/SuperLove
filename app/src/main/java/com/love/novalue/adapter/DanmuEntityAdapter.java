package com.love.novalue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.love.novalue.R;
import com.love.novalue.bean.DanmuEntity;
import com.orzangleli.xdanmuku.XAdapter;

public class DanmuEntityAdapter extends XAdapter<DanmuEntity> {
    Context context;

    public DanmuEntityAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(DanmuEntity danmuEntity, View view) {
        view=LayoutInflater.from(context).inflate(R.layout.item_danmu,null);
        return view;
    }

    @Override
    public int[] getViewTypeArray() {
        return new int[0];
    }

    @Override
    public int getSingleLineHeight() {
        View view=LayoutInflater.from(context).inflate(R.layout.item_danmu,null);
        view.measure(0, 0);
        return view.getMeasuredHeight();
    }
}
