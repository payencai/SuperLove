package com.love.novalue.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.love.novalue.R;
import com.love.novalue.tools.PhotoUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import indi.liyi.viewer.ImageLoader;
import indi.liyi.viewer.ImageViewer;
import indi.liyi.viewer.ViewData;

public class SeePhotoActivity extends AppCompatActivity {
    @BindView(R.id.imageViewer)
    ImageViewer imageViewer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_photo);
        ButterKnife.bind(this);
        List<ViewData> photo=new ArrayList<>();
        ViewData viewData1=new ViewData();
        viewData1.setImageSrc("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2035109898,3819262580&fm=26&gp=0.jpg");
        ViewData viewData2=new ViewData();
        viewData2.setImageSrc("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2035109898,3819262580&fm=26&gp=0.jpg");
        ViewData viewData3=new ViewData();
        viewData3.setImageSrc("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2035109898,3819262580&fm=26&gp=0.jpg");
        photo.add(viewData1);
        photo.add(viewData2);
        photo.add(viewData3);
        imageViewer.overlayStatusBar(false) // ImageViewer 是否会占据 StatusBar 的空间
                .viewData(photo) // 数据源
                .imageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Object src, ImageView imageView, LoadCallback callback) {
                        PhotoUtil.loadPic(SeePhotoActivity.this,(String) src,imageView,20);
                    }
                }) // 设置图片加载方式
                .playEnterAnim(true) // 是否开启进场动画，默认为true
                .playExitAnim(true) // 是否开启退场动画，默认为true
                .showIndex(true) // 是否显示图片索引，默认为true
                .watch(0);
    }
}
