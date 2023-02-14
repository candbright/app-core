package com.candbright.base.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * 事件动机模式的View模块：
 * 当包含者Activity需要调用ExternalRelations中的业务数据或方法时，则使用这个BaseFragment2，否则使用BaseFragment。
 */
public abstract class BaseFragment<BindingView extends ViewBinding> extends BaseToolFragment {

    private IFragmentLifecycleListener mLifecycleListener;
    protected BindingView rootBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLifecycleListener = createLifecycleListener();
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class cls = (Class) type.getActualTypeArguments()[0];
        try {
            Method inflate = cls.getDeclaredMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
            rootBinding = (BindingView) inflate.invoke(null, inflater, container, false);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return rootBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initViewBinding();
        initManager();
        if (mLifecycleListener != null) {
            mLifecycleListener.onViewCreated();
        }
    }

    protected abstract void initViewBinding();

    protected abstract void initManager();

    protected IFragmentLifecycleListener createLifecycleListener() {
        return new IFragmentLifecycleListener() {
            @Override
            public void onViewCreated() {
            }

            @Override
            public void onResume() {
            }

            @Override
            public void onPause() {
            }

            @Override
            public void onHiddenChanged(boolean hidden) {
            }

            @Override
            public void onDestroyView() {
            }

            @Override
            public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            }

            @Override
            public void onActivityResult(int requestCode, int resultCode, Intent data) {
            }
        };
    }

    public BindingView getRootBinding() {
        return rootBinding;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mLifecycleListener != null) {
            mLifecycleListener.onResume();
        }
    }

    @Override
    public void onPause() {
        if (mLifecycleListener != null) {
            mLifecycleListener.onPause();
        }
        super.onPause();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        if (mLifecycleListener != null) {
            mLifecycleListener.onHiddenChanged(hidden);
        }
    }

    @Override
    public void onDestroyView() {
        //Fragment的存在时间比其视图长，需要清除对绑定类实例的所有引用
        rootBinding = null;
        if (mLifecycleListener != null) {
            mLifecycleListener.onDestroyView();
        }
        super.onDestroyView();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (mLifecycleListener != null) {
            mLifecycleListener.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mLifecycleListener != null) {
            mLifecycleListener.onActivityResult(requestCode, resultCode, data);
        }
    }
}

