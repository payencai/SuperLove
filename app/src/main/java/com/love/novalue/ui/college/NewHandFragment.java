package com.love.novalue.ui.college;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.love.novalue.R;
import com.love.novalue.adapter.CollegeShareAdapter;
import com.love.novalue.adapter.CollegeTypeAdapter;
import com.love.novalue.bean.CollegeShare;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewHandFragment extends Fragment {


    CollegeTypeAdapter collegeTypeAdapter;
    CollegeShareAdapter collegeShareAdapter;
    List<CollegeShare> collegeShares;
    List<String> shopList;
    @BindView(R.id.rv_type)
    RecyclerView rv_type;
    @BindView(R.id.rv_goods)
    RecyclerView rv_goods;
    public NewHandFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_new_hand, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private void initView() {
        initShare();
        initTab();
    }

    private void initShare(){
        collegeShares=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            collegeShares.add(new CollegeShare());
        }
        collegeShareAdapter=new CollegeShareAdapter(collegeShares);
        rv_goods.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_goods.setAdapter(collegeShareAdapter);
    }
    //初始化标题
    private void initTab() {
        shopList = new ArrayList<>();
        shopList.add("全部");
        shopList.add("小白第一天");
        shopList.add("小白第二天");
        shopList.add("小白第三天");

        collegeTypeAdapter = new CollegeTypeAdapter(shopList);
        collegeTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                collegeTypeAdapter.setSelect(position);
                collegeTypeAdapter.notifyDataSetChanged();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_type.setLayoutManager(linearLayoutManager);
        rv_type.setAdapter(collegeTypeAdapter);
    }

}
