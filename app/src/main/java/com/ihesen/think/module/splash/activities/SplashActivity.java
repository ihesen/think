package com.ihesen.think.module.splash.activities;

import android.os.Handler;

import com.ihesen.think.module.mvp.activities.BasePersenterActivity;
import com.ihesen.think.module.splash.vus.SplashVu;


/**
 * author: ihesen on 2016/4/18 17:20
 * email: hesen@ichsy.com
 */
public class SplashActivity extends BasePersenterActivity<SplashVu> {

    @Override
    public Class<SplashVu> getVuClass() {
        return SplashVu.class;
    }

    @Override
    protected void onBindVu() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(HomeActivity.class);
                finish();
            }
        }, 2000);
    }
}
