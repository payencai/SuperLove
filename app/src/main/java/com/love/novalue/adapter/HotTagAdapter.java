package com.love.novalue.adapter;

import android.view.LayoutInflater;
import android.view.View;

import com.coorchice.library.SuperTextView;
import com.love.novalue.R;
import com.love.novalue.bean.HotWord;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

public class HotTagAdapter extends TagAdapter<HotWord> {

    public HotTagAdapter(List<HotWord> datas) {
        super(datas);
    }

    @Override
    public View getView(FlowLayout parent, int position, HotWord h) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_tag,null);
        SuperTextView superTextView=view.findViewById(R.id.tv_tag);
        superTextView.setText(h.getName());
        return view;
    }
}
