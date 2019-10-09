package com.love.novalue.ui.home;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.ImmersionFragment;
import com.jaeger.library.StatusBarUtil;
import com.love.novalue.R;
import com.love.novalue.adapter.GridShopCategoryAdapter;
import com.love.novalue.adapter.HomeGoodsAdapter;
import com.love.novalue.adapter.HomePagerAdapter;
import com.love.novalue.adapter.KillTimeAdapter;
import com.love.novalue.adapter.MyGridViewAdapter;
import com.love.novalue.adapter.MyPagerAdapter;
import com.love.novalue.bean.HomeGoods;
import com.love.novalue.bean.HomeMsg;
import com.love.novalue.bean.ItemInfo;
import com.love.novalue.bean.KillTime;
import com.love.novalue.bean.ShopCategory;
import com.love.novalue.tools.GlideImageLoader;
import com.love.novalue.widget.CustomScrollViewPager;
import com.love.novalue.widget.NoScrollViewPager;
import com.love.novalue.widget.ShopCategoryPopup;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.SimpleCallback;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends ImmersionFragment {

    @BindView(R.id.viewpager)
    CustomScrollViewPager mViewPager;
    @BindView(R.id.tab_type)
    SlidingTabLayout tabType;
    @BindView(R.id.rl_category)
    RelativeLayout rl_category;
    HomePagerAdapter homePagerAdapter;
    List<Fragment> fragments;
    List<String> titleList;
    NewHomeFragment newHomeFragment;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initView();

        return view;
    }

    @OnClick({R.id.rl_down,R.id.rl_msg,R.id.ll_search})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_search:
                startActivity(new Intent(getContext(), HomeSearchActivity.class));
                break;
            case R.id.rl_msg:
                startActivity(new Intent(getContext(), HomeMsgActivity.class));
                break;
            case R.id.rl_down:

                startActivity(new Intent(getContext(),GoodsCategoryActivity.class));
                break;

        }
    }


    private void initView() {
        titleList=new ArrayList<>();
        fragments=new ArrayList<>();
        initTab();
        homePagerAdapter=new HomePagerAdapter(getChildFragmentManager(),fragments,titleList);
        mViewPager.setScrollable(false);
        mViewPager.setAdapter(homePagerAdapter);
        tabType.setViewPager(mViewPager);


    }



    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this).statusBarColor(R.color.color_red).fitsSystemWindows(true).init();
    }
    //初始化标题
    private void initTab() {
        newHomeFragment=new NewHomeFragment();
        fragments.add(newHomeFragment);
        for (int i = 0; i <7 ; i++) {
             fragments.add(OtherHomeFragment.newInstance(i));
        }
        titleList.add("精选");
        titleList.add("女装");
        titleList.add("彩妆");
        titleList.add("洗护");
        titleList.add("电器");
        titleList.add("保健");
        titleList.add("食品");
        titleList.add("男装");
    }

}
