package com.love.novalue.ui.college;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.ImmersionFragment;
import com.love.novalue.R;
import com.love.novalue.adapter.CollegeShareAdapter;
import com.love.novalue.adapter.CollegeTypeAdapter;
import com.love.novalue.adapter.DanmuEntityAdapter;
import com.love.novalue.adapter.HomePagerAdapter;
import com.love.novalue.bean.CollegeShare;
import com.love.novalue.bean.DanmuEntity;
import com.love.novalue.ui.home.HomeFragment;
import com.love.novalue.widget.CustomScrollViewPager;
import com.love.novalue.widget.barrage.BarrageView;
import com.love.novalue.widget.barrage.BarrageViewBean;
import com.orzangleli.xdanmuku.DanmuContainerView;
import com.orzangleli.xdanmuku.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollegeFragment extends ImmersionFragment {
    @BindView(R.id.viewpager)
    CustomScrollViewPager viewPager;
    @BindView(R.id.danmu)
    BarrageView barrageView;
    @BindView(R.id.tab_type)
    SlidingTabLayout tab_type;
    ArrayList<String> titleList;
    String [] titels={"商品推荐","营销素材","新手必发","商学院"};
    ArrayList<Fragment> fragments;

    List<BarrageViewBean> barrageViewBeans;

    public CollegeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_college, container, false);
        ;
        ButterKnife.bind(this, view);
        initView();
        return view;
    }
    HomePagerAdapter homePagerAdapter;
    private void initView() {
        fragments=new ArrayList<>();
        titleList=new ArrayList<>();
        titleList.add(titels[0]);
        titleList.add(titels[1]);
        titleList.add(titels[2]);
        titleList.add(titels[3]);
        fragments.add(new GoodRecommendFragment());
        fragments.add(new MaterialFragment());
        fragments.add(new NewHandFragment());
        fragments.add(new SchoolFragment());
        barrageViewBeans=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            barrageViewBeans.add(new BarrageViewBean("哆*在20分钟前分享发圈赚了"+i+"元","https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2380312545,209326876&fm=26&gp=0.jpg"));
        }
        barrageView.setData(barrageViewBeans);
        barrageView.start();
        homePagerAdapter=new HomePagerAdapter(getChildFragmentManager(),fragments,titleList);
        viewPager.setScrollable(false);
        viewPager.setAdapter(homePagerAdapter);
        tab_type.setViewPager(viewPager);
    }


    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this).init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        barrageView.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        barrageView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        barrageView.onResume();
    }
}
