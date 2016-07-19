package com.ihesen.imageloader;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * author: ihesen on 2016/7/13 17:50
 * email: hesen@ichsy.com
 */
public class PathUtils {

    public static File getDiskCacheDir(Context context, String childPath) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + childPath);
    }
}
