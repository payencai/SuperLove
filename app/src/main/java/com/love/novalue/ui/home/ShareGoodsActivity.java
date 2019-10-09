package com.love.novalue.ui.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.love.novalue.R;
import com.love.novalue.adapter.SharePhotoAdapter;
import com.love.novalue.bean.SharePhoto;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShareGoodsActivity extends AppCompatActivity {
    SharePhotoAdapter sharePhotoAdapter;
    @BindView(R.id.rv_photos)
    RecyclerView rv_photos;
    List<SharePhoto> sharePhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_goods);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        sharePhotos=new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            SharePhoto sharePhoto=new SharePhoto();
            if(i==0){
                sharePhoto.setCheck(true);
            }
            sharePhotos.add(sharePhoto);
        }
        sharePhotoAdapter=new SharePhotoAdapter(sharePhotos);
        rv_photos.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rv_photos.setAdapter(sharePhotoAdapter);
    }
}
