package com.love.novalue.net;

/**
 * 作者：凌涛 on 2019/5/5 11:17
 * 邮箱：771548229@qq..com
 */
public class Api {

    //public static final String BASE_URL="http://192.168.10.48:9122";
    public static final String BASE_URL="https://xiao-chengxu.fyaiwujia.com/awj-plus";

    public static class Shop {
        public static final String getCategoryProduct = BASE_URL + "/api/product/get/category/product";//获取商城商品分类下商品
        public static final String getCategory = BASE_URL + "/category/api/list";//获取商城商品分类
        public static final String getHotAndNine = BASE_URL + "/api/channel/list";//获取商城频道
        public static final String getBanners = BASE_URL + "/adsCarousel/list";//获取商城轮播图
    }

}
