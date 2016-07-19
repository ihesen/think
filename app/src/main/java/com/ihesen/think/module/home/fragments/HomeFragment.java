package com.ihesen.think.module.home.fragments;

import com.ihesen.think.module.home.vus.HomeVu;
import com.ihesen.think.module.mvp.fragments.BasePersenterFragment;

/**
 * author: ihesen on 2016/4/19 16:44
 * email: hesen@ichsy.com
 */
public class HomeFragment extends BasePersenterFragment<HomeVu> {

    @Override
    public Class<HomeVu> getVuClass() {
        return HomeVu.class;
    }

    public static HomeFragment getNewInstance() {
        return new HomeFragment();
    }
}
