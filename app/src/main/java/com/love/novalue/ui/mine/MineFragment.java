package com.love.novalue.ui.mine;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.ImmersionFragment;
import com.love.novalue.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends ImmersionFragment {


    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this,view);
        return view;
    }
    @OnClick({R.id.rl_order})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.rl_order:
                startActivity(new Intent(getContext(),MyOrderActivity.class));
                break;
        }
    }
    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this).init();
    }
}
