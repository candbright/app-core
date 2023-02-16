package com.candbright.base.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.viewbinding.ViewBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

public abstract class DataItem<Data, Holder extends BaseViewHolder, BindingView extends ViewBinding> extends SortedItem<Holder, BindingView> {

    private Data data;

    protected abstract Holder createViewHolder(BindingView viewBinding);

    @Override
    public Holder bindViewHolder(ViewGroup parent) {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        assert type != null;
        Class cls = (Class) type.getActualTypeArguments()[2];
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

    public Data getData() {
        return data;
    }

    public DataItem(Data data) {
        this.data = data;
    }
}
