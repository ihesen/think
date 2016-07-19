package com.ihesen.think.module.base;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ihesen.think.R;
import com.ihesen.think.module.mvp.vus.Vu;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author: ihesen on 2016/4/18 18:21
 * email: hesen@ichsy.com
 */
public class BaseActivityVu implements Vu {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.framelayout_contaner)
    FrameLayout framelayoutContaner;
    @Bind(R.id.title)
    TextView title;
    private View rootView;

    @Override
    public View getView() {
        return rootView;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup viewGroup) {
        rootView = inflater.inflate(R.layout.root_view, viewGroup, false);
        ButterKnife.bind(this, rootView);
    }

    public void setToolbarText(String text) {
        toolbar.setTitle("");
        title.setText(text);
    }

    public ViewGroup getContainerView() {
        return framelayoutContaner;
    }
}
