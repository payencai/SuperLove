package com.love.novalue.tools;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;


import java.util.ArrayList;

public class PhotoUtil {

    public static void loadPic(Context mContext,String url,ImageView imageView){
        Glide.with(mContext)
                .load(url)
                .apply(new RequestOptions()
                        .transforms(new CenterCrop(), new RoundedCorners(8)
                        ))
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
