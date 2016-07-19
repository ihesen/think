package com.ihesen.think.module.splash.activities;

import com.ihesen.think.R;
import com.ihesen.think.module.base.BaseActivity;
import com.ihesen.think.module.splash.fragments.LoginRegisterFragment;
import com.ihesen.think.module.splash.vus.HomePageVu;

/**
 * 登录注册主页面
 * author: ihesen on 2016/4/19 10:33
 * email: hesen@ichsy.com
 */
public class HomeActivity extends BaseActivity<HomePageVu> {

    @Override
    public Class<HomePageVu> getContainerVuClass() {
        return HomePageVu.class;
    }

    @Override
    public void onContainerBindVu() {
        v.setToolbarText("登录");
        fragmentManager.beginTransaction().replace(R.id.framelayout, LoginRegisterFragment.getNewInstance()).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
