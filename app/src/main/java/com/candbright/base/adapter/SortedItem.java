package com.candbright.base.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

public abstract class SortedItem<Holder extends BaseViewHolder, BindingView extends ViewBinding> implements Cloneable, Serializable {
    private int sortedIndex;

    public Holder bindViewHolder(ViewGroup parent) {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        assert type != null;
        Class cls = (Class) type.getActualTypeArguments()[1];
        BindingView viewBinding = null;
        try {
            //View view = LayoutInflater.from(parent.getContext()).inflate(layoutID(), parent, false);
            Method inflate = cls.getDeclaredMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
            viewBinding = (BindingView) inflate.invoke(null, LayoutInflater.from(parent.getContext()), parent, false);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
        Holder viewHolder = createViewHolder(viewBinding);
        viewHolder.rootBinding = viewBinding;
        return viewHolder;
    }

    protected abstract Holder createViewHolder(BindingView viewBinding);

    public int getSortedIndex() {
        return sortedIndex;
    }

    public SortedItem<Holder, BindingView> setSortedIndex(int index) {
        this.sortedIndex = index;
        return this;
    }

    @NonNull
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
