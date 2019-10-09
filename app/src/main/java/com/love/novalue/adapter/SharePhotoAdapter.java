package com.love.novalue.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.SharePhoto;

import java.util.List;

public class SharePhotoAdapter extends BaseQuickAdapter<SharePhoto, BaseViewHolder> {
    int select=0;

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public SharePhotoAdapter(@Nullable List<SharePhoto> data) {
        super(R.layout.item_share_photo, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SharePhoto item) {
        ImageView ivSelect=helper.getView(R.id.iv_select);
        TextView tv_tag=helper.getView(R.id.tv_tag);
        if(helper.getAdapterPosition()==0){
            tv_tag.setVisibility(View.VISIBLE);
        }else{
            tv_tag.setVisibility(View.GONE);
        }
        if(item.isCheck()){
            ivSelect.setImageResource(R.mipmap.sl_photo_check);
        }else{
            ivSelect.setImageResource(R.mipmap.sl_photo_uncheck);
        }
    }
}
