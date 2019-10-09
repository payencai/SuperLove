package com.love.novalue.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.KillTime;

import java.util.List;

public class KillTimeAdapter extends BaseQuickAdapter<KillTime, BaseViewHolder> {
    int pos=0;
    public KillTimeAdapter( @Nullable List<KillTime> data) {
        super(R.layout.item_kill_time, data);
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    protected void convert(BaseViewHolder helper, KillTime item) {
        TextView tv_time=helper.getView(R.id.tv_time);
        TextView tv_status=helper.getView(R.id.tv_status);
        LinearLayout llItem=helper.getView(R.id.ll_item);
        if(pos==helper.getAdapterPosition()){
            tv_time.setTextColor(Color.parseColor("#ffffff"));
            tv_status.setTextColor(Color.parseColor("#ffffff"));
            llItem.setBackground(mContext.getResources().getDrawable(R.mipmap.sl_kill_time));
        }else{
            tv_time.setTextColor(Color.parseColor("#333333"));
            tv_status.setTextColor(Color.parseColor("#979797"));
            llItem.setBackgroundColor(Color.parseColor("#f5f5f5"));
        }
        tv_status.setText(item.getStatus()+"");
        tv_time.setText(item.getTime());
    }
}
