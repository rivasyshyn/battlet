package com.example.com.spot.tank;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.SurfaceView;

public class Rusher extends Activity {

	private StageView mStageView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rusher);

		mStageView = (StageView) findViewById(R.id.stageView1);

	}

	@Override
	public void onResume() {
		super.onResume();
		mStageView.start();
	}

	@Override
	public void onPause() {
		super.onPause();
		mStageView.stop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_rusher, menu);
		return true;
	}

}
