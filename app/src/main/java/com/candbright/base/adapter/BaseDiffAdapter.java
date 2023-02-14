package com.candbright.base.adapter;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * created by wyh in 2021/10/19
 */
public abstract class BaseDiffAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    protected final List<SortedItem> temp; // 用于保存修改之前的数据源的副本
    protected final List<SortedItem> data; // 数据源

    public BaseDiffAdapter(List<SortedItem> data) {
        this.data = data;
        temp = new ArrayList<>(data);
    }

    protected abstract boolean areItemsTheSame(SortedItem oldItem, SortedItem newItem);

    protected abstract boolean areContentsTheSame(SortedItem oldItem, SortedItem newItem);

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (data == null || position < 0 || position >= data.size()) {
            return -1;
        }
        return data.get(position).getClass().hashCode();
    }

    public void notifyDiff() {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return temp.size();
            }

            @Override
            public int getNewListSize() {
                return data.size();
            }

            // 判断是否是同一个 item
            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return BaseDiffAdapter.this.areItemsTheSame(temp.get(oldItemPosition), data.get(newItemPosition));
            }

            // 如果是同一个 item 判断内容是否相同
            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return BaseDiffAdapter.this.areContentsTheSame(temp.get(oldItemPosition), data.get(newItemPosition));
            }
        });
        diffResult.dispatchUpdatesTo(this);
        // 通知刷新了之后，要更新副本数据到最新
        temp.clear();
        temp.addAll(data);
    }
}
