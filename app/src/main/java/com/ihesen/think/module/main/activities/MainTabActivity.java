package com.ihesen.think.module.main.activities;

import com.ihesen.think.module.main.vus.MainTabVu;
import com.ihesen.think.module.mvp.activities.BasePersenterActivity;

/**
 * 主页面(tab菜单项)
 * author: ihesen on 2016/4/19 15:40
 * email: hesen@ichsy.com
 */
public class MainTabActivity extends BasePersenterActivity<MainTabVu> {

    @Override
    public Class<MainTabVu> getVuClass() {
        return MainTabVu.class;
    }
}
