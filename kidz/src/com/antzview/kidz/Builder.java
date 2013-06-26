package com.antzview.kidz;
 
import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.Log;

public class Builder
{

	Resources resources;

	private static ArrayList<Category> categoryList;

	public static ArrayList<Category> getCategoryList()
	{
		return categoryList;
	}

	public void buildCategoryList(Resources res)
	{
		categoryList = new ArrayList<Category>();
		resources = res;
		try
		{
			parsexml();
		} catch (XmlPullParserException e)
		{
			  
			e.printStackTrace();
		}

	}

	public void parsexml() throws XmlPullParserException
	{
		XmlResourceParser parser = resources.getXml(R.xml.sample);
		// check state
		Category newCategory = null;
		int event = parser.getEventType();
		Log.i("tag", "before while loop");
		int categoryId = 0;
		int itemId = 0;
		while (event != XmlPullParser.END_DOCUMENT)
		{
			// get the type of the event
			event = parser.getEventType();

			// 'tagName'
			if (event == XmlPullParser.START_TAG)
			{

				String name = parser.getName();
				if (name.contentEquals("category"))
				{
					// create new cat

					String label = parser.getAttributeValue(null, "label");
					String icon = parser.getAttributeValue(null, "icon");
					int iconResourceId = getResourceId(icon, "drawable");
					// create new cat
					newCategory = new Category(categoryId++, iconResourceId, label);

					itemId = 0;
					Log.i("tag", "hjhvnbv");

				} else if (parser.getName().contentEquals("item"))
				{
					// create new item

					String icon = parser.getAttributeValue(null, "icon");
					String image = parser.getAttributeValue(null, "image");
					String label = parser.getAttributeValue(null, "label");
					String sound = parser.getAttributeValue(null, "sound");

					int iconResourceId = getResourceId(icon, "drawable");
					int imageResourceId = getResourceId(image, "drawable");
					int soundResourceId = getResourceId(sound, "raw");

					Item newItem = new Item(itemId++, label, iconResourceId, imageResourceId, soundResourceId);
					newCategory.getItemList().add(newItem);
					// add item to list
					Log.i("tag", "hjhvnbv");
				}

			}
			if (event == XmlPullParser.END_TAG && parser.getName().contentEquals("category"))
			{
				categoryList.add(newCategory);
				// add newcat to list
			}

			// go to the next tag
			try
			{
				parser.next();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Release resources associated with the parser
		parser.close();

	}

	private int getResourceId(String name, String type)
	{
		return resources.getIdentifier(name, type, "com.antzview.kidz");

	}

	public static ArrayList<Item> getItemListAtPosition(int position)
	{
		return categoryList.get(position).getItemList();
	}

}
