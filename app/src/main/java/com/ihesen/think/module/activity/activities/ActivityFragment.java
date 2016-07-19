package com.ihesen.think.module.activity.activities;

import com.ihesen.think.module.activity.vus.ActivityVu;
import com.ihesen.think.module.mvp.fragments.BasePersenterFragment;

/**
 * 主页动态
 * author: ihesen on 2016/4/19 16:56
 * email: hesen@ichsy.com
 */
public class ActivityFragment extends BasePersenterFragment<ActivityVu> {

    @Override
    public Class<ActivityVu> getVuClass() {
        return ActivityVu.class;
    }

    public static ActivityFragment getNewInstance() {
        return new ActivityFragment();
    }

    @Override
    protected void onBindVu() {
        super.onBindVu();

    }
}
