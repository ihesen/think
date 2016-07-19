package com.ihesen.think.module.base;

import com.ihesen.think.module.mvp.activities.BasePersenterActivity;
import com.ihesen.think.module.mvp.vus.Vu;

/**
 * author: ihesen on 2016/4/18 18:20
 * email: hesen@ichsy.com
 */
public abstract class BaseActivity<V extends Vu> extends BasePersenterActivity<BaseActivityVu> {

    private static BaseActivity mForegroundActivity;

    @Override
    public Class<BaseActivityVu> getVuClass() {
        return BaseActivityVu.class;
    }

    @Override
    protected final void onBindVu() {
        try {
            V containerVu = getContainerVuClass().newInstance();
            containerVu.init(getLayoutInflater(), null);
            v.getContainerView().addView(containerVu.getView());
            onContainerBindVu();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
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

    public static BaseActivity getForegroundActivity() {
        return mForegroundActivity;
    }

    public abstract Class<V> getContainerVuClass();

    public abstract void onContainerBindVu();
}
