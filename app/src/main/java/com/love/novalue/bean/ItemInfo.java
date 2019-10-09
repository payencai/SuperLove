package com.love.novalue.bean;

public class ItemInfo {
    //图片对应下的文字
    String iconName;
    //图片
    int iconId;

    public ItemInfo(String iconName, int iconId) {
        this.iconName = iconName;
        this.iconId = iconId;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

}
