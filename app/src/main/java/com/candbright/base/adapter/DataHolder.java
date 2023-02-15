package com.candbright.base.adapter;

import android.view.View;

import androidx.viewbinding.ViewBinding;

public abstract class DataHolder<Item extends DataItem, BindingView extends ViewBinding> extends BaseViewHolder<Item, BindingView> {

    public DataHolder(View itemView) {
        super(itemView);
    }

    @Override
    public abstract void bindViewData(Item data);

}
