package com.candbright.base.fragment;

import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * created by wyh in 2021/11/16
 */
public abstract class BaseToolFragment extends Fragment {
    private int mContainerId;

    public int getContainerId() {
        return mContainerId;
    }

    public void setContainerId(int containerViewId) {
        mContainerId = containerViewId;
    }

    public int getColor(int colorRes) {
        return getContext().getColor(colorRes);
    }

    public void showToast(String text) {
        showToast(text, true);
    }

    public void showToast(String text, boolean isShort) {
        if (isShort) {
            Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
        }
    }
}
