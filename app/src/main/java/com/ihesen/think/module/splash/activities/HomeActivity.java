package com.ihesen.think.module.splash.activities;

import com.ihesen.think.R;
import com.ihesen.think.module.mvp.activities.BasePersenterActivity;
import com.ihesen.think.module.splash.fragments.LoginRegisterFragment;
import com.ihesen.think.module.splash.vus.HomePageVu;

/**
 * 登录注册主页面
 * author: ihesen on 2016/4/19 10:33
 * email: hesen@ichsy.com
 */
public class HomeActivity extends BasePersenterActivity<HomePageVu> {

    @Override
    public Class<HomePageVu> getVuClass() {
        return HomePageVu.class;
    }

    @Override
    protected void onBindVu() {
        v.setToolbarText("登录");
        fragmentManager.beginTransaction().replace(R.id.framelayout, LoginRegisterFragment.getNewInstance()).commit();
    }
}
