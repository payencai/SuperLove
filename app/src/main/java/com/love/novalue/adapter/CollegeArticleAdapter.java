package com.love.novalue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.novalue.R;
import com.love.novalue.bean.CollegeArticle;

import java.util.List;

public class CollegeArticleAdapter extends BaseQuickAdapter<CollegeArticle, BaseViewHolder> {
    public CollegeArticleAdapter( @Nullable List<CollegeArticle> data) {
        super(R.layout.item_college_article, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollegeArticle item) {

    }
}
