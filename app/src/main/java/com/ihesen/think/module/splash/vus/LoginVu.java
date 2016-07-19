package com.ihesen.think.module.splash.vus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ihesen.think.module.splash.activities.HomeActivity;
import com.ihesen.think.module.main.activities.MainTabActivity;
import com.ihesen.think.R;
import com.ihesen.think.module.mvp.vus.Vu;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: ihesen on 2016/4/19 15:24
 * email: hesen@ichsy.com
 */
public class LoginVu implements Vu {

    @Bind(R.id.login)
    Button login;
    private View rootView;

    @Override
    public View getView() {
        return rootView;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup viewGroup) {
        rootView = inflater.inflate(R.layout.login_vu, viewGroup, false);
        ButterKnife.bind(this, rootView);
    }

    @OnClick(R.id.login)
    public void onClick() {
        ((HomeActivity)rootView.getContext()).startActivity(MainTabActivity.class);
    }
}
