package com.love.novalue.ui.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.love.novalue.R;
import com.love.novalue.adapter.OtherGoodsAdapter;
import com.love.novalue.adapter.OtherTypeAdapter;
import com.love.novalue.bean.OtherGoods;
import com.love.novalue.bean.OtherType;
import com.love.novalue.widget.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherHomeFragment extends Fragment {
    @BindView(R.id.rv_goods)
    RecyclerView rv_goods;
    @BindView(R.id.gridview)
    MyGridView gridview;
    OtherGoodsAdapter otherGoodsAdapter;
    List<OtherGoods> otherGoods;
    OtherTypeAdapter otherTypeAdapter;
    List<OtherType> otherTypeList;
    public OtherHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_other_home, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }
    private void initType(){
        otherTypeList=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            otherTypeList.add(new OtherType());
        }
        otherTypeAdapter=new OtherTypeAdapter(otherTypeList,getContext());
        gridview.setAdapter(otherTypeAdapter);
    }
    private void initView() {
        otherGoods=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            otherGoods.add(new OtherGoods());
        }
        otherGoodsAdapter=new OtherGoodsAdapter(otherGoods);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
        rv_goods.setLayoutManager(gridLayoutManager);
        rv_goods.setNestedScrollingEnabled(false);
        rv_goods.setHasFixedSize(true);

        rv_goods.setFocusable(false);
        rv_goods.setAdapter(otherGoodsAdapter);
        initType();
    }

    public static OtherHomeFragment newInstance(int type) {
        OtherHomeFragment otherHomeFragment = new OtherHomeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        otherHomeFragment.setArguments(bundle);
        return otherHomeFragment;
    }
}
