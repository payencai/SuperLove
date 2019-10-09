package com.love.novalue.ui.shop;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coorchice.library.SuperTextView;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gyf.immersionbar.ImmersionBar;
import com.love.novalue.R;
import com.love.novalue.adapter.GoodsDetailAdapter;
import com.love.novalue.adapter.HomeRecommendAdapter;
import com.love.novalue.adapter.LikeGoodsAdapter;
import com.love.novalue.bean.LikeGoods;
import com.love.novalue.bean.RecommendGoods;
import com.love.novalue.bean.TabEntity;
import com.love.novalue.tools.MarginUtils;

import com.love.novalue.widget.DensityUtil;
import com.love.novalue.widget.ScrollChangedScrollView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_show)
    TextView tvShow;
    @BindView(R.id.iv_show)
    ImageView ivShow;
    @BindView(R.id.iv_header)
    Banner mBanner;
    @BindView(R.id.rl_top)
    RelativeLayout rl_top;
    @BindView(R.id.tab_type)
    CommonTabLayout tabLayout;
    @BindView(R.id.rv_like)
    RecyclerView rvLike;
    @BindView(R.id.scrollView)
    ScrollChangedScrollView sv_bodyContainer;
    @BindView(R.id.rv_detail)
    RecyclerView rvDetail;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_car)
    ImageView ivCar;
    @BindView(R.id.iv_collect)
    ImageView ivCollege;
    @BindView(R.id.rv_recommend)
    RecyclerView rv_recommend;
    @BindView(R.id.main_line)
    SuperTextView mLine;
    @BindView(R.id.ll_page4)
    LinearLayout llPage4;
    @BindView(R.id.ll_page2)
    LinearLayout llPage2;
    @BindView(R.id.ll_page3)
    LinearLayout llPage3;
    @BindView(R.id.iv_notice)
    ImageView iv_notice;
    List<String> images;
    List<String> goodsImages;
    ArrayList<CustomTabEntity> tabEntities;
    LikeGoodsAdapter likeGoodsAdapter;
    List<LikeGoods> likeGoods;
    List<RecommendGoods> recommendGoods;
    ImmersionBar immersionBar;
    String TAG = "DETAIL";
    int imageHeight = 0;
    int barHeight = 0;
    boolean isShow=false;
    GoodsDetailAdapter goodsDetailAdapter;
    HomeRecommendAdapter homeRecommendAdapter;
    private boolean tagFlag = false;
    private int lastTagIndex = 0;
    private boolean content2NavigateFlagInnerLock = false;

    /**
     * 用于在同一个内容模块内滑动，锁定导航标签，防止重复刷新标签
     * true: 锁定
     * false ; 没有锁定
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化推荐列表
     */
    private void initRecommend() {
        recommendGoods = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            recommendGoods.add(new RecommendGoods());
        }
        homeRecommendAdapter = new HomeRecommendAdapter(recommendGoods);
        rv_recommend.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv_recommend.setAdapter(homeRecommendAdapter);
        final float[] endX = {0};
        rv_recommend.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //整体的总宽度，注意是整体，包括在显示区域之外的。
                int range = rv_recommend.computeHorizontalScrollRange();
                //float density = DisplayUtil.getDensity(this);
                //计算出溢出部分的宽度，即屏幕外剩下的宽度
