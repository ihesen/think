package com.ihesen.think.module.main;

import android.support.v4.app.Fragment;

import com.ihesen.think.module.activity.activities.ActivityFragment;
import com.ihesen.think.module.home.fragments.HomeFragment;
import com.ihesen.think.module.my.activities.UserCenterFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * author: ihesen on 2016/4/19 16:47
 * email: hesen@ichsy.com
 */
public class FragmentFactory {

    private static Map<Integer, Fragment> fragments = new HashMap<>();

    public static Fragment obtainFragment(int position) {
        if (fragments.get(position) != null) {
            return fragments.get(position);
        }
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = HomeFragment.getNewInstance();
                break;
            case 1:
                fragment = ActivityFragment.getNewInstance();
                break;
            case 2:
                fragment = UserCenterFragment.getNewInstance();
                break;
            default:

        }
        fragments.put(position, fragment);
        return fragment;
    }

}
