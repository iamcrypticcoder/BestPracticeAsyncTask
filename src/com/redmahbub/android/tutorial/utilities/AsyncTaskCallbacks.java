package com.redmahbub.android.tutorial.utilities;

import android.app.Activity;

public interface AsyncTaskCallbacks {
    void onPreExecute(Activity mActivity);
    void onProgressUpdate(Activity mActivity, int percent);
    void onCancelled(Activity mActivity);
    void onPostExecute(Activity mActivity);
}
