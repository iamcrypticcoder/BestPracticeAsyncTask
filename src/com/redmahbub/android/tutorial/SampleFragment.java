package com.redmahbub.android.tutorial;

import com.redmahbub.android.tutorial.R;
import com.redmahbub.android.tutorial.asynctasks.SampleAsyncTaskOne;
import com.redmahbub.android.tutorial.utilities.AsyncTaskCallbacks;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SampleFragment extends Fragment {
	
	Activity thisActivity;
	
	private AsyncTaskCallbacks sampleTaskCallbacks;
	private SampleAsyncTaskOne sampleTaskOne;
	private boolean testValue;
	
	private TextView textView1;
	private Button button1;
	
	public SampleFragment() {
		
	}

	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
		thisActivity = getActivity();
		
		textView1 = (TextView) rootView.findViewById(R.id.textView1);
		button1 = (Button) rootView.findViewById(R.id.button1);
		
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sampleTaskOne = (SampleAsyncTaskOne) new SampleAsyncTaskOne(thisActivity, sampleTaskCallbacks).execute();
			}
		});
		
		/*
		sampleTaskCallbacks = new AsyncTaskCallbacks() {
			
			@Override
			public void onProgressUpdate(int percent) {
				
			}
			
			@Override
			public void onPreExecute() {
				textView1.setVisibility(View.INVISIBLE);
			}
			
			@Override
			public void onPostExecute() {
				textView1.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onCancelled() {
				
			}
		};
		*/
		return rootView;
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}


	@Override
	public void onStart() {
		super.onStart();
	}


	@Override
	public void onResume() {
		super.onResume();
	}


	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}


	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}


	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}


	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}


	@Override
	public void onDetach() {
		super.onDetach();
		sampleTaskCallbacks = null;
		sampleTaskOne.cancel(true);
	}
	
	
	
}
