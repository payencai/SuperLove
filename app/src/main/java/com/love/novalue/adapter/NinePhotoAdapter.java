package com.love.novalue.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.love.novalue.R;
import com.love.novalue.tools.PhotoUtil;
import com.love.novalue.ui.SeePhotoActivity;
import com.maning.imagebrowserlibrary.ImageEngine;
import com.maning.imagebrowserlibrary.MNImageBrowser;
import com.maning.imagebrowserlibrary.listeners.OnClickListener;
import com.maning.imagebrowserlibrary.listeners.OnLongClickListener;
import com.maning.imagebrowserlibrary.listeners.OnPageChangeListener;
import com.maning.imagebrowserlibrary.model.ImageBrowserConfig;

import java.util.ArrayList;
import java.util.List;

public class NinePhotoAdapter extends NineGridImageViewAdapter<String> {
    private OnPhotoItemClickListener onPhotoItemClickListener;
    private List<ImageView> imageViews=new ArrayList<>();
    public OnPhotoItemClickListener getOnPhotoItemClickListener() {
        return onPhotoItemClickListener;
    }

    public void setOnPhotoItemClickListener(OnPhotoItemClickListener onPhotoItemClickListener) {
        this.onPhotoItemClickListener = onPhotoItemClickListener;
    }

    public interface  OnPhotoItemClickListener{
        void onClick(int pos,ImageView imageView);
    }

    @Override
    protected ImageView generateImageView(Context context) {
        return super.generateImageView(context);
    }

    @Override
    protected void onItemImageClick(Context context, int index, List<String> list) {
        ArrayList<String> photoList=new ArrayList<>();
        photoList.addAll(list);
        //context.startActivity(new Intent(context, SeePhotoActivity.class));
        MNImageBrowser.with(context)
                //必须-当前位置

                .setCurrentPosition(index)
                //必须-图片加载用户自己去选择
                .setImageEngine(new ImageEngine() {
                    @Override
                    public void loadImage(Context context, String url, ImageView imageView, View progressView) {
                        Glide.with(context).load(url).into(imageView);
                    }
                })
                .setIndicatorType(ImageBrowserConfig.IndicatorType.Indicator_Circle)
                //必须（setImageList和setImageUrl二选一，会覆盖）-图片集合
                .setImageList(photoList)
                //必须（setImageList和setImageUrl二选一，会覆盖）-设置单张图片

                //非必须-图片切换动画
                .setTransformType(transformType)
                //非必须-指示器样式（默认文本样式：两种模式）
                //设置隐藏指示器
                .setIndicatorHide(false)
                //设置自定义遮盖层，定制自己想要的效果，当设置遮盖层后，原本的指示器会被隐藏

                //非必须-屏幕方向：横屏，竖屏，Both（默认：横竖屏都支持）
                .setScreenOrientationType(screenOrientationType)
                //非必须-图片单击监听
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(FragmentActivity activity, ImageView view, int position, String url) {
                        //单击监听
                    }
                })
                //非必须-图片长按监听
                .setOnLongClickListener(new OnLongClickListener() {
                    @Override
                    public void onLongClick(FragmentActivity activity, ImageView imageView, int position, String url) {
                        //长按监听
                    }
                })
               .setFullScreenMode(true)
                        //手势下拉缩小效果是否开启
                        .setOpenPullDownGestureEffect(true).show(generateImageView(context));
        //onPhotoItemClickListener.onClick(index,generateImageView(context));
    }

    @Override
    protected void onDisplayImage(Context context, ImageView imageView, String s) {
        PhotoUtil.loadPic(context,s,imageView,20);

    }
    public ImageBrowserConfig.TransformType transformType = ImageBrowserConfig.TransformType.Transform_Default;
    public ImageBrowserConfig.IndicatorType indicatorType = ImageBrowserConfig.IndicatorType.Indicator_Number;
    public ImageBrowserConfig.ScreenOrientationType screenOrientationType = ImageBrowserConfig.ScreenOrientationType.Screenorientation_Default;


}
