package com.example.com.spot.tank.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class StageView extends View implements SensorEventListener {

	private SensorManager mSensorManager;

	private int rad = 10;

	private Context mContext;

	private static final int delay = 20;
	private static final double delays = ((double) delay) / 1000;

	private Handler mHandler;

	private final Runnable mHerts = new Runnable() {

		public void run() {
			tick();
		}
	};

	private static class Data {
		public static double x;
		public static double y;
		public static double vx;
		public static double vy;
		public static double gx;
		public static double gy;
	}

	private Paint mBrush;

	public StageView(Context context) {
		super(context);
		init(context);
	}

	public StageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public StageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		mContext = context;
		mBrush = new Paint(Paint.ANTI_ALIAS_FLAG);
		mBrush.setColor(Color.RED);
		mBrush.setStyle(Style.FILL);
		Data.x = 20;
		Data.y = 20;
		mSensorManager = (SensorManager) mContext
				.getSystemService(Context.SENSOR_SERVICE);
		mHandler = new Handler();
	}

	@SuppressWarnings("deprecation")
	public void start() {

		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				SensorManager.SENSOR_DELAY_UI);

		mHandler.postDelayed(mHerts, delay);
	}

	public void stop() {
		mSensorManager.unregisterListener(this);
		mHandler.removeCallbacks(mHerts);
	}

	private void tick() {
		int mX = this.getWidth();
		int mY = this.getHeight();
		Data.x = validatePoint(move(Data.x, Data.vx, Data.gx), mX, "x");
		Data.y = validatePoint(move(Data.y, Data.vy, Data.gy), mY, "y");
		this.invalidate();
		mHandler.postDelayed(mHerts, delay);
	}

	private double move(double d0, double v0, double g) {
		return d0 + v0 + 1 / 2 * g * delays * delays;
	}

	private double speed(double v0, double g) {
		return v0 + g * delays;
	}

	private double validatePoint(double p, int val, String to) {
		if (p < rad) {
			if (to.equals("x")) {
				Data.vx = -Data.vx / 2;
			} else {
				Data.vy = -Data.vy / 2;
			}
			return rad;
		}
		if (p > val - rad) {
			if (to.equals("x")) {
				Data.vx = -Data.vx / 2;
			} else {
				Data.vy = -Data.vy / 2;
			}
			return val - rad;
		}
		if (to.equals("x")) {
			Data.vx = speed(Data.vx, Data.gx);
		} else {
			Data.vy = speed(Data.vy, Data.gy);
		}
		return p;
	}

	@Override
	public void draw(Canvas g) {
		g.drawCircle((int) Data.x, (int) Data.y, rad, mBrush);
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	public void onSensorChanged(SensorEvent event) {
		Data.gx = -event.values[2] / 10;
		Data.gy = -event.values[1] / 10;
	}
}
