package com.antzview.kidz;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class WelcomeActivity extends Activity
{

	Thread t;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_welcome);

		Thread splashThread = new Thread() {
			@Override
			public void run()
			{
				try
				{
					int waited = 0;
					while (waited < 2000)
					{
						sleep(100);
						waited += 100;
					}
				} catch (InterruptedException e)
				{
					// do nothing
				} finally
				{
					finish();
					showCatagoryListActivity();
				}
			}
		};
		splashThread.start();
	}

	public void showCatagoryListActivity()
	{
		Intent intent = new Intent();
		intent.setClass(this, CatagoryListActivity.class);
		startActivity(intent);
	}

}
