package com.ihesen.think.quickadapter.loadingadapter;

import android.content.Context;

import com.ihesen.think.quickadapter.BaseAdapterHelper;
import com.ihesen.think.quickadapter.BaseQuickAdapter;
import com.ihesen.think.quickadapter.MultiItemTypeSupport;

import java.util.List;

/**
 * author: ihesen on 2016/7/18 18:40
 * email: hesen@ichsy.com
 */
public class LoadMoreAdapter<T> extends BaseQuickAdapter<T, BaseAdapterHelper> {


    protected LoadMoreAdapter(Context context, MultiItemTypeSupport<T> multiItemTypeSupport, List<T> data) {
        super(context, multiItemTypeSupport, data);
    }

    protected LoadMoreAdapter(Context context, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, multiItemTypeSupport);
    }

    protected LoadMoreAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    protected LoadMoreAdapter(Context context, int layoutResId, List<T> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, T item) {
        
    }
}
