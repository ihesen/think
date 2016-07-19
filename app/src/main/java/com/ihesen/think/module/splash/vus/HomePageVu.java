package com.ihesen.think.module.splash.vus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihesen.think.R;
import com.ihesen.think.module.mvp.vus.Vu;

/**
 * author: ihesen on 2016/4/19 14:52
 * email: hesen@ichsy.com
 */
public class HomePageVu implements Vu{

    private View rootView;

    @Override
    public View getView() {
        return rootView;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup viewGroup) {
        rootView = inflater.inflate(R.layout.home_page, viewGroup, false);
    }
}
