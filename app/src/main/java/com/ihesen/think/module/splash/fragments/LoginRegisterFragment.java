package com.ihesen.think.module.splash.fragments;

import com.ihesen.think.module.mvp.fragments.BasePersenterFragment;
import com.ihesen.think.module.splash.vus.LoginRegisterVu;

/**
 * 登录 注册入口fragment
 * author: ihesen on 2016/4/19 10:51
 * email: hesen@ichsy.com
 */
public class LoginRegisterFragment extends BasePersenterFragment<LoginRegisterVu> {

    @Override
    public Class<LoginRegisterVu> getVuClass() {
        return LoginRegisterVu.class;
    }

    @Override
    protected void onBindVu() {
        super.onBindVu();

    }

    public static LoginRegisterFragment getNewInstance() {
        return new LoginRegisterFragment();
    }
}
