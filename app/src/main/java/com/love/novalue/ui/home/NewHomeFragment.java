package com.love.novalue.ui.home;


import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.love.novalue.R;
import com.love.novalue.adapter.CenterLayoutManager;
import com.love.novalue.adapter.HomeCategoryAdapter;
import com.love.novalue.adapter.HomeGoodsAdapter;
import com.love.novalue.adapter.HomeNineAdapter;
import com.love.novalue.adapter.KillTimeAdapter;
import com.love.novalue.adapter.MyGridViewAdapter;
import com.love.novalue.adapter.MyPagerAdapter;
import com.love.novalue.bean.HomeCategory;
import com.love.novalue.bean.HomeGoods;
import com.love.novalue.bean.ItemInfo;
import com.love.novalue.bean.KillTime;
import com.love.novalue.tools.GlideImageLoader;
import com.love.novalue.ui.home.category.ChongzhiActivity;
import com.love.novalue.ui.home.category.JdGoodsActivity;
import com.love.novalue.ui.home.category.JuhuasuanActivity;
import com.love.novalue.ui.home.category.NineMoneyActivity;
import com.love.novalue.ui.home.category.PinduoduoActivity;
import com.love.novalue.ui.home.category.TaobaoActivity;
import com.love.novalue.ui.home.category.TodayHotActivity;
import com.love.novalue.ui.home.category.TodayRecommendActivity;
import com.love.novalue.ui.home.category.YoukaActivity;
import com.love.novalue.widget.CustomScrollView;
import com.love.novalue.widget.DensityUtil;
import com.love.novalue.widget.SimpleCircleIndicator;
import com.love.novalue.widget.list.HorizontalPageLayoutManager;
import com.love.novalue.widget.list.PagingScrollHelper;
import com.love.novalue.widget.pager.PageIndicatorView;
import com.love.novalue.widget.pager.PageRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.DummyPagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewHomeFragment extends Fragment {
    List<String> images;
    List<View> mViewList;

    List<KillTime> killTimes;
    List<HomeGoods> homeGoods;
    HomeGoodsAdapter homeGoodsAdapter;
    @BindView(R.id.rv_kill)
    RecyclerView rvKill;
    @BindView(R.id.main_line)
    View mLine;
    @BindView(R.id.rv_goods)
    RecyclerView rv_goods;
    @BindView(R.id.rv_page)
    RecyclerView rv_page;

    @BindView(R.id.magic_indicator1)
    MagicIndicator magicIndicator;
    KillTimeAdapter killTimeAdapter;
    private static final String[] CHANNELS = new String[]{"CUPCAKE", "DONUT"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    PagingScrollHelper scrollHelper = new PagingScrollHelper();
    HomeCategoryAdapter pageAdapter;
    List<HomeCategory> homeCategories;

    CenterLayoutManager centerLayoutManager;
    public NewHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_new_home, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }
    @OnClick({R.id.ll_type1,R.id.ll_type2,R.id.ll_type3,R.id.ll_type4})
    void OnClick(View view) {
        switch (view.getId()) {

            case R.id.ll_type1:
                startActivity(new Intent(getContext(),HomeType1Activity.class));
                break;
            case R.id.ll_type2:
                startActivity(new Intent(getContext(),HomeType2Activity.class));
                break;
            case R.id.ll_type3:
                startActivity(new Intent(getContext(),HomeType3Activity.class));
                break;
            case R.id.ll_type4:
                startActivity(new Intent(getContext(),HomeType4Activity.class));
                break;
        }
    }

    private void intHomeCategory(){
        String[] iconName = getResources().getStringArray(R.array.home_labels);
        TypedArray array = getResources().obtainTypedArray(R.array.home_icon);

        homeCategories=new ArrayList<>();
        for (int i = 0; i <17 ; i++) {
            homeCategories.add(new HomeCategory(iconName[i],array.getResourceId(i, 0)));
        }
        pageAdapter = new HomeCategoryAdapter(homeCategories);
        pageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 16:
                        startActivity(new Intent(getContext(), YoukaActivity.class));
                        break;
                    case 15:
                        startActivity(new Intent(getContext(), ChongzhiActivity.class));
                        break;
                    case 10:
                        startActivity(new Intent(getContext(), TaobaoActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(getContext(), JuhuasuanActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(getContext(), NineMoneyActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(getContext(), TodayHotActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(getContext(), TodayRecommendActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getContext(), PinduoduoActivity.class));
                        break;
                    case 0:
                        startActivity(new Intent(getContext(), JdGoodsActivity.class));
                        break;
                }
            }
        });
        HorizontalPageLayoutManager horizontalPageLayoutManager=new HorizontalPageLayoutManager(2,5);
        rv_page.setLayoutManager(horizontalPageLayoutManager);
        rv_page.setAdapter(pageAdapter);
        scrollHelper.setUpRecycleView(rv_page);
        scrollHelper.setOnPageChangeListener(new PagingScrollHelper.onPageChangeListener() {
            @Override
            public void onPageChange(int index) {

                int transMaxRange = ((ViewGroup) mLine.getParent()).getWidth() - mLine.getWidth();
                if(index==1){
                    mLine.setTranslationX(transMaxRange);
                }else{
                    mLine.setTranslationX(0);
                }

            }
        });
        scrollHelper.updateLayoutManger();
        scrollHelper.scrollToPosition(0);
        rv_page.setHorizontalScrollBarEnabled(true);
        //获取总页数,采用这种方法才能获得正确的页数。否则会因为RecyclerView.State 缓存问题，页数不正确。


    }
    private void initView() {
        initBanner();
        initGoods();
        initKillTime();
        intHomeCategory();
        initMagicIndicator1();
    }
    private void initMagicIndicator1() {

        magicIndicator.setBackgroundColor(Color.LTGRAY);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                return new DummyPagerTitleView(context);
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                float lineHeight = context.getResources().getDimension(R.dimen.small_navigator_height);
                indicator.setLineHeight(lineHeight);
                indicator.setRoundRadius(5);
                indicator.setColors(Color.parseColor("#FE3F49"));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }
    private void initGoods() {
        homeGoods = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            homeGoods.add(new HomeGoods());
        }
        homeGoodsAdapter = new HomeGoodsAdapter(homeGoods);
        homeGoodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getContext(),HomeGoodsDetailActivity.class));
            }
        });
        rv_goods.setLayoutManager(new LinearLayoutManager(getContext()) {
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

    private void initKillTime() {
        killTimes = new ArrayList<>();
        killTimes.add(new KillTime("14:00", 0));
        killTimeAdapter = new KillTimeAdapter(killTimes);
        killTimeAdapter.setPos(0);
        killTimeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                killTimeAdapter.setPos(position);
                killTimeAdapter.notifyDataSetChanged();
                int size = killTimes.size();
                if (size>2) {
                    if ( position> 1 && position< size - 2) {
                        moveToCenter(view);
                    } else if (position>= 0 && position< 2) {
                        rvKill.smoothScrollToPosition(0);
                    } else {
                        rvKill.smoothScrollToPosition(size-1);
                    }
                }
            }
        });
        centerLayoutManager = new CenterLayoutManager(getContext());
        centerLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvKill.setLayoutManager(centerLayoutManager);
        rvKill.setAdapter(killTimeAdapter);
