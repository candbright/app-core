package com.candbright.base.adapter;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SortedItemList<ItemType extends SortedItem> {

    private boolean isCustomMode;
    private List<ItemType> data;

    public SortedItemList() {
        data = new ArrayList<>();
    }

    public SortedItemList(boolean isCustomMode) {
        data = new ArrayList<>();
        this.isCustomMode = isCustomMode;
    }

    public SortedItemList(List<ItemType> data) {
        this.data = new ArrayList<>(data);
    }

    public SortedItemList(List<ItemType> data, boolean isCustomMode) {
        this.data = new ArrayList<>(data);
        this.isCustomMode = isCustomMode;
    }

    public List list() {
        return data;
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }


    public boolean add(ItemType o) {
        if (isCustomMode) {
            return data.add(o);
        }
        return data.add((ItemType) o.setSortedIndex(data.size()));
    }

    public boolean remove(@Nullable ItemType o) {
        if (isCustomMode) {
            return data.remove(o);
        }
        boolean isSucceed = data.remove(o);
        if (isSucceed) {
            int sortedIndex = o.getSortedIndex();
            for (int i = sortedIndex + 1; i < data.size() + 1; i++) {
                data.get(i).setSortedIndex(i - 1);
            }
        }
        return isSucceed;
    }

    public ItemType get(int index) {
        return data.get(index);
    }


    public void add(int index, ItemType element) {
        if (isCustomMode) {
            data.add(index, element);
            return;
        }
        data.add(index, (ItemType) element.setSortedIndex(index));
    }

    public ItemType remove(int index) {
        if (isCustomMode) {
            return data.remove(index);
        }
        SortedItem removedItem = data.remove(index);
        for (int i = index + 1; i < data.size() + 1; i++) {
            data.get(i).setSortedIndex(i - 1);
        }
        return (ItemType) removedItem;
    }

    public void clear() {
        data.clear();
    }

}
