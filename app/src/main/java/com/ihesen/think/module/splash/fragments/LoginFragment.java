package com.ihesen.think.module.splash.fragments;

import com.ihesen.think.module.mvp.fragments.BasePersenterFragment;
import com.ihesen.think.module.splash.vus.LoginVu;

/**
 * author: ihesen on 2016/4/19 15:23
 * email: hesen@ichsy.com
 */
public class LoginFragment extends BasePersenterFragment<LoginVu> {

    @Override
    public Class<LoginVu> getVuClass() {
        return LoginVu.class;
    }

    @Override
    protected void onBindVu() {
        super.onBindVu();
    }

    public static LoginFragment getNewInstance(){
        return new LoginFragment();
    }
}
