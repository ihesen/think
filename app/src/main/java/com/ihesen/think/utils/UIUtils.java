package com.ihesen.think.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.ihesen.think.AppApplication;
import com.ihesen.think.module.base.BaseActivity;
import com.ihesen.think.R;

/**
 * author: ihesen on 2016/4/22 14:22
 * email: hesen@ichsy.com
 */
public class UIUtils {


    public static Context getContext() {
        return AppApplication.getApplication();
    }

    public static Thread getMainThread() {
        return AppApplication.getMainThread();
    }

    public static long getMainThreadId() {
        return AppApplication.getMainThreadId();
    }

    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * px转换dip
     */
    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getHandler() {
        // 获得主线程的looper
        Looper mainLooper = AppApplication.getMainThreadLooper();
        // 获取主线程的handler
        Handler handler = new Handler(mainLooper);
        return handler;
    }

    /**
     * 延时在主线程执行runnable
     */
    public static boolean postDelayed(Runnable runnable, long delayMillis) {
        return getHandler().postDelayed(runnable, delayMillis);
    }

    /**
     * 在主线程执行runnable
     */
    public static boolean post(Runnable runnable) {
        return getHandler().post(runnable);
    }

    /**
     * 从主线程looper里面移除runnable
     */
    public static void removeCallbacks(Runnable runnable) {
        getHandler().removeCallbacks(runnable);
    }

    public static View inflate(int resId) {
        return LayoutInflater.from(getContext()).inflate(resId, null);
    }

    /**
     * 获取资源
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获取文字
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 获取文字数组
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 获取dimen
     */
    public static int getDimens(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /**
     * 获取drawable
     */
    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    /**
     * 获取颜色
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 获取颜色选择器
     */
    public static ColorStateList getColorStateList(int resId) {
        return getResources().getColorStateList(resId);
    }

    public static boolean isRunInMainThread() {
        return android.os.Process.myTid() == getMainThreadId();
    }

    public static void runInMainThread(Runnable runnable) {
        if (isRunInMainThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    public static void startActivity(Intent intent) {
        BaseActivity activity = BaseActivity.getForegroundActivity();
        if (activity != null) {
            activity.startActivity(intent);
        } else {
            System.out.println("Activity为空!");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        }
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public static void startActivityForResult(Intent intent, int requestCode) {
        BaseActivity activity = BaseActivity.getForegroundActivity();
        if (activity != null) {
            activity.startActivityForResult(intent, requestCode);
        } else {
            System.out.println("Activity为空!");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        }
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 对toast的简易封装。线程安全，可以在非UI线程调用。
     */
    public static void showToastSafe(final int resId) {
        showToastSafe(getString(resId));
    }

    /**
     * 对toast的简易封装。线程安全，可以在非UI线程调用。
     */
    public static void showToastSafe(final String str) {
        if (isRunInMainThread()) {
            LogUtil.hLog().e("ui线程!");
            showToast(str);
        } else {
            LogUtil.hLog().e("非ui线程!");
            post(new Runnable() {
                @Override
                public void run() {
                    showToast(str);
                }
            });
        }
    }

    private static void showToast(String str) {
        BaseActivity frontActivity = BaseActivity.getForegroundActivity();
//		if (frontActivity != null) {
        Toast.makeText(UIUtils.getContext(), str, Toast.LENGTH_LONG).show();
//		}
    }
}
