package com.love.novalue.bean;

public class ShopBanners {

    /**
     * imgUrl : string
     * limit : 0
     * linkUrl : string
     * status : 0
     */

    private String imgUrl;
    private int limit;
    private String linkUrl;
    private int status;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
