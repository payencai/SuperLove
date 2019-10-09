package com.love.novalue.ui.college;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.love.novalue.R;
import com.love.novalue.adapter.CollegeArticleAdapter;
import com.love.novalue.bean.CollegeArticle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SchoolFragment extends Fragment {

    List<CollegeArticle> collegeArticles;
    CollegeArticleAdapter collegeArticleAdapter;
    @BindView(R.id.rv_college)
    RecyclerView rv_college;
    public SchoolFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_school, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }
    @OnClick({R.id.rl_item1,R.id.rl_item2,R.id.rl_item3,R.id.rl_item4})
    void OnClick(View view){
        Intent intent=new Intent(getContext(),CollegeArticleActivity.class);
        switch (view.getId()){
            case R.id.rl_item1:
                intent.putExtra("type",1);
                startActivity(intent);
                break;
            case R.id.rl_item2:
                intent.putExtra("type",2);
                startActivity(intent);
                break;
            case R.id.rl_item3:
                intent.putExtra("type",3);
                startActivity(intent);
                break;
            case R.id.rl_item4:
                intent.putExtra("type",4);
                startActivity(intent);
                break;

        }
    }
    private void initView() {
        collegeArticles=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            collegeArticles.add(new CollegeArticle());
        }
        collegeArticleAdapter=new CollegeArticleAdapter(collegeArticles);
        rv_college.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_college.setAdapter(collegeArticleAdapter);
    }

}
