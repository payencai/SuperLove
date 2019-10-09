package com.love.novalue.ui.shop;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.coorchice.library.utils.LogUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.ImmersionFragment;
import com.love.novalue.R;
import com.love.novalue.adapter.GoodsTypeAdapter;
import com.love.novalue.adapter.HomeGoodsAdapter;
import com.love.novalue.adapter.KillTimeAdapter;
import com.love.novalue.adapter.MyGridViewAdapter;
import com.love.novalue.adapter.MyPagerAdapter;
import com.love.novalue.adapter.NewKillTimeAdapter;
import com.love.novalue.adapter.ShopGoodsAdapter;
import com.love.novalue.adapter.ShopHotAdapter;
import com.love.novalue.bean.GoodsType;
import com.love.novalue.bean.HomeGoods;
import com.love.novalue.bean.HomeMsg;
import com.love.novalue.bean.ItemInfo;
import com.love.novalue.bean.KillTime;
import com.love.novalue.bean.Result;
import com.love.novalue.bean.ShopBanners;
import com.love.novalue.bean.ShopCategory;
import com.love.novalue.bean.ShopChannel;
import com.love.novalue.bean.ShopGoods;
import com.love.novalue.net.Api;
import com.love.novalue.net.NetUtils;
import com.love.novalue.net.OnMessageReceived;
import com.love.novalue.tools.GlideImageLoader;
import com.love.novalue.tools.GsonUtil;
import com.love.novalue.ui.home.HomeFragment;
import com.love.novalue.ui.home.HomeMsgActivity;
import com.love.novalue.ui.login.LoginQrcodeActivity;
import com.love.novalue.ui.mine.MyBalanceActivity;
import com.love.novalue.widget.MyGridView;
import com.lzy.okgo.model.HttpParams;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends ImmersionFragment {
    @BindView(R.id.gv_kill)
    MyGridView gvKill;
    @BindView(R.id.rv_type)
    RecyclerView rvType;
    @BindView(R.id.rv_goods)
    RecyclerView rv_goods;
    NewKillTimeAdapter killTimeAdapter;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.gv_hot)
    MyGridView gvChannel;
    @BindView(R.id.rl_balance)
    RelativeLayout rl_balance;
    List<String> images;
    List<ShopBanners> shopBanners;
    List<KillTime> killTimes;
    List<ShopGoods> homeGoods;
    List<ShopCategory>goodsTypes;
    List<ShopChannel> shopChannels;
    ShopGoodsAdapter homeGoodsAdapter;
    GoodsTypeAdapter goodsTypeAdapter;
    ShopHotAdapter shopHotAdapter;
    int currentSelect=0;
    public ShopFragment() {
        // Required empty public constrrv_goodsuctor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        ButterKnife.bind(this, view);
        initView();

        return view;
    }

    private void initView() {

        initGoods();
        initKillTime();
        initBanner();
        initGoodsType();
        initChannel();
        getRealData();
    }
    private void getRealData(){
        getBanners();//获取轮播图
        getChannel();//获取频道
        getGoodsCategory();//获取分类
    }
    @OnClick({R.id.iv_ear,R.id.ll_search,R.id.rl_msg,R.id.rl_shopcar,R.id.rl_balance,R.id.ll_category})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.iv_ear:
                startActivity(new Intent(getContext(), LoginQrcodeActivity.class));
                break;
            case R.id.ll_category://跳转分类
                startActivity(new Intent(getContext(),ShopCategoryActivity.class));
                break;
            case R.id.rl_balance://跳转金融
                startActivity(new Intent(getContext(), MyBalanceActivity.class));
                break;
            case R.id.rl_msg://跳转消息中心
                startActivity(new Intent(getContext(), HomeMsgActivity.class));
                break;
            case R.id.ll_search://跳转搜索
                startActivity(new Intent(getContext(),ShopSearchActivity.class));
                break;
            case R.id.rl_shopcar://跳转购物车
                startActivity(new Intent(getContext(),ShopCarActivity.class));
                break;
        }
    }
    //初始化频道
    private void initChannel(){
        shopChannels=new ArrayList<>();
        shopHotAdapter=new ShopHotAdapter(getContext(),shopChannels);
        gvChannel.setAdapter(shopHotAdapter);
        gvChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    startActivity(new Intent(getContext(),HotGoodsActivity.class));
                }else if(position==1){
                    startActivity(new Intent(getContext(),NewGoodsActivity.class));
                }
            }
        });
    }
    //初始化秒杀时间
    private void initKillTime() {
        killTimes = new ArrayList<>();


        Calendar cal = Calendar.getInstance();// 当前日期
        int hour = cal.get(Calendar.HOUR_OF_DAY);// 获取小时
        Log.e("Hour",hour+"hour");

        if(hour<10){
            killTimes.add(new KillTime("10:00", 0));
            killTimes.add(new KillTime("13:00", 0));
            killTimes.add(new KillTime("18:00", 0));
            killTimes.add(new KillTime("21:00", 3));
        }else{
            if(hour<=13) {
                killTimes.add(new KillTime("10:00", 2));
                killTimes.add(new KillTime("13:00", 0));
                killTimes.add(new KillTime("18:00", 0));
                killTimes.add(new KillTime("21:00", 0));
            }else if(hour<=18) {
                killTimes.add(new KillTime("10:00", 1));
                killTimes.add(new KillTime("13:00", 2));
                killTimes.add(new KillTime("18:00", 0));
                killTimes.add(new KillTime("21:00", 0));
            }else if(hour<=21){
                killTimes.add(new KillTime("10:00", 1));
                killTimes.add(new KillTime("13:00", 1));
                killTimes.add(new KillTime("18:00", 2));
                killTimes.add(new KillTime("21:00", 0));
            }else{
                killTimes.add(new KillTime("10:00", 1));
                killTimes.add(new KillTime("13:00", 1));
                killTimes.add(new KillTime("18:00", 1));
                killTimes.add(new KillTime("21:00", 2));
            }
        }
        killTimeAdapter = new NewKillTimeAdapter(getContext(),killTimes);
        killTimeAdapter.setPos(currentSelect);
        gvKill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentSelect=position;
                killTimeAdapter.setPos(currentSelect);
                killTimeAdapter.notifyDataSetChanged();
            }
        });
        gvKill.setAdapter(killTimeAdapter);
    }
    //初始化轮播图
    private void initBanner() {
        shopBanners=new ArrayList<>();
        images = new ArrayList<>();
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ShopBanners myBanner=shopBanners.get(position);
                Intent intent=new Intent(getContext(),ShopDetailActivity.class);
                intent.putExtra("data",myBanner.getLinkUrl());
                startActivity(intent);
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
    //初始化分类列表
    private void initGoodsType(){
        goodsTypes=new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        goodsTypeAdapter=new GoodsTypeAdapter(goodsTypes);
        goodsTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(getContext(),ShopCategoryActivity.class);
                intent.putExtra("pos",position);
                startActivity(intent);
            }
        });

        rvType.setLayoutManager(linearLayoutManager);
        rvType.setAdapter(goodsTypeAdapter);
    }
    //初始化秒杀列表
    private void initGoods() {
        homeGoods=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            homeGoods.add(new ShopGoods());
        }
        homeGoodsAdapter=new ShopGoodsAdapter(homeGoods);
        homeGoodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getContext(),ShopDetailActivity.class));
            }
        });
        rv_goods.setLayoutManager(new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                //解决ScrollView里存在多个RecyclerView时滑动卡顿的问题
                //如果你的RecyclerView是水平滑动的话可以重写canScrollHorizontally方法
                return false;
            }
        });

        rv_goods.setNestedScrollingEnabled(false);
        rv_goods.setHasFixedSize(true);

        rv_goods.setFocusable(false);
        rv_goods.setAdapter(homeGoodsAdapter);
    }

    private void getGoodsCategory(){
        Map<String,Object> httpParams=new HashMap<>();
        httpParams.put("isBan","0");
        httpParams.put("limit",8);
        httpParams.put("page",1);
        final String json=new Gson().toJson(httpParams);
        LogUtils.e(json);
        NetUtils.getInstance().post(Api.Shop.getCategory, json, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int code=jsonObject.getInt("resultCode");
                    if(code==0){
                        jsonObject=jsonObject.getJSONObject("data");
                        JSONArray array=jsonObject.getJSONArray("list");
                        for (int i = 0; i <array.length() ; i++) {
                            JSONObject item=array.getJSONObject(i);
                            ShopCategory shopCategory=new Gson().fromJson(item.toString(),ShopCategory.class);
                            goodsTypes.add(shopCategory);
                        }
                        goodsTypeAdapter.setNewData(goodsTypes);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }
    private void getBanners(){
        HttpParams httpParams=new HttpParams();
        httpParams.put("limit",6);
        httpParams.put("status",1);
        String json=new Gson().toJson(httpParams);
        NetUtils.getInstance().post(Api.Shop.getBanners, json, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                Result<List<ShopBanners>> result= GsonUtil.fromJsonArray(response,ShopBanners.class);
                if(result.resultCode==0){
                    shopBanners.addAll(result.getData());
                    List<String> newImageList=new ArrayList<>();
                    for (int i = 0; i <shopBanners.size() ; i++) {
                        newImageList.add(shopBanners.get(i).getImgUrl());
                    }
                    mBanner.update(newImageList);
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }
    private void getChannel(){
        HttpParams httpParams=new HttpParams();
        httpParams.put("type",1);
        String json=new Gson().toJson(httpParams);
        NetUtils.getInstance().post(Api.Shop.getHotAndNine, json, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                Result<List<ShopChannel>> result= GsonUtil.fromJsonArray(response,ShopChannel.class);
                if(result.resultCode==0){
                    shopChannels.addAll(result.getData());
                    shopHotAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }
    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this).statusBarColor(R.color.color_red).fitsSystemWindows(true).init();
    }



}
