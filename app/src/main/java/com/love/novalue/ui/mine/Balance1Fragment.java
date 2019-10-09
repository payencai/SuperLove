package com.love.novalue.ui.mine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.love.novalue.R;
import com.love.novalue.adapter.TotalBalanceAdapter;
import com.love.novalue.bean.MyBalance;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Balance1Fragment extends Fragment {

    List<MyBalance> myBalances;
    TotalBalanceAdapter totalBalanceAdapter;
    @BindView(R.id.rv_balance)
    RecyclerView rvBalance;
    public Balance1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_balance1, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private void initView() {
        myBalances=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            myBalances.add(new MyBalance());
        }
        totalBalanceAdapter=new TotalBalanceAdapter(myBalances);
        rvBalance.setLayoutManager(new LinearLayoutManager(getContext()));
        rvBalance.setAdapter(totalBalanceAdapter);
    }

}
