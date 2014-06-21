package com.redmahbub.android.tutorial.asynctasks;

import org.json.JSONObject;

import com.redmahbub.android.tutorial.utilities.AsyncTaskCallbacks;
import com.redmahbub.android.tutorial.utilities.CustomAsyncTask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

public class SampleAsyncTaskOne extends CustomAsyncTask<String, Integer, JSONObject> {
	
	AsyncTaskCallbacks mCallbacks;
	
	
	public SampleAsyncTaskOne(Activity activity, AsyncTaskCallbacks mCallbacks) {
		super(activity);
		this.mCallbacks = mCallbacks;
	}

	
	
	@Override
	protected void onActivityAttached() {
		super.onActivityAttached();
	}


	@Override
	protected void onActivityDetached() {
		super.onActivityDetached();
	}


	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		Log.d("DEBUG_TAG", "Running onPreExecute()");
		if(mCallbacks != null) mCallbacks.onPreExecute(mActivity);
	}
	
	@Override
	protected JSONObject doInBackground(String... params) {     
		Log.d("DEBUG_TAG", "Running doInBackground()");
		for (int i = 0; !isCancelled() && i < 100; i++) {
	        SystemClock.sleep(50);
	        publishProgress(i);
		}
		return null;
	}

	
	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		//Log.d("DEBUG_TAG", "Running onProgressUpdate()");
		if(mCallbacks != null) mCallbacks.onProgressUpdate(mActivity, values[0]);
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		super.onPostExecute(result);
		Log.d("DEBUG_TAG", "Running onPostExecute()");
		if(mCallbacks != null) mCallbacks.onPostExecute(mActivity);
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
		Log.d("DEBUG_TAG", "Running onCancelled()");
		if(mCallbacks != null) mCallbacks.onCancelled(mActivity);
	}


	
}
