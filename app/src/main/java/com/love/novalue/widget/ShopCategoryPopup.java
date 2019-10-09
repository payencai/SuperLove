package com.love.novalue.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.love.novalue.R;
import com.love.novalue.adapter.GridShopCategoryAdapter;
import com.love.novalue.bean.ShopCategory;
import com.lxj.xpopup.impl.PartShadowPopupView;

import java.util.ArrayList;
import java.util.List;

public class ShopCategoryPopup extends PartShadowPopupView {
    public ShopCategoryPopup(@NonNull Context context) {
        super(context);
    }



    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_shop_category;
    }


    @Override
    protected void onShow() {
        super.onShow();
        Log.e("tag","CustomPartShadowPopupView onShow");
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
        Log.e("tag","CustomPartShadowPopupView onDismiss");
    }
}
