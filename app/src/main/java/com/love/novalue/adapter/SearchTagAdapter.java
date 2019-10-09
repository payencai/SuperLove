package com.love.novalue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.coorchice.library.SuperTextView;
import com.love.novalue.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

public class SearchTagAdapter extends TagAdapter<String> {

    public SearchTagAdapter(List<String> datas) {
        super(datas);
    }

    @Override
    public View getView(FlowLayout parent, int position, String s) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_tag,null);
        SuperTextView superTextView=view.findViewById(R.id.tv_tag);
        superTextView.setText(s);
        return view;
    }
}
