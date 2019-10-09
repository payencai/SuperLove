package com.love.novalue.bean;

import java.util.List;

public class LoveOrder {
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    private List<Child> children;
    public static class Child{

    }
}
