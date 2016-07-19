package com.ihesen.think.module.splash.fragments;

import com.ihesen.think.module.mvp.fragments.BasePersenterFragment;
import com.ihesen.think.module.splash.vus.RegisterVu;

/**
 * author: ihesen on 2016/4/19 15:23
 * email: hesen@ichsy.com
 */
public class RegisterFragment extends BasePersenterFragment<RegisterVu> {

    @Override
    public Class<RegisterVu> getVuClass() {
        return RegisterVu.class;
    }

    @Override
    protected void onBindVu() {
        super.onBindVu();
    }

    public static RegisterFragment getNewInstance(){
        return new RegisterFragment();
    }
}
