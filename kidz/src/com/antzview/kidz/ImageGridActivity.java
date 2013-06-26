package com.antzview.kidz;

import java.util.ArrayList;
import java.util.ListIterator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

@SuppressLint("NewApi")
public class ImageGridActivity extends Activity implements OnItemClickListener
{

	private int imageThumbIds[] = null;
	private int selectedCatagory = -1;
	private SharedPreferences sharedPrefs;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_ACTION_BAR);
		setContentView(R.layout.activity_image_grid);
		
		sharedPrefs = getPreferences(MODE_PRIVATE);

		selectedCatagory = getIntent().getExtras().getInt("result");
		this.setTitle(Builder.getCategoryList().get(selectedCatagory).getLabel());
		SharedPreferences.Editor editor = sharedPrefs.edit();
		editor.putInt(getResources().getString(R.string.selected_category_saved_value), selectedCatagory);
		editor.commit();
		
		// Toast.makeText(getApplicationContext(), "" +
		// selectedCatagory, Toast.LENGTH_SHORT).show();
		GridView gridview = (GridView) findViewById(R.id.imageGrid);
		extractThumbIdsFromItemList(Builder.getItemListAtPosition(selectedCatagory));
		setGridViewColumns(gridview);
		gridview.setAdapter(new ImageAdapter(this, imageThumbIds));

		gridview.setOnItemClickListener(this);

	}

	private void setGridViewColumns(GridView gridview)
	{

		int Measuredwidth = 0;
		int Measuredheight = 0;
		Point size = new Point();
		WindowManager w = getWindowManager();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2)
		{
			w.getDefaultDisplay().getSize(size);

			Measuredwidth = size.x;
			Measuredheight = size.y;
		} else
		{
			Display d = w.getDefaultDisplay();
			Measuredwidth = d.getWidth();
			Measuredheight = d.getHeight();
		}
		int numCols = Measuredwidth / 120;
		// Toast.makeText(this, "  "+Measuredwidth +" "+ numCols,
		// Toast.LENGTH_LONG).show();
		gridview.setNumColumns(numCols);
		/*
		 * 
		 * int screenSize =
		 * getResources().getConfiguration().screenLayout &
		 * Configuration.SCREENLAYOUT_SIZE_MASK;
		 * 
		 * switch (screenSize) {
		 * 
		 * case Configuration.SCREENLAYOUT_SIZE_XLARGE:
		 * Toast.makeText(this, "XLarge screen",
		 * Toast.LENGTH_LONG).show(); gridview.setNumColumns(4); break;
		 * case Configuration.SCREENLAYOUT_SIZE_LARGE:
		 * Toast.makeText(this, "Large screen",
		 * Toast.LENGTH_LONG).show(); gridview.setNumColumns(3); break;
		 * case Configuration.SCREENLAYOUT_SIZE_NORMAL:
		 * Toast.makeText(this, "Normal screen",
		 * Toast.LENGTH_LONG).show(); gridview.setNumColumns(2); break;
		 * case Configuration.SCREENLAYOUT_SIZE_SMALL:
		 * Toast.makeText(this, "Small screen",
		 * Toast.LENGTH_LONG).show(); gridview.setNumColumns(2); break;
		 * default: Toast.makeText(this,
		 * "Screen size is neither large, normal or small",
		 * Toast.LENGTH_LONG).show(); }
		 */}
    
	@Override
	protected void onPause()       
	{ 

		SharedPreferences.Editor editor = sharedPrefs.edit();
		editor.putInt(getResources().getString(R.string.selected_category_saved_value), selectedCatagory);
		editor.commit();
		Log.i("tag", "on pause : " + selectedCatagory);
		super.onPause();
	}

	@Override
	protected void onResume()
	{
		selectedCatagory = sharedPrefs.getInt(getResources().getString(R.string.selected_category_saved_value), -1);
		Log.i("tag", "on resume : " + selectedCatagory);
		super.onResume();
	}

	public void onItemClick(AdapterView<?> parent, View v, int position, long id)
	{

		Intent intent = new Intent();
		intent.setClass(this, ScreenSlideActivity.class);
		intent.putExtra("result", position);
		intent.putExtra("categoryId", selectedCatagory);
		// setting selecedcategory as shared preference to use later
		SharedPreferences.Editor editor = sharedPrefs.edit();
		editor.putInt(getResources().getString(R.string.selected_category_saved_value), selectedCatagory);
		editor.commit();
		// start next activity
		// Toast.makeText(getApplicationContext(), "" +
		// selectedCatagory, Toast.LENGTH_SHORT).show();
		startActivity(intent);
	}

	private void extractThumbIdsFromItemList(ArrayList<Item> itemList)
	{
		imageThumbIds = new int[itemList.size()];
		ListIterator<Item> it = itemList.listIterator();
		int i = 0;
		while (it.hasNext())
		{
			imageThumbIds[i] = it.next().getIconResourceId();
			Log.i("tag", "" + imageThumbIds[i]);
			i++;

		}
	}

}
