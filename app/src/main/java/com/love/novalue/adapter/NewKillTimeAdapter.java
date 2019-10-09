package com.love.novalue.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.love.novalue.R;
import com.love.novalue.bean.KillTime;

import java.util.List;

public class NewKillTimeAdapter extends BaseAdapter {
    Context context;
    List<KillTime> killTimes;
    int pos=0;
    public NewKillTimeAdapter(Context context, List<KillTime> killTimes) {
        this.context = context;
        this.killTimes = killTimes;
    }
    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public int getCount() {
        return killTimes.size();
    }

    @Override
    public Object getItem(int position) {
        return killTimes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.item_kill_time,null);
        TextView tv_time=convertView.findViewById(R.id.tv_time);
        TextView tv_status=convertView.findViewById(R.id.tv_status);
        LinearLayout llItem=convertView.findViewById(R.id.ll_item);
        if(pos==position){
            tv_time.setTextColor(Color.parseColor("#ffffff"));
            tv_status.setTextColor(Color.parseColor("#ffffff"));
            llItem.setBackground(context.getDrawable(R.mipmap.sl_kill_time));
        }else{
            tv_time.setTextColor(Color.parseColor("#333333"));
            tv_status.setTextColor(Color.parseColor("#979797"));
            llItem.setBackgroundColor(Color.parseColor("#f5f5f5"));
        }
        switch (killTimes.get(position).getStatus()){
            case 0:
                tv_status.setText("即将开抢");
                break;
            case 1:
                tv_status.setText("已开抢");
                break;
            case 2:
                tv_status.setText("抢购中");
                break;
            case 3:
                tv_status.setText("昨晚开始");
                break;
        }

        tv_time.setText(killTimes.get(position).getTime());
        return convertView;
    }
}
