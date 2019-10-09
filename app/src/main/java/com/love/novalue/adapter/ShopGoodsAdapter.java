package com.love.novalue.adapter;

import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.HomeGoods;
import com.love.novalue.bean.ShopGoods;
import com.love.novalue.tools.AmountUtil;
import com.love.novalue.tools.PhotoUtil;
import com.love.novalue.tools.StringUtils;
import com.love.novalue.widget.DensityUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class ShopGoodsAdapter extends BaseQuickAdapter<ShopGoods, BaseViewHolder> {
    public ShopGoodsAdapter(@Nullable List<ShopGoods> data) {
        super(R.layout.item_shop_goods, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopGoods item) {
        RoundedImageView iv_photo=helper.getView(R.id.iv_logo);
        //PhotoUtil.loadPic(mContext,item.getMyCoverPic(),iv_photo);
        Glide.with(mContext).load(item.getMyCoverPic()).into(iv_photo);

        TextView tv_get=helper.getView(R.id.tv_get);
        TextView tv_share=helper.getView(R.id.tv_share);
        TextView tv_cheap_price=helper.getView(R.id.tv_cheap_price);
        TextView tv_name=helper.getView(R.id.tv_name);
        TextView tv_real_price=helper.getView(R.id.tv_real_price);
        TextView tv_sale=helper.getView(R.id.tv_sale);
        tv_name.setText(item.getSubject());
        try {
            tv_get.setText("赚"+ AmountUtil.changeF2Y(item.getGrossProfitPrice()));
            tv_share.setText("PLUS会员分享赚"+ AmountUtil.changeF2Y(item.getGrossProfitPrice()));
            tv_real_price.setText(""+AmountUtil.changeF2Y(item.getMySellingPrice()));
            tv_cheap_price.setText("￥"+AmountUtil.changeF2Y(item.getMyOriginalPrice()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        tv_cheap_price.setPaintFlags(tv_cheap_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        if(item.getTimeStatus()==0){
            tv_sale.setText("已抢"+"0%");
        }else{
            tv_sale.setText("已售"+item.getMyOriginalSale()+" | "+"已抢"+item.getSnapUpRate()+"%");
        }

    }
}
