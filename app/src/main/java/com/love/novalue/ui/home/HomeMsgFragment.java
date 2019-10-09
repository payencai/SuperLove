package com.love.novalue.ui.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.love.novalue.R;
import com.love.novalue.adapter.HomeMsgAdapter;
import com.love.novalue.adapter.HomeType1Adapter;
import com.love.novalue.adapter.HomeTypeGoods;
import com.love.novalue.bean.HomeMsg;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeMsgFragment extends Fragment {


    HomeMsgAdapter homeMsgAdapter;
    @BindView(R.id.rv_msg)
    RecyclerView rv_goods;
    List<HomeMsg> homeMsgs;
    public HomeMsgFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_home_msg, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private void initView() {
        homeMsgs=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            homeMsgs.add(new HomeMsg());
        }
        homeMsgAdapter=new HomeMsgAdapter(homeMsgs);
        rv_goods.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_goods.setAdapter(homeMsgAdapter);
    }

    public static HomeMsgFragment newInstance(int type) {
        HomeMsgFragment homeType1Fragment = new HomeMsgFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        homeType1Fragment.setArguments(bundle);
        return homeType1Fragment;
    }

}
