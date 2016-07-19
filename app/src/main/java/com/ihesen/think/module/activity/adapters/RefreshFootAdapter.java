package com.ihesen.think.module.activity.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ihesen.think.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author: ihesen on 2016/4/20 10:57
 * email: hesen@ichsy.com
 */
public class RefreshFootAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    // 上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    // 正在加载中
    public static final int LOADING_MORE = 1;
    // 上拉加载更多状态-默认为0
    private int load_more_status = 0;
    private LayoutInflater mInflater;
    private List<String> mTitles = null;
    private static final int TYPE_ITEM = 0; // 普通Item View
    private static final int TYPE_FOOTER = 1; // 顶部FootView
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private OnRecyclerViewItemLongClickListener mOnItemLongClickListener = null;

    // item的点击事件
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data);
    }

    //item的长按事件
    public interface OnRecyclerViewItemLongClickListener {
        void onItemLongClick(View view, String data);
    }

    public RefreshFootAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mTitles = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int index = i + 1;
            mTitles.add("item" + index);
        }
    }

    /**
     * item显示类型
     *
     * @param parent
     * @param viewType
     * @return
     */
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 进行判断显示类型，来创建返回不同的View
        if (viewType == TYPE_ITEM) {
            View view = mInflater.inflate(R.layout.item_recycler_layout, parent, false);
            // 这边可以做一些属性设置，甚至事件监听绑定
            // view.setBackgroundColor(Color.RED);
            ItemViewHolder itemViewHolder = new ItemViewHolder(view);
            // 绑定点击事件
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            return itemViewHolder;
        } else if (viewType == TYPE_FOOTER) {
            View foot_view = mInflater.inflate(R.layout.recycler_load_more_layout, parent, false);
            // 这边可以做一些属性设置，甚至事件监听绑定
            // view.setBackgroundColor(Color.RED);
            FootViewHolder footViewHolder = new FootViewHolder(foot_view);
            return footViewHolder;
        }
        return null;

    }

    /**
     * 数据的绑定显示
     *
     * @param holder
     * @param position
     */
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).item_tv.setText(mTitles.get(position));
            // 将数据保存在itemView的Tag中，以便点击时进行获取
            // holder.itemView.setTag(position);
            holder.itemView.setTag(mTitles.get(position));

        } else if (holder instanceof FootViewHolder) {
            FootViewHolder footViewHolder = (FootViewHolder) holder;
            switch (load_more_status) {
                case PULLUP_LOAD_MORE:
                    footViewHolder.foot_view_item_tv.setText("上拉加载更多...");
                    break;
                case LOADING_MORE:
                    footViewHolder.foot_view_item_tv.setText("正在加载更多数据...");
                    break;
            }
        }
    }

    /**
     * 进行判断是普通Item视图还是FootView视图
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return mTitles.size() + 1;
    }

    // 自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView item_tv;

        public ItemViewHolder(View view) {
            super(view);
            item_tv = (TextView) view.findViewById(R.id.item_tv);
        }
    }

    /**
     * 底部FootView布局
     */
    public static class FootViewHolder extends RecyclerView.ViewHolder {
        private TextView foot_view_item_tv;

        public FootViewHolder(View view) {
            super(view);
            foot_view_item_tv = (TextView) view.findViewById(R.id.foot_view_item_tv);
        }
    }

    // 添加数据
    public void addItem(List<String> newDatas) {
        // mTitles.add(position, data);
        // notifyItemInserted(position);
        newDatas.addAll(mTitles);
        mTitles.removeAll(mTitles);
        mTitles.addAll(newDatas);
        notifyDataSetChanged();
    }

    public void addMoreItem(List<String> newDatas) {
        mTitles.addAll(newDatas);
        notifyDataSetChanged();
    }

    /**
     * //上拉加载更多 PULLUP_LOAD_MORE=0; //正在加载中 LOADING_MORE=1; //加载完成已经没有更多数据了
     * NO_MORE_DATA=2;
     *
     * @param status
     */
    public void changeMoreStatus(int status) {
        load_more_status = status;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            // 注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (String) v.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnRecyclerViewItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnItemLongClickListener != null) {
            mOnItemLongClickListener.onItemLongClick(v, (String) v.getTag());
        }
        return true;
    }
}

