package com.example.com.spot.tank;

import android.app.Application;

public class BTApplication extends Application {

	private static BTApplication INSTANCE;

	private BTApplication() {

	}

	@Override
	public void onCreate() {
		super.onCreate();
		INSTANCE = this;
	}

	public static final BTApplication getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new BTApplication();
		}
		return INSTANCE;
	}

}
