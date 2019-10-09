package com.love.novalue.bean;

import java.util.ArrayList;
import java.util.List;

public class ShopSku {
    private String attributeId;
    private String attributeName;
    private List<Child> childList=new ArrayList<>();

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }

    public static class Child{

        /**
         * id : 343469156514676736
         * skuId : 338045208440356864
         * attributeId : 3216
         * attributeValue : 07# 魅粉色
         * skuImageUrl : https://cbu01.alicdn.com/img/ibank/2019/010/133/10834331010_1932397242.jpg
         * attributeName : 颜色
         */
        private int amountonSale;
        private String price;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getAmountonSale() {
            return amountonSale;
        }

        public void setAmountonSale(int amountonSale) {
            this.amountonSale = amountonSale;
        }

        private String id;
        private String skuId;
        private String attributeId;
        private String attributeValue;
        private String skuImageUrl;
        private String attributeName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public String getAttributeId() {
            return attributeId;
        }

        public void setAttributeId(String attributeId) {
            this.attributeId = attributeId;
        }

        public String getAttributeValue() {
            return attributeValue;
        }

        public void setAttributeValue(String attributeValue) {
            this.attributeValue = attributeValue;
        }

        public String getSkuImageUrl() {
            return skuImageUrl;
        }

        public void setSkuImageUrl(String skuImageUrl) {
            this.skuImageUrl = skuImageUrl;
        }

        public String getAttributeName() {
            return attributeName;
        }

        public void setAttributeName(String attributeName) {
            this.attributeName = attributeName;
        }
    }
}
