package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.love.novalue.R;
import com.love.novalue.bean.CollegeShare;

import java.util.ArrayList;
import java.util.List;

public class CollegeShareAdapter extends BaseQuickAdapter<CollegeShare, BaseViewHolder> {
    public CollegeShareAdapter( @Nullable List<CollegeShare> data) {
        super(R.layout.item_college_goods, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollegeShare item) {
        NineGridImageView nineGridImageView=helper.getView(R.id.ngiv_photo);
        List<String> photo=new ArrayList<>();
        photo.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2035109898,3819262580&fm=26&gp=0.jpg");
        photo.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=485595573,3897999935&fm=26&gp=0.jpg");
        photo.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=123457689,3571431638&fm=15&gp=0.jpg");
        NinePhotoAdapter ninePhotoAdapter=new NinePhotoAdapter();
        nineGridImageView.setAdapter(ninePhotoAdapter);
        nineGridImageView.setImagesData(photo);


    }
}
