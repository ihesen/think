package com.ihesen.think.customview.quickadapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ihesen.think.R;

import java.util.List;

/**
 * 无加载更多默认显示布局
 * author: ihesen on 2016/7/19 16:26
 * email: hesen@ichsy.com
 */
public abstract class QuickAdapter<T> extends BaseQuickAdapter<T> {

    public QuickAdapter(int layoutResId, List<T> data) {
        super(layoutResId, data);
    }

    public QuickAdapter(List<T> data) {
        super(data);
    }

    public QuickAdapter(View contentView, List<T> data) {
        super(contentView, data);
    }

    @Override
    public void notifyDataChangedAfterLoadMore(boolean isNextLoad) {
        super.notifyDataChangedAfterLoadMore(isNextLoad);
        if (!isNextLoad) {
            View view = View.inflate(mContext, R.layout.not_loading, null);
            addFooterView(view);
        }
    }
}
