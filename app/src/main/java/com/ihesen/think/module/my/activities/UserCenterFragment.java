package com.ihesen.think.module.my.activities;

import com.ihesen.think.module.mvp.fragments.BasePersenterFragment;
import com.ihesen.think.module.my.vus.UserCenterVu;

/**
 * 我的
 * author: ihesen on 2016/4/19 16:59
 * email: hesen@ichsy.com
 */
public class UserCenterFragment extends BasePersenterFragment<UserCenterVu> {

    @Override
    public Class<UserCenterVu> getVuClass() {
        return UserCenterVu.class;
    }

    public static UserCenterFragment getNewInstance() {
        return new UserCenterFragment();
    }

}
