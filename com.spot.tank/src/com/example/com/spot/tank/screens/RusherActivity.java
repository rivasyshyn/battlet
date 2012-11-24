package com.example.com.spot.tank.screens;

import test.aa;
import test.bb;
import test.cc;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.com.spot.tank.R;
import com.example.com.spot.tank.views.StageView;

public class RusherActivity extends Activity implements
		OnPreferenceChangeListener {

	private StageView mStageView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rusher);

		mStageView = (StageView) findViewById(R.id.stageView1);
		cc callback = new cc() {

			@Override
			public void onCall(int number) {
				Log.d("callback", "" + number);
			}

		};

		aa A = new aa();
		A.setCallback(callback);
		bb B = new bb();
		B.setCallback(A.getCallback());
		B.call(5);
		A.call(10);
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

	@Override
	public boolean onMenuItemSelected(int id, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:
			startActivity(new Intent(this, SettingActivity.class));
			break;

		default:
			break;
		}
		return true;
	}

	public boolean onPreferenceChange(Preference preference, Object newValue) {

		return false;
	}

}
