package com.love.novalue.bean;

import java.util.List;

public class ShopCategory {

    public ShopCategory(String name) {
        this.name = name;
    }

    /**
     * id : 336216798685020160
     * name : 家居百货
     * prntId : 0
     * level : 1
     * sortNum : 5
     * childList : [{"id":"336217249778221056","name":"家居百货","prntId":"336216798685020160","level":2,"sortNum":1,"childList":null,"imgUrl":null,"isBan":"0"}]
     * imgUrl : http://img-awjplus.fyaiwujia.com/awj/2019100810280620?Expires=1571972915&OSSAccessKeyId=LTAI4FpWdfcFwWNuhTVKhszj&Signature=kF3Z02e%2Bd1N8g6%2BEyptccd2TAwg%3D
     * isBan : 0
     */

    private String id;
    private String name;
    private String prntId;
    private int level;
    private int sortNum;
    private String imgUrl;
    private String isBan;
    private List<ChildListBean> childList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrntId() {
        return prntId;
    }

    public void setPrntId(String prntId) {
        this.prntId = prntId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIsBan() {
        return isBan;
    }

    public void setIsBan(String isBan) {
        this.isBan = isBan;
    }

    public List<ChildListBean> getChildList() {
        return childList;
    }

    public void setChildList(List<ChildListBean> childList) {
        this.childList = childList;
    }

    public static class ChildListBean {
        /**
         * id : 336217249778221056
         * name : 家居百货
         * prntId : 336216798685020160
         * level : 2
         * sortNum : 1
         * childList : null
         * imgUrl : null
         * isBan : 0
         */

        private String id;
        private String name;
        private String prntId;
        private int level;
        private int sortNum;
        private Object childList;
        private Object imgUrl;
        private String isBan;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrntId() {
            return prntId;
        }

        public void setPrntId(String prntId) {
            this.prntId = prntId;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getSortNum() {
            return sortNum;
        }

        public void setSortNum(int sortNum) {
            this.sortNum = sortNum;
        }

        public Object getChildList() {
            return childList;
        }

        public void setChildList(Object childList) {
            this.childList = childList;
        }

        public Object getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(Object imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getIsBan() {
            return isBan;
        }

        public void setIsBan(String isBan) {
            this.isBan = isBan;
        }
    }
}
