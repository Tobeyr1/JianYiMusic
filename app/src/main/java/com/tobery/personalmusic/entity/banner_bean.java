package com.tobery.personalmusic.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 首页轮播图bean
 * Created By Tobey on 2020/9/10
 */
public class banner_bean implements Serializable {


    /**
     * banners : [{"pic":"http://p1.music.126.net/t1_KzNb5-4LKRzGqYQA81A==/109951167565475950.jpg","titleColor":"blue","typeTitle":"独家策划","url":"https://y.music.163.com/m/at/62a071f79c36d0102fa27345","bannerId":"1655555430984479"}]
     * code : 200
     */

    private int code;
    private List<BannersBean> banners;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public static class BannersBean implements Serializable {
        /**
         * pic : http://p1.music.126.net/t1_KzNb5-4LKRzGqYQA81A==/109951167565475950.jpg
         * titleColor : blue
         * typeTitle : 独家策划
         * url : https://y.music.163.com/m/at/62a071f79c36d0102fa27345
         * bannerId : 1655555430984479
         */

        private String pic;
        private String titleColor;
        private String typeTitle;
        private String url;
        private String bannerId;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitleColor() {
            return titleColor;
        }

        public void setTitleColor(String titleColor) {
            this.titleColor = titleColor;
        }

        public String getTypeTitle() {
            return typeTitle;
        }

        public void setTypeTitle(String typeTitle) {
            this.typeTitle = typeTitle;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getBannerId() {
            return bannerId;
        }

        public void setBannerId(String bannerId) {
            this.bannerId = bannerId;
        }
    }
}
