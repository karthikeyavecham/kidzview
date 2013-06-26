/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.antzview.kidz; 

import java.util.ArrayList;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Demonstrates a "screen-slide" animation using a {@link ViewPager}. Because
 * {@link ViewPager} automatically plays such an animation when calling
 * {@link ViewPager#setCurrentItem(int)}, there isn't any animation-specific
 * code in this sample.
 * 
 * <p>
 * This sample shows a "next" button that advances the user to the next step in
 * a wizard, animating the current screen out (to the left) and the next screen
 * in (from the right). The reverse animation is played when the user presses
 * the "previous" button.
 * </p>
 * 
 * @see ScreenSlidePageFragment
 */
public class ScreenSlideActivity extends FragmentActivity implements OnClickListener
{
	/**
	 * The number of pages (wizard steps)
	 */
	private int NUM_PAGES;
	MediaPlayer mp;

	/**
	 * The pager widget, which handles animation and allows swiping
	 * horizontally to access previous and next wizard steps.
	 */
	private ViewPager mPager;
	private int position;
	private int itemPosition;
	ArrayList<Item> itemList;
	TextView itemText;
	/**
	 * The pager adapter, which provides the pages to the view pager widget.
	 */
	private PagerAdapter mPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slide_screen);

		position = getIntent().getIntExtra("result", -1);
		int categoryPosition = getIntent().getIntExtra("categoryId", -1);
		itemList = Builder.getItemListAtPosition(categoryPosition);
		NUM_PAGES = itemList.size();
		// Instantiate a ViewPager and a PagerAdapter.
		mPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter); 
		ImageButton backButton = (ImageButton) findViewById(R.id.previous_button);
		ImageButton nextButton = (ImageButton) findViewById(R.id.next_button);
		ImageButton homeButton = (ImageButton) findViewById(R.id.home_button);
		itemText = (TextView) findViewById(R.id.itemText);
		setImageDesc();
		backButton.setOnClickListener(this);
		nextButton.setOnClickListener(this);
		homeButton.setOnClickListener(this);
		mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position)
			{

				itemPosition = position;
				setImageDesc();
				playAudio(); // play when item on screen changes
			}
			@Override
		        public void onPageScrollStateChanged(int state) {

		            if (state == ViewPager.SCROLL_STATE_IDLE) {
		        	    if(itemPosition == itemList.size())
		        	    {
		        		    Log.i("dimension", "-"+itemPosition+"-"+(itemList.size()-1));
		        		    
		        		    mPager.setNextFocusRightId(0);
		        	    }
		        	    if(itemPosition == 0)
		        	    { 
		        		    Log.i("dimension", "-"+itemPosition+"-0");
		        		    mPager.setCurrentItem(itemList.size()-1);
		        		    
		        	    }
		                // go back to the center allowing to scroll indefinitely
		                
		            }
		        }

		});

		mPager.setCurrentItem(position);

		ImageButton playButton = (ImageButton) findViewById(R.id.play_button);
		playAudio(); // play when an item is opened from grid

		playButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				playAudio(); // play when play button is clicked

			}

		});

	}

	private void playAudio()
	{
		stopMediaPlayerIfRunning();
		mp = MediaPlayer.create(getApplicationContext(), itemList.get(itemPosition).getSoundResourceId());
		mp.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mediaplayer)
			{ // TODO
				mediaplayer.release();
				mp = null;
				Log.i("tag", "mp made null");
			}

		});

		mp.start();
	}

	private void setImageDesc()
	{

		this.setTitle(itemList.get(itemPosition).getImageDesc());
		// itemText.setText(itemList.get(itemPosition).getImageDesc());

	}

	@Override
	protected void onPause()
	{
		stopMediaPlayerIfRunning();
		super.onPause();
	}

	private void stopMediaPlayerIfRunning()
	{
		if (mp != null)
		{

			if (mp.isPlaying())
			{
				mp.stop();
				mp.release();
				mp = null;
			}
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
			{
			case R.id.previous_button:
			{
				Log.i("tag", "before back: " + mPager.getCurrentItem());
				mPager.setCurrentItem(mPager.getCurrentItem() - 1);
				Log.i("tag", "after back: " + mPager.getCurrentItem());
				break;
			}
			case R.id.next_button:
			{
				Log.i("tag", "before next: " + mPager.getCurrentItem());
				mPager.setCurrentItem(mPager.getCurrentItem() + 1);
				Log.i("tag", "after next: " + mPager.getCurrentItem());
				break;
			}
			case R.id.home_button:
			{
				Intent intent = new Intent();
				intent.setClass(this, CatagoryListActivity.class);
				startActivity(intent);
			}
			}

	}

	/**
	 * A simple pager adapter that represents 5
	 * {@link ScreenSlidePageFragment} objects, in sequence.
	 */
	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter
	{
		public ScreenSlidePagerAdapter(android.support.v4.app.FragmentManager fm)
		{
			super(fm);
		}

		@Override
		public int getCount()
		{
			return NUM_PAGES;
		}

		@Override
		public android.support.v4.app.Fragment getItem(int position)
		{
			// TODO Auto-generated method stub
			return ScreenSlidePageFragment.create(position, itemList.get(position).getFullImageResourceId());
		}
	}

}
