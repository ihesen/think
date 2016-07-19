package com.ihesen.think.bean.request;

/**
 * author: ihesen on 2016/4/21 10:40
 * email: hesen@ichsy.com
 */
public class StartPage {

    public ZooBean zoo = new ZooBean();
    public PageOption pageOption = new PageOption();

    public class ZooBean {
        public String key;
        public String token;
    }

    public class PageOption{
        public int itemCount;
        public int pageNum;
    }
}
