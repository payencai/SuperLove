package com.love.novalue.ui.shop;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.coorchice.library.SuperTextView;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.love.novalue.R;
import com.love.novalue.adapter.GoodsDetailAdapter;
import com.love.novalue.adapter.HomeRecommendAdapter;
import com.love.novalue.adapter.LikeGoodsAdapter;
import com.love.novalue.adapter.ShopNumDialogAdapter;
import com.love.novalue.bean.LikeGoods;
import com.love.novalue.bean.RecommendGoods;
import com.love.novalue.bean.ShopDetail;
import com.love.novalue.bean.ShopGoods;
import com.love.novalue.bean.ShopSku;
import com.love.novalue.bean.TabEntity;
import com.love.novalue.net.Api;
import com.love.novalue.net.NetUtils;
import com.love.novalue.net.OnMessageReceived;
import com.love.novalue.tools.AmountUtil;
import com.love.novalue.tools.MarginUtils;

import com.love.novalue.tools.PhotoUtil;
import com.love.novalue.tools.StringUtils;
import com.love.novalue.tools.TimeUtil;
import com.love.novalue.widget.DensityUtil;
import com.love.novalue.widget.ScrollChangedScrollView;
import com.love.novalue.widget.X5WebView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;

public class ShopDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_show)
    TextView tvShow;
    @BindView(R.id.tv_recommend)
    TextView tv_recommend;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_sale1)
    TextView tv_sale1;
    @BindView(R.id.tv_sale2)
    TextView tv_sale2;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_get)
    TextView tv_get;
    @BindView(R.id.cv_kill)
    CountdownView countdownView;
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
    @BindView(R.id.tv_kill_price1)
    TextView tv_kill_price1;
    @BindView(R.id.tv_kill_price2)
    TextView tv_kill_price2;
    @BindView(R.id.tv_price1)
    TextView tv_price1;
    @BindView(R.id.tv_price2)
    TextView tv_price2;
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
    @BindView(R.id.rl_kill)
    RelativeLayout rlKill;
    @BindView(R.id.rl_commom)
    RelativeLayout rl_commom;
    @BindView(R.id.webview)
    X5WebView x5WebView;
    List<String> images;
    List<String> goodsImages;
    List<String> photoList;
    ArrayList<CustomTabEntity> tabEntities;
    LikeGoodsAdapter likeGoodsAdapter;
    List<LikeGoods> likeGoods;
    List<ShopSku> shopSkus;
    List<RecommendGoods> recommendGoods;
    ImmersionBar immersionBar;
    ShopDetail shopDetail;
    String TAG = "DETAIL";
    int imageHeight = 0;
    int barHeight = 0;
    boolean isShow = false;
    String productId;
    String status;
    int num=1;
    GoodsDetailAdapter goodsDetailAdapter;
    HomeRecommendAdapter homeRecommendAdapter;
    private boolean tagFlag = false;
    private int lastTagIndex = 0;
    int currentSelect=0;
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
        status=getIntent().getStringExtra("status");
        productId = getIntent().getStringExtra("id");
        currentSelect=getIntent().getIntExtra("pos",0);
        initView();
    }

    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>html{padding:15px;} body{word-wrap:break-word;font-size:13px;padding:0px;margin:0px} p{padding:0px;margin:0px;font-size:13px;color:#222222;line-height:1.3;} img{padding:0px,margin:0px;max-width:100%; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }
    private void initKill(){
        LogUtils.e(status+"-"+currentSelect);
        switch (currentSelect){
            case 0:
                if("0".equals(status)){
                    tvStatus.setText("距开始");
                    countdownView.start(TimeUtil.getTypeTime(0));
                }else{
                    tvStatus.setText("距结束");
                    countdownView.start(TimeUtil.getTypeTime(4));
                }
                break;
            case 1:
                if("1".equals(status)){
                    tvStatus.setText("距开始");
                    countdownView.start(TimeUtil.getTypeTime(1));
                }else{
                    tvStatus.setText("距结束");
                    countdownView.start(TimeUtil.getTypeTime(4));
                }
                break;
            case 2:
                if("2".equals(status)){
                    tvStatus.setText("距开始");
                    countdownView.start(TimeUtil.getTypeTime(2));
                }else{
                    tvStatus.setText("距结束");
                    countdownView.start(TimeUtil.getTypeTime(4));
                }
                break;
            case 3:
                if("3".equals(status)){
                    tvStatus.setText("距开始");
                    countdownView.start(TimeUtil.getTypeTime(3));
                }else{
                    tvStatus.setText("距结束");
                    countdownView.start(TimeUtil.getTypeTime(4));
                }

                break;
        }
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
        getDetail();

    }

    private void initBanner() {
        images = new ArrayList<>();

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

    @OnClick({R.id.iv_top, R.id.ll_show,R.id.rl_sku})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.rl_sku:
                showSkuDialog();
                break;
            case R.id.ll_show:
                if (isShow) {
                    isShow = false;
                    tvShow.setText("点击展开");
                    ivShow.setRotation(0);
                    iv_notice.setImageResource(R.mipmap.sl_small_notice);
                    sv_bodyContainer.smoothScrollTo(0, llPage3.getTop() - barHeight);
                } else {
                    iv_notice.setImageResource(R.mipmap.sl_notice_show);
                    isShow = true;
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

    private void getDetail() {
        Map<String, Object> httpParams = new HashMap<>();
        httpParams.put("productId", productId);
        final String json = new Gson().toJson(httpParams);
        NetUtils.getInstance().post(Api.Shop.getDetail, json, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        jsonObject = jsonObject.getJSONObject("data");
                        shopDetail = new Gson().fromJson(jsonObject.toString(), ShopDetail.class);
                        initData();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //com.coorchice.library.utils.LogUtils.d(response);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void initData() {
        shopSkus = new ArrayList<>();
        Log.e("name", shopDetail.getProductSkuList().size() + "");
        for (int i = 0; i < shopDetail.getProductSkuList().size(); i++) {
            ShopDetail.ProductSkuListBean productSkuListBean = shopDetail.getProductSkuList().get(i);
            for (int j = 0; j < productSkuListBean.getAttributeList().size(); j++) {
                ShopDetail.ProductSkuListBean.AttributeListBean attributeListBean = productSkuListBean.getAttributeList().get(j);
                if (isShouldAddToSku(attributeListBean.getAttributeName())) {
                    ShopSku shopSku = new ShopSku();
                    shopSku.setAttributeId(attributeListBean.getAttributeId());
                    shopSku.setAttributeName(attributeListBean.getAttributeName());
                    shopSkus.add(shopSku);
                }
                ShopSku.Child child = new ShopSku.Child();
                child.setId(attributeListBean.getId());
                child.setAttributeId(attributeListBean.getAttributeId());
                child.setAttributeName(attributeListBean.getAttributeName());
                child.setAttributeValue(attributeListBean.getAttributeValue());
                child.setSkuImageUrl(attributeListBean.getSkuImageUrl());
                child.setAmountonSale(productSkuListBean.getAmountonSale());
                child.setPrice(productSkuListBean.getPrice());
                for (int k = 0; k < shopSkus.size(); k++) {
                    if (shopSkus.get(k).getAttributeName().equals(child.getAttributeName())) {
                        if(isShouldAddToChild(k,child.getAttributeValue())){
                            shopSkus.get(k).getChildList().add(child);
                        }

                    }
                }


            }
        }
        for (int i = 0; i <shopSkus.size() ; i++) {
            Log.e("size", shopSkus.get(i).getChildList().size() + "");
        }
        String images=shopDetail.getProductDetailsApi().getMyPics();
        photoList= StringUtils.StringToArrayList(images,",");
        mBanner.update(photoList);
        try {
            tv_get.setText("赚"+AmountUtil.changeF2Y(shopDetail.getProductDetailsApi().getGrossProfitPrice()));
            tv_price1.setText(""+AmountUtil.changeF2Y(shopDetail.getProductDetailsApi().getMySellingPrice()));
            tv_price2.setText("￥"+AmountUtil.changeF2Y(shopDetail.getProductDetailsApi().getMyOriginalPrice()));
            tv_kill_price1.setText(""+AmountUtil.changeF2Y(shopDetail.getProductDetailsApi().getMySellingPrice()));
            tv_kill_price2.setText("￥"+AmountUtil.changeF2Y(shopDetail.getProductDetailsApi().getMyOriginalPrice()));
            tv_name.setText(shopDetail.getProductDetailsApi().getSubject());
            tv_kill_price2.setPaintFlags(tv_kill_price2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tv_price2.setPaintFlags(tv_price2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tv_sale1.setText("销量"+shopDetail.getProductDetailsApi().getMyOriginalSale());
            tv_sale2.setText("销量"+shopDetail.getProductDetailsApi().getMyOriginalSale());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!TextUtils.isEmpty(status)){
            initKill();
            rlKill.setVisibility(View.VISIBLE);
        }else{
            rl_commom.setVisibility(View.VISIBLE);
        }

        x5WebView.loadData(getHtmlData(shopDetail.getProductDetailsApi().getDescription()), "text/html;charset=utf-8","utf-8");
        x5WebView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent ev) {

                x5WebView.requestDisallowInterceptTouchEvent(true);

                return false;
            }
        });
    }

    private boolean isShouldAddToSku(String id) {
        boolean isOk = true;
        for (int i = 0; i < shopSkus.size(); i++) {
            if (id.equals(shopSkus.get(i).getAttributeName())) {
                isOk = false;
                break;
            }
        }
        return isOk;
    }

    private boolean isShouldAddToChild(int k, String value) {
        boolean isOk = true;
        for (int j = 0; j < shopSkus.get(k).getChildList().size(); j++) {
            if(value.equals(shopSkus.get(k).getChildList().get(j).getAttributeValue())){
                isOk=false;
                break;
            }
        }
        return isOk;
    }

    private void showSkuDialog() {

        final Dialog dialog = new Dialog(this, R.style.BottomDialog);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_shopcar_num, null);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        final TextView tv_num=view.findViewById(R.id.tv_num);
        ImageView iv_photo = view.findViewById(R.id.iv_photo);
        PhotoUtil.loadPic(this,photoList.get(0),iv_photo,DensityUtil.dip2px(this,5));
        ListView listView = view.findViewById(R.id.listview);
        ShopNumDialogAdapter shopNumDialogAdapter=new ShopNumDialogAdapter(this,shopSkus);
        listView.setAdapter(shopNumDialogAdapter);
        TextView tvPrice1 = view.findViewById(R.id.tv_price1);
        TextView tvPrice2 = view.findViewById(R.id.tv_price2);
        try {
            tvPrice1.setText(AmountUtil.changeF2Y(shopDetail.getProductDetailsApi().getMySellingPrice()));
            tvPrice2.setText(AmountUtil.changeF2Y(shopDetail.getProductDetailsApi().getMyOriginalPrice()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.findViewById(R.id.rl_div).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num <= 1) {
                    return;
                }
                num--;
                tv_num.setText(num+"");
            }
        });
        view.findViewById(R.id.rl_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num++;
                tv_num.setText(num+"");
            }
        });
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        view.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.setContentView(view);
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
    }
}
