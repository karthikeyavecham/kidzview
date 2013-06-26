package com.antzview.kidz;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CatagoryListAdapter extends ArrayAdapter<String>
{

	private final Context context;
	private final String[] values;
	private final ArrayList<Integer> imageIdList;

	HashMap<String, Integer> mIdMap;

	public CatagoryListAdapter(Context context, String[] values, ArrayList<Integer> imageIdList)
	{
		super(context, R.layout.row_layout, values);
		this.context = context;
		this.values = values;
		this.imageIdList = imageIdList;
		//Log.i("tag","item "+imageIdList);
		
		mIdMap = new HashMap<String, Integer>();
		
		for (int i = 0; i < values.length; ++i)
		{
			mIdMap.put(values[i], i);
		}
		
	}

	@Override
	public long getItemId(int position)
	{
		String item = getItem(position);
		return mIdMap.get(item);
		//return super.getItemId(position);
	}

	@Override
	public int getCount()
	{

		// TODO Auto-generated method stub
		Log.i("tag", "item Length="+values.length);
		return values.length;
	}
	static int i=0;
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		//Log.i("tag","getview");
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.row_layout, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.rowItemLabel);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.rowItemIcon);
		textView.setText(values[position]);
		Log.i("tag", "item "+(position)+"   "+imageIdList.get(position));
		imageView.setBackgroundResource(imageIdList.get(position));
		//Log.i("tag","endgetview");
		return rowView;
	}

	@Override
	public String getItem(int position)
	{
		// TODO Auto-generated method stub
		return super.getItem(position);
	}

}
