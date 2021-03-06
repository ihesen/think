package com.ihesen.think.module.base;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ihesen.think.R;
import com.ihesen.think.module.mvp.vus.Vu;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 带topbar，需要topbar的vu可以继承它
 * author: ihesen on 2016/4/18 18:21
 * email: hesen@ichsy.com
 */
public abstract class TopBarVu implements Vu {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    protected View rootView;

    public abstract int getContentLayoutRes();

    @Override
    public View getView() {
        return rootView;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup viewGroup) {
        rootView = inflater.inflate(R.layout.root_view, viewGroup, false);
        ((ViewGroup) rootView.findViewById(R.id.framelayout_contaner)).addView(inflater.inflate(getContentLayoutRes(), viewGroup, false));
        ButterKnife.bind(this, rootView);
        logic();
    }

    public void setToolbarText(String text) {
//        title.setText(text);
    }

    /**
     * 后续逻辑处理
     */
    protected void logic() {

    }

    public Toolbar getToolbar() {
        return toolbar == null ? (Toolbar) rootView.findViewById(R.id.toolbar) : toolbar;
    }
}
