package com.love.novalue.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.data.LocalUriFetcher;
import com.love.novalue.R;
import com.love.novalue.net.NetUtils;
import com.love.novalue.net.OnMessageReceived;
import com.love.novalue.ui.center.CenterFragment;
import com.love.novalue.ui.college.CollegeFragment;
import com.love.novalue.ui.home.HomeFragment;
import com.love.novalue.ui.mine.MineFragment;
import com.love.novalue.ui.shop.ShopFragment;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    FragmentManager fm;
    List<Fragment> fragments;
    @BindView(R.id.iv_home)
    ImageView ivHome;
    @BindView(R.id.iv_shop)
    ImageView ivShop;
    @BindView(R.id.iv_college)
    ImageView ivCollege;
    @BindView(R.id.iv_mine)
    ImageView ivMine;
    HomeFragment homeFragment;
    ShopFragment shopFragment;
    CollegeFragment collegeFragment;
    CenterFragment centerFragment;
    MineFragment mineFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        testApi();
    }

    private void initView() {
        fragments = new ArrayList<>();
        fm=getSupportFragmentManager();
        setHome();
    }

    private void setHome() {
        homeFragment=new HomeFragment();
        fragments.add(homeFragment);
        fm.beginTransaction().add(R.id.fr_container, homeFragment).commit();
        showFragment(homeFragment);
    }

    private void hideAllFragment() {
        for (Fragment fragment : fragments) {
            if (fragment != null)
                fm.beginTransaction().hide(fragment).commit();
        }

    }
    private void testApi(){
        HttpParams httpParams=new HttpParams();
        httpParams.put("limit",6);
        httpParams.put("status",0);

    }
    private void reset() {
        ivHome.setImageResource(R.mipmap.sl_unselect_home);
        ivShop.setImageResource(R.mipmap.sl_unselect_shop);
        ivCollege.setImageResource(R.mipmap.sl_unselect_college);
        ivMine.setImageResource(R.mipmap.sl_unselect_mine);
    }
    private void showFragment(Fragment fragment) {
        fm.beginTransaction().show(fragment).commit();
    }

    @OnClick({R.id.rl_home, R.id.rl_shop, R.id.rl_college, R.id.rl_mine, R.id.rl_center})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.rl_home:
                reset();
                hideAllFragment();
                ivHome.setImageResource(R.mipmap.sl_select_home);
                showFragment(homeFragment);
                break;
            case R.id.rl_shop:
                if(shopFragment==null){
                    shopFragment = new ShopFragment();
                    fragments.add(shopFragment);
                    fm.beginTransaction().add(R.id.fr_container, shopFragment).commit();
                }
                reset();
                hideAllFragment();
                ivShop.setImageResource(R.mipmap.sl_select_shop);
                showFragment(shopFragment);
                break;
            case R.id.rl_college:
                if(collegeFragment==null){
                    collegeFragment = new CollegeFragment();
                    fragments.add(collegeFragment);
                    fm.beginTransaction().add(R.id.fr_container, collegeFragment).commit();
                }
                reset();
                hideAllFragment();
                ivCollege.setImageResource(R.mipmap.sl_select_college);
                showFragment(collegeFragment);
                break;
            case R.id.rl_mine:
                if(mineFragment==null){
                    mineFragment = new MineFragment();
                    fragments.add(mineFragment);
                    fm.beginTransaction().add(R.id.fr_container, mineFragment).commit();
                }
                reset();
                hideAllFragment();
                ivMine.setImageResource(R.mipmap.sl_select_mine);
                showFragment(mineFragment);
                break;
            case R.id.rl_center:
                if(centerFragment==null){
                    centerFragment = new CenterFragment();
                    fragments.add(centerFragment);
                    fm.beginTransaction().add(R.id.fr_container, centerFragment).commit();
                }
                reset();
                hideAllFragment();
                showFragment(centerFragment);
                break;
        }
    }
}
