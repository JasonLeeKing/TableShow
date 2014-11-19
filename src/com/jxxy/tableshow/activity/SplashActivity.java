package com.jxxy.tableshow.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

import com.jxxy.tableshow.BaseActivity;
import com.jxxy.tableshow.R;
import com.jxxy.tableshow.frgment.FramentMain;

public class SplashActivity extends BaseActivity
{

	private final Timer timer = new Timer(); 
	private TimerTask task; 
	private int time = 3000; 
	
	Handler handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case 9090:
				loadUI();
				break;
			}
		};
	};
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_layout);
		timer();
	}

	@Override
	protected void onResume()
	{
		super.onResume();

	}

	@Override
	protected void onPause()
	{
		super.onPause();
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
	}

	private void loadUI()
	{
		timer.cancel(); 

		
		Intent intent = new Intent();
			intent.setClass(SplashActivity.this, FramentMain.class);
		startActivity(intent);
		SplashActivity.this.finish();
	}


	private void timer()
	{
		task = new TimerTask()
		{
			@Override
			public void run()
			{
				Message message = new Message();
				message.what = 9090;
				handler.sendMessage(message);
			}
		};
		timer.schedule(task, time, time);
	}
}
