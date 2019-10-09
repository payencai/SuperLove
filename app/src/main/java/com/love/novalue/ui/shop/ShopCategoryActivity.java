package com.love.novalue.ui.shop;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.coorchice.library.utils.LogUtils;
import com.google.gson.Gson;
import com.love.novalue.R;
import com.love.novalue.adapter.GridShopCategoryAdapter;
import com.love.novalue.adapter.ShopCategoryAdapter;
import com.love.novalue.adapter.ShopCategoryGoodsAdapter;
import com.love.novalue.adapter.ShopLeftAdapter;
import com.love.novalue.bean.ShopCategory;
import com.love.novalue.bean.ShopCategoryGoods;
import com.love.novalue.bean.ShopLeft;
import com.love.novalue.net.Api;
import com.love.novalue.net.NetUtils;
import com.love.novalue.net.OnMessageReceived;
import com.love.novalue.ui.home.HomeMsgActivity;
import com.love.novalue.ui.mine.MyBalanceActivity;
import com.love.novalue.widget.DensityUtil;
import com.love.novalue.widget.ShopCategoryPopup;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.SimpleCallback;

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

public class ShopCategoryActivity extends AppCompatActivity {
    ShopCategoryGoodsAdapter shopCategoryGoodsAdapter;
    ShopLeftAdapter shopLeftAdapter;
    ShopCategoryAdapter shopCategoryAdapter;
    List<ShopCategory.ChildListBean> shopLefts;
    List<ShopCategoryGoods> shopCategoryGoods;
    List<ShopCategory> shopCategories;
    @BindView(R.id.ll_left)
    ListView ll_left;
    @BindView(R.id.iv_sort)
    ImageView iv_sort;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_sale)
    TextView tvSale;
    @BindView(R.id.rl_top)
    RelativeLayout rl_top;
    @BindView(R.id.rv_right)
    RecyclerView rv_right;
    @BindView(R.id.rv_type)
    RecyclerView rv_type;
    LinearLayoutManager linearLayoutManager;
    GridShopCategoryAdapter gridShopCategoryAdapter;
    int pos=0;
    int page=1;
    ShopCategory.ChildListBean currentCategory;
    GridView gridView;
    boolean isLoadMore=false;
    String order="";//排序asc
    String sidx="";//2.MY_ORIGINAL_SALE  1.MY_SELLING_PRICE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_category);
        ButterKnife.bind(this);
        pos=getIntent().getIntExtra("pos",0);
        initView();
    }
    ShopCategoryPopup popupView;
    private void showPartShadow(final View v){

        if(popupView==null){
            popupView = (ShopCategoryPopup) new XPopup.Builder(this)
                    .atView(v)
                    .autoOpenSoftInput(true)
                    .setPopupCallback(new SimpleCallback() {
                        @Override
                        public void onShow() {

                        }
                        @Override
                        public void onDismiss() {

                        }
                    })
                    .asCustom(new ShopCategoryPopup(this));
        }
        View contentView=popupView.getPopupContentView();
        gridView=contentView.findViewById(R.id.gridview);
        contentView.findViewById(R.id.rl_down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupView.dismiss();
            }
        });
        gridShopCategoryAdapter=new GridShopCategoryAdapter(this,shopCategories);
        gridShopCategoryAdapter.setSelect(pos);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos=position;
                gridShopCategoryAdapter.setSelect(position);
                gridShopCategoryAdapter.notifyDataSetChanged();
                currentCategory=shopCategories.get(pos).getChildList().get(0);
                shopCategoryAdapter.setSelect(pos);
                shopCategoryAdapter.notifyDataSetChanged();
                rv_type.smoothScrollToPosition(pos);
                shopLefts.clear();
                shopLefts.addAll(shopCategories.get(pos).getChildList());
                shopLeftAdapter.setSelect(0);
                shopLeftAdapter.notifyDataSetChanged();
                shopCategoryGoods.clear();
                page=1;
                shopCategoryGoodsAdapter.setNewData(shopCategoryGoods);
                getCategoryGoods();
            }
        });
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gridView.setAdapter(gridShopCategoryAdapter);
        popupView.show();

    }
    private void refreshData(){
        shopCategoryGoods.clear();
        page=1;
        shopCategoryGoodsAdapter.setNewData(shopCategoryGoods);
        getCategoryGoods();
    }
    @OnClick({R.id.rl_down,R.id.iv_back,R.id.tv_sale,R.id.ll_price})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.rl_down:
                showPartShadow(rl_top);
                break;
            case R.id.ll_price:
                tvSale.setTextColor(getResources().getColor(R.color.color_b5));
                if(TextUtils.isEmpty(order)){
                    order="desc";
                    sidx="MY_SELLING_PRICE";
                    iv_sort.setImageResource(R.mipmap.sl_price_down);
                    tvPrice.setTextColor(getResources().getColor(R.color.color_red4));
                }else if("desc".equals(order)){
                    order="asc";
                    sidx="MY_SELLING_PRICE";
                    iv_sort.setImageResource(R.mipmap.sl_category_up);
                    tvPrice.setTextColor(getResources().getColor(R.color.color_red4));
                }else if("asc".equals(order)){
                    order="";
                    sidx="";
                    iv_sort.setImageResource(R.mipmap.sl_grey_sort);
                    tvPrice.setTextColor(getResources().getColor(R.color.color_b5));
                }
                refreshData();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_sale:
                iv_sort.setImageResource(R.mipmap.sl_grey_sort);
                tvPrice.setTextColor(getResources().getColor(R.color.color_b5));
                if(TextUtils.isEmpty(sidx)){
                    sidx="MY_ORIGINAL_SALE";
                    order="desc";
                    tvSale.setTextColor(getResources().getColor(R.color.color_red4));
                }else if("MY_SELLING_PRICE".equals(sidx)){
                    sidx="MY_ORIGINAL_SALE";
                    order="desc";
                    tvSale.setTextColor(getResources().getColor(R.color.color_red4));
                }else{
                    sidx="";
                    order="";
                    tvSale.setTextColor(getResources().getColor(R.color.color_b5));
                }
                refreshData();
                break;
        }
    }
    private void initLeft(){
        shopLefts=new ArrayList<>();
        shopLeftAdapter=new ShopLeftAdapter(this,shopLefts);
        ll_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(shopLeftAdapter.getSelect()==position){
                    return;
                }
                shopLeftAdapter.setSelect(position);
                shopLeftAdapter.notifyDataSetChanged();
                currentCategory=shopLefts.get(position);
                refreshData();
            }
        });
        ll_left.setAdapter(shopLeftAdapter);
    }
    private void initGoods(){
        shopCategoryGoods=new ArrayList<>();


        shopCategoryGoodsAdapter=new ShopCategoryGoodsAdapter(shopCategoryGoods);
        shopCategoryGoodsAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                isLoadMore=true;
                getCategoryGoods();
            }
        },rv_right);
        rv_right.setLayoutManager(new LinearLayoutManager(this));
        rv_right.setAdapter(shopCategoryGoodsAdapter);
    }
    private void initCategory(){
        shopCategories=new ArrayList<>();
        shopCategoryAdapter=new ShopCategoryAdapter(shopCategories);
        shopCategoryAdapter.setSelect(pos);
        shopCategoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                pos=position;
                selectCategory(view);
            }
        });
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_type.setLayoutManager(linearLayoutManager);
        rv_type.setAdapter(shopCategoryAdapter);

    }
    private void selectCategory(View view){
        currentCategory=shopCategories.get(pos).getChildList().get(0);
        shopCategoryAdapter.setSelect(pos);
        shopCategoryAdapter.notifyDataSetChanged();
        int size = shopCategories.size();
        if (size>2) {
            if ( pos> 1 && pos< size - 2) {
                moveToCenter(view);
            } else if (pos>= 0 && pos< 2) {
                rv_type.smoothScrollToPosition(0);
            } else {
                rv_type.smoothScrollToPosition(size-1);
            }
        }
        shopLefts.clear();
        shopLefts.addAll(shopCategories.get(pos).getChildList());
        shopLeftAdapter.setSelect(0);
        shopLeftAdapter.notifyDataSetChanged();
        refreshData();
    }
    private void getCategoryGoods(){
        Map<String,Object> httpParams=new HashMap<>();
        if(!TextUtils.isEmpty(sidx))
           httpParams.put("sidx",sidx);//类型
        if(!TextUtils.isEmpty(order))
           httpParams.put("order",order);//排序
        httpParams.put("limit",10);
        httpParams.put("myCatId",currentCategory.getId());
        httpParams.put("page",page);
        String json=new Gson().toJson(httpParams);
        Log.e("json",json);
        NetUtils.getInstance().post(Api.Shop.getCategoryProduct, json, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.i(response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int code=jsonObject.getInt("resultCode");
                    if(code==0){
                        jsonObject=jsonObject.getJSONObject("data");
                        JSONArray array=jsonObject.getJSONArray("list");
                        List<ShopCategoryGoods> shopCategoryGoodsList=new ArrayList<>();
                        for (int i = 0; i <array.length() ; i++) {
                            JSONObject item=array.getJSONObject(i);
                            ShopCategoryGoods goods=new Gson().fromJson(item.toString(),ShopCategoryGoods.class);
                            shopCategoryGoodsList.add(goods);
                        }
                        shopCategoryGoodsAdapter.addData(shopCategoryGoodsList);
                        if(isLoadMore){
                            isLoadMore=false;
                            if(array==null||array.length()==0){
                                shopCategoryGoodsAdapter.loadMoreEnd();
                            }else{
                                shopCategoryGoodsAdapter.loadMoreComplete();
                            }
                        }else{
                            shopCategoryGoodsAdapter.loadMoreComplete();
                        }

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


    private void initView() {
        initCategory();
        initGoods();
        initLeft();
        getGoodsCategory();
    }
    private void moveToCenter(View itemView) {
        int[] locationView = new int[2];
        itemView.getLocationOnScreen(locationView);
        int viewWidth = itemView.getWidth();
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int centerX = dm.widthPixels / 2;
        int distance = locationView[0] - centerX + viewWidth / 2;
        rv_type.smoothScrollBy(distance, 0);
    }
    private void getGoodsCategory(){
        Map<String,Object> httpParams=new HashMap<>();
        httpParams.put("isBan","0");
        httpParams.put("limit",50);
        httpParams.put("page",1);
        final String json=new Gson().toJson(httpParams);
        LogUtils.e(json);
        NetUtils.getInstance().post(Api.Shop.getCategory, json, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                //LogUtils.e(response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int code=jsonObject.getInt("resultCode");
                    if(code==0){
                        jsonObject=jsonObject.getJSONObject("data");
                        JSONArray array=jsonObject.getJSONArray("list");
                        for (int i = 0; i <array.length() ; i++) {
                            JSONObject item=array.getJSONObject(i);
                            ShopCategory shopCategory=new Gson().fromJson(item.toString(),ShopCategory.class);
                            shopCategories.add(shopCategory);
                        }
                        shopCategoryAdapter.setNewData(shopCategories);
                        if(shopCategories.size()>0){
                            currentCategory=shopCategories.get(pos).getChildList().get(0);
                            shopLefts.addAll(shopCategories.get(0).getChildList());
                            shopLeftAdapter.notifyDataSetChanged();
                        }
                        if(pos>0){
                            linearLayoutManager.scrollToPositionWithOffset(pos,0);
                        }
                        getCategoryGoods();
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
}
