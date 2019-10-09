package com.love.novalue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.coorchice.library.SuperTextView;
import com.love.novalue.R;
import com.love.novalue.bean.ShopSku;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

public class DialogTagAdapter extends TagAdapter<ShopSku.Child> {
   Context context;
    public DialogTagAdapter(Context context,List<ShopSku.Child> datas) {
        super(datas);
        this.context=context;
    }

    @Override
    public View getView(FlowLayout parent, int position, ShopSku.Child child) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog_tag,null);
        SuperTextView superTextView=view.findViewById(R.id.tv_tag);
        superTextView.setText(child.getAttributeValue());
        if(child.isSelect()){
            superTextView.setTextStrokeColor(R.color.color_red4);
            superTextView.setTextColor(context.getResources().getColor(R.color.color_red4));
        }else{
            superTextView.setTextStrokeColor(R.color.color_c7);
            superTextView.setTextColor(context.getResources().getColor(R.color.color_333));
        }
        return view;
    }
}
