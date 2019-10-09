package com.love.novalue.ui.college;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.love.novalue.R;
import com.love.novalue.adapter.CollegeArticleAdapter;
import com.love.novalue.bean.CollegeArticle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollegeArticleActivity extends AppCompatActivity {
    List<CollegeArticle> collegeArticles;
    CollegeArticleAdapter collegeArticleAdapter;
    @BindView(R.id.rv_college)
    RecyclerView rv_college;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_article);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
        collegeArticles=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            collegeArticles.add(new CollegeArticle());
        }
        collegeArticleAdapter=new CollegeArticleAdapter(collegeArticles);
        rv_college.setLayoutManager(new LinearLayoutManager(this));
        rv_college.setAdapter(collegeArticleAdapter);
    }
}
