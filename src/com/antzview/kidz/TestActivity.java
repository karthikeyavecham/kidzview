package com.antzview.kidz;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class TestActivity extends Activity
{
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		 ImageView img = (ImageView)findViewById(R.id.testAnimation);
		 //img.setBackgroundResource(R.drawable.alphabet);
 
		 // Get the background, which has been compiled to an AnimationDrawable object.
		 AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
 
		 // Start the animation (looped playback by default).
		 frameAnimation.start();
		 
	}

}
