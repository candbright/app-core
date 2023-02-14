package com.candbright.base.activity;

import android.util.Log;

/**
 * created by wyh in 2021/11/15
 */
public class ActivityLifecycleListener implements IActivityLifecycleListener {
    private static final String TAG = "<ActivityLifecycleListener>";
    public static boolean DEBUG = true;

    @Override
    public void onViewCreated() {
        if (DEBUG) {
            Log.d(TAG, "onViewCreated()");
        }
    }

    @Override
    public void onResume() {
        if (DEBUG) {
            Log.d(TAG, "onResume()");
        }
    }

    @Override
    public void onPause() {
        if (DEBUG) {
            Log.d(TAG, "onPause()");
        }
    }

    @Override
    public void onDestroy() {
        if (DEBUG) {
            Log.d(TAG, "onDestroy()");
        }
    }

    /**
     * 返回true，则调用super方法。返回false，则不调用。
     */
    @Override
    public boolean onBackPressed() {
        if (DEBUG) {
            Log.d(TAG, "onBackPressed()");
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (DEBUG) {
            Log.d(TAG, "onRequestPermissionsResult() {requestCode = " + requestCode + "}");
        }
    }
}