package com.love.novalue.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class HomePagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;
    List<String> titlesList;

    public HomePagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titlesList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titlesList = titlesList;
    }

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titlesList.get(position);
    }
}
