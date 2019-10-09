package com.love.novalue.bean;

import java.util.ArrayList;
import java.util.List;

public class SubmitOrder {
    private List<Child> childList=new ArrayList<>();

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }

    public static class Child{

    }
}
