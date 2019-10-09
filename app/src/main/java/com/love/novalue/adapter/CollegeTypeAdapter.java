package com.love.novalue.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coorchice.library.SuperTextView;
import com.love.novalue.R;

import java.util.List;

public class CollegeTypeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    int select=0;

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public CollegeTypeAdapter( @Nullable List<String> data) {
        super(R.layout.item_college_type, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        SuperTextView superTextView=helper.getView(R.id.tv_name);
        superTextView.setText(item);
        if(select==helper.getAdapterPosition()){
            superTextView.setTextColor(Color.parseColor("#FE3F49"));
        }else{
            superTextView.setTextColor(Color.parseColor("#6E6E6E"));
        }
    }
}
