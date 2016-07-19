package com.ihesen.think.bean.data;

import java.util.ArrayList;
import java.util.List;

/**
 * author: ihesen on 2016/4/20 15:07
 * email: hesen@ichsy.com
 */
public class ActivityBean {
    /**
     * 宫格图片资源
     */
    public List<Image> pics = new ArrayList<>();
    public String avatar;
    public String schoolName;
    public String message;
    /**发布时间*/
    public String pulishDate;
    /**点赞次数*/
    public int favourCount;
    /**浏览次数*/
    public int browseCount;
}
