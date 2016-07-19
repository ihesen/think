package com.ihesen.think.module.activity.adapters;

import com.chad.library.adapter.base.BaseViewHolder;
import com.ihesen.think.R;
import com.ihesen.think.bean.data.ActivityBean;
import com.ihesen.think.customview.NineGridlayout;
import com.ihesen.think.customview.quickadapter.QuickAdapter;

import java.util.List;

/**
 * author: ihesen on 2016/7/19 15:58
 * email: hesen@ichsy.com
 */
public class ActivityAdapter extends QuickAdapter<ActivityBean> {

    public ActivityAdapter(int layoutResId, List<ActivityBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ActivityBean item) {
        ((NineGridlayout) helper.getView(R.id.iv_ngrid_layout)).setImagesData(item.pics);
    }
}
