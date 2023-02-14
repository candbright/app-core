package com.candbright.base.fragment;

import android.content.Intent;

public interface IFragmentLifecycleListener {

    void onViewCreated();

    void onResume();

    void onPause();

    void onHiddenChanged(boolean hidden);

    void onDestroyView();

    void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);

    void onActivityResult(int requestCode, int resultCode, Intent data);
}
