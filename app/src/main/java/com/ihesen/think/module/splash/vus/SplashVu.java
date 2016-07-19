package com.ihesen.think.module.splash.vus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ihesen.think.R;
import com.ihesen.think.module.mvp.vus.Vu;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author: ihesen on 2016/4/18 17:21
 * email: hesen@ichsy.com
 */
public class SplashVu implements Vu {

    @Bind(R.id.imageView)
    ImageView imageView;

    private View rootView;

    @Override
    public View getView() {
        return rootView;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup viewGroup) {
        rootView = inflater.inflate(R.layout.splash_main, viewGroup, false);
        ButterKnife.bind(this, rootView);
        imageView.setBackgroundResource(R.drawable.ic_menu_gallery);
    }

}
