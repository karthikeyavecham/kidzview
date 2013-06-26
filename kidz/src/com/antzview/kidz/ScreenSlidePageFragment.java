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

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * A fragment representing a single step in a wizard. The fragment shows a dummy
 * title indicating the page number, along with some dummy text.
 * 
 * <p>
 * This class is used by the {@link CardFlipActivity} and
 * {@link ScreenSlideActivity} samples.
 * </p>
 */
public class ScreenSlidePageFragment extends android.support.v4.app.Fragment
{
	/**
	 * The argument key for the page number this fragment represents.
	 */
	public static final String ARG_PAGE = "page";
	public static final String IMAGE_RESOURCE_ID = "resourceId";
	public static int Measuredwidth;
	public static int Measuredheight;
	/**
	 * The fragment's page number, which is set to the argument value for
	 * {@link #ARG_PAGE}.
	 */
	private int mPageNumber;
	private int imageResourceId;

	/**
	 * Factory method for this fragment class. Constructs a new fragment for
	 * the given page number.
	 */
	public static ScreenSlidePageFragment create(int pageNumber, int imageResourceId)
	{

		ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_PAGE, pageNumber);
		args.putInt(IMAGE_RESOURCE_ID, imageResourceId);
		Log.i("tag", "resourceId at creation = " + imageResourceId);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mPageNumber = getArguments().getInt(ARG_PAGE);
		imageResourceId = getArguments().getInt(IMAGE_RESOURCE_ID);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Inflate the layout containing a title and body text.
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page, container, false);

		ImageView image = (ImageView) rootView.findViewById(R.id.full_image);

		Log.i("tag", "resourceId = " + imageResourceId);
		setImageViewSize(image);
		image.setBackgroundResource(imageResourceId);
		/*
		int width = rootView.getMeasuredWidth();
		int height = rootView.getMeasuredHeight();
		Log.i("dimensions", "width =" + width + " height=" + height);
		if (width > height)
		{
			image.getLayoutParams().width = height;
			image.getLayoutParams().height = height;
		} else
		{
			image.getLayoutParams().width = width;
			image.getLayoutParams().height = width;
		}
		*/
		return rootView;
	}

	private void setImageViewSize(ImageView image)
	{

	}

	/**
	 * Returns the page number represented by this fragment object.
	 */
	public int getPageNumber()
	{
		return mPageNumber;
	}

}
