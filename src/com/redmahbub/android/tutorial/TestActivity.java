package com.redmahbub.android.tutorial;

import com.redmahbub.android.tutorial.asynctasks.SampleAsyncTaskOne;
import com.redmahbub.android.tutorial.utilities.AsyncTaskCallbacks;
import com.redmahbub.android.tutorial.utilities.CustomApplication;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.os.Build;

public class TestActivity extends Activity implements OnClickListener {
	
	public static String SAMPLE_TASK_IN_PROGRESS = "sampleTaskInProgress";
	
	private SampleAsyncTaskOne sampleTaskOne;
	
	private TextView statusTextView;
	private Button startBtn;
	private Button stopBtn;
	
	
	private AsyncTaskCallbacks sampleTaskCallbacks = new AsyncTaskCallbacks() {
		
		@Override
		public void onProgressUpdate(Activity mActivity, int percent) {
			((TestActivity) mActivity).statusTextView.setText(percent + "%");
			//textView1.setText(percent + "%");
		}
		
		@Override
		public void onPreExecute(Activity mActivity) {
			Log.d("DEBUG_TAG", "Running onPreExecute() of Call back");
			final TestActivity activity = ((TestActivity) mActivity);
			activity.statusTextView.setText("Starting...");
			activity.startBtn.setVisibility(View.INVISIBLE);
			activity.stopBtn.setVisibility(View.VISIBLE);
			//textView1.setText("Starting...");
		}
		
		@Override
		public void onPostExecute(Activity mActivity) {
			Log.d("DEBUG_TAG", "Running onPostExecute() of Call back");
			final TestActivity activity = ((TestActivity) mActivity);
			activity.statusTextView.setText("Done");
			activity.startBtn.setVisibility(View.VISIBLE);
			activity.stopBtn.setVisibility(View.INVISIBLE);
			Log.d("DEBUG_TAG", "Running onPostExecute() of Call back End");
		}
		
		@Override
		public void onCancelled(Activity mActivity) {
			final TestActivity activity = ((TestActivity) mActivity);
			activity.startBtn.setVisibility(View.VISIBLE);
			activity.stopBtn.setVisibility(View.INVISIBLE);
			activity.statusTextView.setText("Cancelled");
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

		statusTextView = (TextView) findViewById(R.id.textView1);
		startBtn = (Button) findViewById(R.id.startBtn);
		stopBtn = (Button) findViewById(R.id.stopBtn);
		
		stopBtn.setVisibility(View.INVISIBLE);
		
		Log.d("DEBUG_TAG", "Got Ref");
		
		startBtn.setOnClickListener(this);
		stopBtn.setOnClickListener(this);
	}

    @Override
    public void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
    	
    	if(sampleTaskOne != null && sampleTaskOne.getStatus() != AsyncTask.Status.FINISHED) {
    		outState.putBoolean(SAMPLE_TASK_IN_PROGRESS, true);
    	}
    	
    	((CustomApplication) getApplication()).detach(this);
    }
    
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
    	super.onRestoreInstanceState(savedInstanceState);
    	
    	// You can setup your activity component according to task running or not
    	if(savedInstanceState.getBoolean(SAMPLE_TASK_IN_PROGRESS)) {
    		startBtn.setVisibility(View.INVISIBLE);
    		stopBtn.setVisibility(View.VISIBLE);
    	}
    	
    	((CustomApplication) getApplication()).attach(this);
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("DEBUG_TAG", "Calling onDestroy()");
	
		//cancelTask(sampleTaskOne);
	}

	
	private void cancelTask(AsyncTask<?, ?, ?> task)
	{
		if(task != null && task.getStatus() == AsyncTask.Status.RUNNING) {
			task.cancel(true);
			task = null;
		}
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.startBtn:
				sampleTaskOne = new SampleAsyncTaskOne(this, sampleTaskCallbacks);
				sampleTaskOne.execute();
				break;
			case R.id.stopBtn:
				sampleTaskOne.cancel(true);
				sampleTaskOne = null;
			default: break;
		}
		
	}
}
