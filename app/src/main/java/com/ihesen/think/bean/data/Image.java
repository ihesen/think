package com.ihesen.think.bean.data;

/**
 * author: ihesen on 2016/4/20 14:48
 * email: hesen@ichsy.com
 */
public class Image {
    private String url;

    public Image(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "image---->>url=" + url;
    }
}
