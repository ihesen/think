package com.ihesen.think.customview.refreshdrawable;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import com.ihesen.refresh.PullRefreshLayout;
import com.ihesen.refresh.RefreshDrawable;
import com.ihesen.think.R;

import java.util.HashMap;
import java.util.Map;


/**
 * author: ihesen on 2016/7/7 15:23
 * email: hesen@ichsy.com
 */
public class AmberRefreshDrawable extends RefreshDrawable {

    private boolean isRunning;
    private Paint mPaint;
    private int position = 0;
    private int[] refreshResources;
    private Map<Integer, Bitmap> bitmapMap = new HashMap<>();
    private int mTop;
    private Handler mHandler = new Handler();
    private int mDiameter;
    /**
     * 滑动到多少开始 启动绘制
     */
    private int maxDrapHeight;
    private int picCount;
    private int widowWidth;

    public AmberRefreshDrawable(Context context, PullRefreshLayout layout) {
        super(context, layout);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDiameter = dp2px(45);
        maxDrapHeight = (getRefreshLayout().getFinalOffset() - mDiameter) / 2;
        mTop = -mDiameter - maxDrapHeight;

        TypedArray array = context.getResources().obtainTypedArray(R.array.refresh_pics);
        int len = array.length();
        refreshResources = new int[len];
        for (int i = 0; i < len; i++) {
            refreshResources[i] = array.getResourceId(i, 0);
        }
        array.recycle();
        picCount = refreshResources.length;

        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Activity.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(dm);
        widowWidth = dm.widthPixels;
    }

    private Runnable mAnimationTask = new Runnable() {
        @Override
        public void run() {
            if (isRunning()) {
                position++;
                invalidateSelf();
                mHandler.postDelayed(this, 100);
            }
        }
    };

    @Override
    public void setPercent(float percent) {

    }

    @Override
    public void setColorSchemeColors(int[] colorSchemeColors) {

    }

    @Override
    public void offsetTopAndBottom(int offset) {
        mTop += offset;
        invalidateSelf();
        if (mTop >= maxDrapHeight) {
            start();
        } else {
            stop();
        }
    }

    @Override
    public void start() {
        if (!isRunning) {
            isRunning = true;
            mHandler.post(mAnimationTask);
        }
    }

    @Override
    public void stop() {
        position = position % picCount;
        isRunning = false;
        mHandler.removeCallbacks(mAnimationTask);
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.translate(0, mTop);
        canvas.drawBitmap(getCurrentBitmap(), widowWidth / 2 - getCurrentBitmap().getHeight() / 2, 0, mPaint);
    }

    private Bitmap getCurrentBitmap() {
        if (bitmapMap.containsKey(position % picCount)) {
            return bitmapMap.get(position % picCount);
        } else {
            Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), refreshResources[position % picCount]);
            bitmapMap.put(position % picCount, bitmap);
            return bitmap;
        }
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getContext().getResources().getDisplayMetrics());
    }

}
