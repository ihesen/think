package com.ihesen.think.module.mvp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihesen.think.module.mvp.vus.Vu;

/**
 * author: ihesen on 2016/4/18 16:51
 * email: hesen@ichsy.com
 */
public abstract class BasePersenterFragment<V extends Vu> extends Fragment {

    private V v;

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            v = getVuClass().newInstance();
            v.init(getLayoutInflater(savedInstanceState), container);
            onBindVu();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v.getView();
    }

    public abstract Class<V> getVuClass();

    protected void onBindVu() {
    }

}
