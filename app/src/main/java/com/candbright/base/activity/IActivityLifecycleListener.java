package com.candbright.base.activity;

public interface IActivityLifecycleListener {
    void onViewCreated();

    void onResume();

    void onPause();

    void onDestroy();

    boolean onBackPressed();

    void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);
}
