package com.antzview.kidz;

import java.util.ArrayList;

public class Category
{
	private int categoryId;
	private int categoryIconResourceId;
	private String label;
	private ArrayList<Item> itemList;

	public Category()
	{
		this.categoryIconResourceId = 0;
		this.label = null;
		itemList = new ArrayList<Item>();

	}

	public Category(int categoryId, int categoryIconResourceId, String label)
	{
		this.categoryIconResourceId = categoryIconResourceId;
		this.label = label;
		itemList = new ArrayList<Item>();
		this.categoryId = categoryId;

	}

	public Category(int categoryIconResourceId, String label, ArrayList<Item> itemList)
	{
		this.categoryIconResourceId = categoryIconResourceId;
		this.label = label;
		this.itemList = itemList;

	}

	public int getCategoryIconResourceId()
	{
		return categoryIconResourceId;
	}

	public void setCategoryIconResourceId(int categoryIconResourceId)
	{
		this.categoryIconResourceId = categoryIconResourceId;
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

	public ArrayList<Item> getItemList()
	{
		return itemList;
	}

	public void setItemList(ArrayList<Item> itemList)
	{
		this.itemList = itemList;
	}

	public void addItem(Item newItem)
	{
		itemList.add(newItem);
	}

	public Item getItemAt(int index)
	{
		return itemList.get(index);
	}

	public int getCategoryId()
	{
		return categoryId;
	}

	public void setCategoryId(int categoryId)
	{
		this.categoryId = categoryId;
	}
}
