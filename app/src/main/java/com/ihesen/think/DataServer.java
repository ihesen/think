package com.ihesen.think;

import com.ihesen.think.bean.data.ActivityBean;
import com.ihesen.think.bean.data.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * author: ihesen on 2016/7/19 16:20
 * email: hesen@ichsy.com
 */
public class DataServer {

    public static List<ActivityBean> getActivityData(int length) {
        List<ActivityBean> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            ActivityBean bean = new ActivityBean();
            for (int j = 0; j < 9; j++) {
                if (j % 2 == 0) {
                    bean.pics.add(new Image("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png"));
                } else {
                    bean.pics.add(new Image("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2909526449,2384267136&fm=80"));
                }
            }
            list.add(bean);
        }
        return list;
    }
}
