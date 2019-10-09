package com.love.novalue.ui.mine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.love.novalue.R;
import com.love.novalue.adapter.LoveOrderAdapter;
import com.love.novalue.bean.LoveOrder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrderFragment extends Fragment {
    List<LoveOrder> loveOrders;
    LoveOrderAdapter loveOrderAdapter;
    @BindView(R.id.rv_order)
    RecyclerView rv_order;
    View emptyView;
    public MyOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_my_order, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private void initView() {
        loveOrders=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            loveOrders.add(new LoveOrder());
        }
        loveOrderAdapter=new LoveOrderAdapter(loveOrders);
        rv_order.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_order.setAdapter(loveOrderAdapter);
    }

    public static MyOrderFragment newInstance(int type){
        MyOrderFragment myOrderFragment=new MyOrderFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("type",type);
        myOrderFragment.setArguments(bundle);
        return myOrderFragment;
    }

}
