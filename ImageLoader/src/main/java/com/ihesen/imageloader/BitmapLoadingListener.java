package com.ihesen.imageloader;

import android.graphics.Bitmap;

/**
 * author: ihesen on 2016/7/13 17:25
 * email: hesen@ichsy.com
 */
public interface BitmapLoadingListener {
    void onSuccess(Bitmap b);
    void onError();
}
