package com.antzview.kidz;
     
import java.util.ArrayList;
import java.util.ListIterator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CatagoryListActivity extends Activity implements OnItemClickListener, Runnable
{

	private ListView listview;
    	private Builder builder;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_catagory_list);
		builder = new Builder();
		builder.buildCategoryList(getResources());
		afterActivityCreation();

	}

 	String[] labels = null;

	private void afterActivityCreation()
	{
		listview = (ListView) findViewById(R.id.catagoryList); 
		ArrayList<Integer> imageIdList = new ArrayList<Integer>();
		Log.i("tag", "before getting attributes"); 
		getCategoryAttributesFromBuilder(imageIdList);
		Log.i("tag", "after getting attributes");
		CatagoryListAdapter adapter = new CatagoryListAdapter(getApplicationContext(), labels, imageIdList);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(this);
	}

	private void getCategoryAttributesFromBuilder(ArrayList<Integer> imageIdList)
	{
		ArrayList<Category> catList = builder.getCategoryList();
		int size = catList.size();
		labels = new String[size];
		ListIterator<Category> it = catList.listIterator();
		int i = 0;
		while (it.hasNext())
		{
			Category obj = it.next();
			labels[i] = obj.getLabel();
			imageIdList.add(obj.getCategoryIconResourceId());
			i++;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{

		Intent intent = new Intent();
		intent.setClass(this, ImageGridActivity.class);
		intent.putExtra("result", position);
		startActivity(intent);
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		
	}

}
