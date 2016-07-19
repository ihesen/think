package com.ihesen.think.utils;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * author: ihesen on 2016/4/22 14:46
 * email: hesen@ichsy.com
 */
public class DialogUtil {
    public static void setFullAndBottom(AlertDialog dialog, Activity activity) {
        WindowManager wManager = activity.getWindowManager();
        Display display = wManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); // 设置宽度
        dialog.getWindow().setAttributes(lp);

        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
        // window.setWindowAnimations(R.style.mystyle); // 添加动画
    }
}
