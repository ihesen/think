package com.ihesen.think.module.mvp.vus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * author: ihesen on 2016/4/18 16:06
 * email: hesen@ichsy.com
 */
public interface Vu {

    View getView();

    void init(LayoutInflater inflater, ViewGroup viewGroup);
}
