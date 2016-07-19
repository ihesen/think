package com.ihesen.think.module.my.vus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihesen.think.R;
import com.ihesen.think.module.mvp.vus.Vu;

/**
 * author: ihesen on 2016/4/19 17:00
 * email: hesen@ichsy.com
 */
public class UserCenterVu implements Vu{

    private View rootView;

    @Override
    public View getView() {
        return rootView;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup viewGroup) {
        rootView = inflater.inflate(R.layout.user_center_vu, viewGroup, false);
    }
}
