package com.love.novalue.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

public class TabEntity implements CustomTabEntity {
    private String name;

    public TabEntity(String name) {
        this.name = name;
    }

    @Override
    public String getTabTitle() {
        return name;
    }

    @Override
    public int getTabSelectedIcon() {
        return 0;
    }

    @Override
    public int getTabUnselectedIcon() {
        return 0;
    }
}
