package com.ihesen.think.module.mvp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.ihesen.think.module.mvp.vus.Vu;

/**
 * author: ihesen on 2016/4/18 16:09
 * email: hesen@ichsy.com
 */
public abstract class BasePersenterActivity<V extends Vu> extends AppCompatActivity {

    public V v;
    public FragmentManager fragmentManager;

    private static BasePersenterActivity mForegroundActivity;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        try {
            v = getVuClass().newInstance();
            v.init(getLayoutInflater(), null);
            setContentView(v.getView());
            onBindVu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract Class<V> getVuClass();

    protected void onBindVu() {
    }

    @Override
    protected void onResume() {
        mForegroundActivity = this;
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (mForegroundActivity == this) {
            mForegroundActivity = null;
        }
        super.onPause();
    }

    public static BasePersenterActivity getForegroundActivity() {
        return mForegroundActivity;
    }

    public void startActivity(Class activity) {
        startActivity(new Intent(this, activity));
    }

}