//                    float maxEndX = range + (10 * density) + 5 - DisplayUtil.getScreenWidth(activity);
                float maxEndX = range - DensityUtil.getScreenWidth(ShopDetailActivity.this);
                mLine.setVisibility(maxEndX <= 0 ? View.GONE : View.VISIBLE);
                //滑动的距离
                endX[0] += dx;
                //计算比例
                float proportion = endX[0] / maxEndX;

                //计算滚动条宽度
                int transMaxRange = ((ViewGroup) mLine.getParent()).getWidth() - mLine.getWidth();
                //设置滚动条移动
                mLine.setTranslationX(transMaxRange * proportion);
            }

        });
    }

    private void initScrollview() {
        barHeight = MarginUtils.getStatusBarHeight(ShopDetailActivity.this) + DensityUtil.dip2px(ShopDetailActivity.this, 50);
        imageHeight = DensityUtil.dip2px(this, 300) - MarginUtils.getStatusBarHeight(this);
        sv_bodyContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //表明当前的动作是由 ScrollView 触发和主导
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    tagFlag = true;
                }
                return false;
            }
        });

        sv_bodyContainer.setScrollViewListener(new ScrollChangedScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ScrollView scrollView, int x, int y, int oldx, int oldy) {
                scrollRefreshNavigationTag(scrollView);
                if (y <= 0) {
                    //设置渐变的头部的背景颜色
                    Log.i(TAG, "y <= 0:----------->");
                    rl_top.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));
                    tabLayout.setVisibility(View.GONE);
                    immersionBar.statusBarColor(R.color.color_trans).statusBarAlpha(0).init();
                } else if (y > 0 && y <= imageHeight) {
                    //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变

                    tabLayout.setVisibility(View.VISIBLE);
                    float scale = (float) y / imageHeight;
                    int alpha = (int) (scale * 255);
                    Log.i(TAG, "alpha---->" + scale);
                    tabLayout.setAlpha(alpha);
                    rl_top.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    immersionBar.statusBarColor(R.color.color_trans).statusBarAlpha(scale).init();
                    if (oldy < y) {

                        ivBack.setImageResource(R.mipmap.sl_circle_back);
                        ivCollege.setImageResource(R.mipmap.sl_circle_collect);
                        ivCar.setImageResource(R.mipmap.sl_circle_car);
                        // 手指向上滑动，屏幕内容下滑

                    } else if (oldy > y) {
                        ivBack.setImageResource(R.mipmap.sl_shop_grey_back);

                        ivCollege.setImageResource(R.mipmap.sl_shop_grey_collect);
                        ivCar.setImageResource(R.mipmap.sl_shop_grey_car);
                        // 手指向下滑动，屏幕内容上滑

                    }
                } else {

                    tabLayout.setVisibility(View.VISIBLE);
                    rl_top.setBackgroundColor(Color.WHITE);
                    immersionBar.statusBarColor(R.color.color_black).statusBarAlpha(1).init();
                }
            }

            @Override
            public void onScrollStop(boolean isScrollStop) {

            }
        });
    }

    private void initGoodsDetail() {
        goodsImages = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            goodsImages.add("http");
        }
        goodsDetailAdapter = new GoodsDetailAdapter(goodsImages);
        rvDetail.setLayoutManager(new LinearLayoutManager(this));
        rvDetail.setAdapter(goodsDetailAdapter);
        rvDetail.setNestedScrollingEnabled(false);
    }

    private void initStatusBar() {

        immersionBar = ImmersionBar.with(this);
        immersionBar.init();
        MarginUtils.setMargin(rl_top, 0, MarginUtils.getStatusBarHeight(this), 0, 0);
    }

    private void initLikeGoods() {
        likeGoods = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            likeGoods.add(new LikeGoods());
        }
        likeGoodsAdapter = new LikeGoodsAdapter(likeGoods);
        rvLike.setLayoutManager(new GridLayoutManager(this, 2));
        rvLike.setAdapter(likeGoodsAdapter);
        rvLike.setNestedScrollingEnabled(false);

    }

    private void initTab() {
        tabEntities = new ArrayList<>();
        tabEntities.add(new TabEntity("商品"));
        tabEntities.add(new TabEntity("详情"));
        tabEntities.add(new TabEntity("须知"));
        tabEntities.add(new TabEntity("推荐"));
        tabLayout.setTabData(tabEntities);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                //表明当前的动作是由 TabLayout 触发和主导
                tagFlag = false;
                // 根据点击的位置，使ScrollView 滑动到对应区域
                // 计算点击的导航标签所对应内容区域的高度
                int targetY = 0;
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        targetY = llPage2.getTop() - barHeight;
                        break;
                    case 2:
                        targetY = llPage3.getTop() - barHeight;
                        break;
                    case 3:
                        targetY = llPage4.getTop() - barHeight;
                        break;
                }
                // 移动到对应的内容区域
                sv_bodyContainer.smoothScrollTo(0, targetY);

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void initView() {
        initTab();
        initRecommend();
        initGoodsDetail();
        initScrollview();
        initLikeGoods();
        initStatusBar();
        initBanner();
    }

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
        // mBanner.setBannerAnimation(Transformer.Tablet);
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(ShopDetailActivity.this).load((String) path).into(imageView);
            }
        });
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

    /**
     * 内容区域滑动刷新导航标签
     *
     * @param scrollView 内容模块容器
     */
    private void scrollRefreshNavigationTag(ScrollView scrollView) {
        if (scrollView == null) {
            return;
        }
        // 获得ScrollView滑动距离
        int scrollY = scrollView.getScrollY();
        // 确定ScrollView当前展示的顶部内容属于哪个内容模块
        if (scrollY > (llPage4.getTop() - barHeight)) {
            setCurrentTab(3);
        } else if (scrollY > (llPage3.getTop() - barHeight)) {
            setCurrentTab(2);

        } else if (scrollY > (llPage2.getTop() - barHeight)) {
            setCurrentTab(1);

        } else {
            setCurrentTab(0);
        }
    }

    /**
     * 刷新标签
     *
     * @param currentTagIndex 当前模块位置
     */
    private void setCurrentTab(int currentTagIndex) {
        // 上一个位置与当前位置不一致是，解锁内部锁，是导航可以发生变化
        if (lastTagIndex != currentTagIndex) {
            content2NavigateFlagInnerLock = false;
        }
        if (!content2NavigateFlagInnerLock) {
            // 锁定内部锁
            content2NavigateFlagInnerLock = true;
            // 动作是由ScrollView触发主导的情况下，导航标签才可以滚动选中
            if (tagFlag) {
                tabLayout.setCurrentTab(currentTagIndex);
            }
        }
        lastTagIndex = currentTagIndex;
    }

    @OnClick({R.id.iv_top,R.id.ll_show})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_show:
                if(isShow){
                    isShow=false;
                    tvShow.setText("点击展开");
                    ivShow.setRotation(0);
                    iv_notice.setImageResource(R.mipmap.sl_small_notice);
                    sv_bodyContainer.smoothScrollTo(0,llPage3.getTop()-barHeight);
                }else{
                    iv_notice.setImageResource(R.mipmap.sl_notice_show);
                    isShow=true;
                    tvShow.setText("点击收起");
                    ivShow.setRotation(180);

                }
                break;
            case R.id.iv_top:
                tagFlag = false;
                sv_bodyContainer.smoothScrollTo(0, 0);
                break;

        }
    }
}
