package com.tobery.personalmusic.entity;

public class url_bean  {
    private String url;
    private String title;
    private String title_color;

    public url_bean(String url, String title, String title_color) {
        this.title = title;
        this.url = url;
        this.title_color = title_color;
    }

    public String getTitle_color() {
        return title_color;
    }

    public void setTitle_color(String title_color) {
        this.title_color = title_color;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
