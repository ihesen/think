package com.ihesen.think.module.main.activities;

import com.ihesen.think.module.main.vus.MainTabVu;
import com.ihesen.think.module.base.BaseActivity;

/**
 * 主页面(tab菜单项)
 * author: ihesen on 2016/4/19 15:40
 * email: hesen@ichsy.com
 */
public class MainTabActivity extends BaseActivity<MainTabVu> {

    @Override
    public Class<MainTabVu> getContainerVuClass() {
        return MainTabVu.class;
    }

    @Override
    public void onContainerBindVu() {

    }
}
