package com.love.novalue.ui.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.love.novalue.R;
import com.love.novalue.adapter.HomeType1Adapter;
import com.love.novalue.adapter.HomeType2Adapter;
import com.love.novalue.adapter.HomeTypeGoods;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeType2Fragment extends Fragment {
    HomeType2Adapter homeType2Adapter;
    @BindView(R.id.rv_goods)
    RecyclerView rv_goods;
    List<HomeTypeGoods> homeTypeGoods;
    public HomeType2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_home_type1, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private void initView() {
        homeTypeGoods=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            homeTypeGoods.add(new HomeTypeGoods());
        }
        homeType2Adapter=new HomeType2Adapter(homeTypeGoods);
        rv_goods.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_goods.setAdapter(homeType2Adapter);
    }

    public static HomeType2Fragment newInstance(int type) {
        HomeType2Fragment homeType1Fragment = new HomeType2Fragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        homeType1Fragment.setArguments(bundle);
        return homeType1Fragment;
    }
}