//        final float[] endX = {0};
//        rvKill.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                //整体的总宽度，注意是整体，包括在显示区域之外的。
//                int range = rvKill.computeHorizontalScrollRange();
//                //float density = DisplayUtil.getDensity(this);
//                //计算出溢出部分的宽度，即屏幕外剩下的宽度
////                    float maxEndX = range + (10 * density) + 5 - DisplayUtil.getScreenWidth(activity);
//                float maxEndX = range - DensityUtil.getScreenWidth(getActivity());
//                mLine.setVisibility(maxEndX <= 0 ? View.GONE : View.VISIBLE);
//                //滑动的距离
//                endX[0] += dx;
//                //计算比例
//                float proportion = endX[0] / maxEndX;
//
//                //计算滚动条宽度
//                int transMaxRange = ((ViewGroup) mLine.getParent()).getWidth() - mLine.getWidth();
//                //设置滚动条移动
//                mLine.setTranslationX(transMaxRange * proportion);
//            }
//
//        });

    }
    private void moveToCenter(View itemView) {
        int[] locationView = new int[2];
        itemView.getLocationOnScreen(locationView);
        int viewWidth = itemView.getWidth();
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int centerX = dm.widthPixels / 2;
        int distance = locationView[0] - centerX + viewWidth / 2;
        rvKill.smoothScrollBy(distance, 0);
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
//                MyBanner myBanner=myBanners.get(position);
//                if(TextUtils.isEmpty(myBanner.getHyperlink())){
//                    PhotoUtil.seeBigPhoto(getContext(),position,images,mBanner.getChildAt(position));
//                }else{
//                    Intent intent=new Intent(getContext(), WebviewActivity.class);
//                    intent.putExtra("img",myBanner.getHyperlink());
//                    startActivityForResult(intent,1);
//                }
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





}
