package com.love.novalue.tools;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.scwang.smartrefresh.layout.util.DensityUtil;


import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class PhotoUtil {

    public static void loadPic(Context mContext,String url,ImageView imageView){
        Glide.with(mContext)
                .load(url)
                .apply(RequestOptions.bitmapTransform(new MultiTransformation(
                        new CenterCrop(),
                        new RoundedCornersTransformation(DensityUtil.dp2px(8), 0, RoundedCornersTransformation.CornerType.TOP))))
                .into(imageView);

    }
    public static void loadPic(Context mContext,String url,ImageView imageView,int corners){
        Glide.with(mContext)
                .load(url)
                .apply(new RequestOptions()
                        .transforms(new CenterCrop(), new RoundedCorners(corners)
                        ))
                .into(imageView);
    }
}
