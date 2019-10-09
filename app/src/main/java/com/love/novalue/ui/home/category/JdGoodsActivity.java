package com.love.novalue.ui.home.category;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gyf.immersionbar.ImmersionBar;
import com.liang.widget.JTabLayout;
import com.love.novalue.R;
import com.love.novalue.adapter.HomeJDAdapter;
import com.love.novalue.adapter.HomePinddAdapter;
import com.love.novalue.bean.HomeCategoryGoods;
import com.love.novalue.tools.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JdGoodsActivity extends AppCompatActivity {


    @BindView(R.id.jtab)
    JTabLayout jTabLayout;
    HomeJDAdapter homeTaobaoAdapter;
    List<HomeCategoryGoods> homeCategoryGoods;
    @BindView(R.id.rv_goods)
    RecyclerView rv_goods;
    @BindView(R.id.banner)
    Banner mBanner;
    List<String> images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jd_goods);
        ButterKnife.bind(this);
        ImmersionBar.with(this).navigationBarColor(R.color.color_red3).init();
        initView();
    }
    private void initGoods(){
        homeCategoryGoods=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            homeCategoryGoods.add(new HomeCategoryGoods());
        }
        homeTaobaoAdapter=new HomeJDAdapter(homeCategoryGoods);
        rv_goods.setLayoutManager(new LinearLayoutManager(this));
        rv_goods.setAdapter(homeTaobaoAdapter);
        rv_goods.setFocusable(false);
        rv_goods.setNestedScrollingEnabled(false);
    }
    private void initView() {
        initTab();
        initGoods();
        initBanner();
    }
    //初始化轮播图
    private void initBanner() {
        images = new ArrayList<>();
        images.add("http://bpic.588ku.com/element_origin_min_pic/00/00/07/105781f1cfe8506.jpg");
        images.add("http://bpic.588ku.com/element_origin_min_pic/00/00/07/105781f0b09a030.jpg");
        images.add("http://ku.90sjimg.com/element_origin_min_pic/00/00/07/105781f567334a1.jpg");
        images.add("http://ku.90sjimg.com/element_origin_min_pic/00/00/07/105781f0ddd2bf3.jpg");
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
        mBanner.setBannerAnimation(Transformer.ScaleInOut);
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集 合
        mBanner.setImages(images);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(5000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }
    private void initTab() {
        jTabLayout.addTab(jTabLayout.newTab().setTitle("优选"));
        jTabLayout.addTab(jTabLayout.newTab().setTitle("拼多多"));
        jTabLayout.addTab(jTabLayout.newTab().setTitle("天猫"));
        jTabLayout.addTab(jTabLayout.newTab().setTitle("京东"));
        jTabLayout.addTab(jTabLayout.newTab().setTitle("美团"));
        jTabLayout.addTab(jTabLayout.newTab().setTitle("聚划算"));
    }
}
