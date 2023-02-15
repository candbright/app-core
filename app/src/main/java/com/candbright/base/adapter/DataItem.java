package com.candbright.base.adapter;

import androidx.viewbinding.ViewBinding;

public abstract class DataItem<Data, Holder extends BaseViewHolder, BindingView extends ViewBinding> extends SortedItem<Holder, BindingView> {

    private Data data;

    protected abstract Holder createViewHolder(BindingView viewBinding);

    public Data getData() {
        return data;
    }

    public DataItem(Data data) {
        this.data = data;
    }
}
