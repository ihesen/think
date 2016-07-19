package com.ihesen.think.module.activity.vus;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ihesen.think.DataServer;
import com.ihesen.think.R;
import com.ihesen.think.module.activity.adapters.ActivityAdapter;
import com.ihesen.think.module.mvp.vus.Vu;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author: ihesen on 2016/4/19 16:57
 * email: hesen@ichsy.com
 */
public class ActivityVu implements Vu, BaseQuickAdapter.RequestLoadMoreListener {

    private int mCurrentCounter = 0;
    private int TOTAL_COUNTER = 25;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private View rootView;

    private ActivityAdapter mAdapter;

    @Override
    public View getView() {
        return rootView;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup viewGroup) {
        rootView = inflater.inflate(R.layout.activity_vu, viewGroup, false);
        ButterKnife.bind(this, rootView);
        setUpRecyclerView();
        testData();
    }


    private void setUpRecyclerView() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setNewData(DataServer.getActivityData(5));
                        mCurrentCounter = 0;
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }

    private void testData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        mAdapter = new ActivityAdapter(R.layout.item_activity, DataServer.getActivityData(10));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.openLoadMore(5, true);
    }

    @Override
    public void onLoadMoreRequested() {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                if (mCurrentCounter >= TOTAL_COUNTER) {
                    mAdapter.notifyDataChangedAfterLoadMore(false);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.notifyDataChangedAfterLoadMore(DataServer.getActivityData(5), true);
                            mCurrentCounter = mAdapter.getData().size();
                        }
                    }, 3000);
                }
            }
        });
    }
}
