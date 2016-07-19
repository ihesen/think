package com.ihesen.think.module.activity.vus;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ihesen.think.bean.data.ActivityBean;
import com.ihesen.think.bean.data.Image;
import com.ihesen.think.customview.NineGridlayout;
import com.ihesen.think.R;
import com.ihesen.think.module.activity.adapters.RefreshFootAdapter;
import com.ihesen.think.module.mvp.vus.Vu;
import com.ihesen.think.quickadapter.BaseAdapterHelper;
import com.ihesen.think.quickadapter.QuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.support.v7.widget.RecyclerView.OnScrollListener;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * author: ihesen on 2016/4/19 16:57
 * email: hesen@ichsy.com
 */
public class ActivityVu implements Vu {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private View rootView;
    private RefreshFootAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private int lastVisibleItem;

    @Override
    public View getView() {
        return rootView;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup viewGroup) {
        rootView = inflater.inflate(R.layout.activity_vu, viewGroup, false);
        ButterKnife.bind(this, rootView);
//        setUpRecyclerView();

        testData();
    }


    private void setUpRecyclerView() {
        recyclerView.setLayoutManager(linearLayoutManager = new LinearLayoutManager(rootView.getContext()));
        recyclerView.setAdapter(adapter = new RefreshFootAdapter(rootView.getContext()));

        // 设置刷新时动画的颜色，可以设置4个
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, rootView.getContext().getResources().getDisplayMetrics()));

        adapter.setOnItemClickListener(new RefreshFootAdapter.OnRecyclerViewItemClickListener() {

            @Override
            public void onItemClick(View view, String data) {
                Toast.makeText(recyclerView.getContext(), "" + data, Toast.LENGTH_SHORT).show();
            }
        });
//        adapter.setOnItemLongClickListener(new RefreshFootAdapter.OnRecyclerViewItemLongClickListener() {
//
//            @Override
//            public void onItemLongClick(View view, String data) {
//                Toast.makeText(recyclerView.getContext(), "我是长按事件" + data, Toast.LENGTH_SHORT).show();
//            }
//        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<String> newDatas = new ArrayList<String>();
                        for (int i = 0; i < 5; i++) {
                            int index = i + 1;
                            newDatas.add("new item" + index);
                        }
                        adapter.addItem(newDatas);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 5000);
            }
        });

        // RecyclerView滑动监听
        recyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    adapter.changeMoreStatus(RefreshFootAdapter.LOADING_MORE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            List<String> newDatas = new ArrayList<>();
                            for (int i = 0; i < 5; i++) {
                                int index = i + 1;
                                newDatas.add("more item" + index);
                            }
                            adapter.addMoreItem(newDatas);
                            adapter.changeMoreStatus(RefreshFootAdapter.PULLUP_LOAD_MORE);
                        }
                    }, 2500);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private void testData() {
        recyclerView.setLayoutManager(linearLayoutManager = new LinearLayoutManager(rootView.getContext()));
        List<ActivityBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ActivityBean bean = new ActivityBean();
            if (i % 2 == 0) {
                for (int j = 0; j < 9; j++) {
                    if (j % 2 == 0) {
                        bean.pics.add(new Image("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png"));
                    } else {
                        bean.pics.add(new Image("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2909526449,2384267136&fm=80"));
                    }
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    if (j % 2 == 0) {
                        bean.pics.add(new Image("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png"));
                    } else {
                        bean.pics.add(new Image("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2909526449,2384267136&fm=80"));
                    }
                }
            }
            list.add(bean);
        }
        recyclerView.setAdapter(new QuickAdapter<ActivityBean>(rootView.getContext(), R.layout.item_activity, list) {

            @Override
            protected void convert(BaseAdapterHelper helper, ActivityBean item) {
                ((NineGridlayout) helper.getConvertView().findViewById(R.id.iv_ngrid_layout)).setImagesData(item.pics);
            }
        });
    }
}
