package com.antzview.kidz;

public class Item
{
	private String imageDesc;
	private int iconResourceId;
	private int fullImageResourceId;
	private int soundResourceId;
	private int itemId=0;
	public Item()
	{
		 
	}
	public Item(int itemId, String imageDesc, int iconResourceId, int fullImageResourceId, int soundResourceId)
	{
		this.itemId=itemId;
		this.imageDesc=imageDesc;
		this.iconResourceId=iconResourceId;
		this.fullImageResourceId=fullImageResourceId;
		this.soundResourceId=soundResourceId;
	}
	public String getImageDesc()
	{
		return imageDesc;
	}

	public void setImageDesc(String imageDesc)
	{
		this.imageDesc = imageDesc;
	}

	public int getIconResourceId()
	{
		return iconResourceId;
	}

	public void setIconResourceId(int iconResourceId)
	{
		this.iconResourceId = iconResourceId;
	}

	public int getFullImageResourceId()
	{
		return fullImageResourceId;
	}

	public void setFullImageResourceId(int fullImageResourceId)
	{
		this.fullImageResourceId = fullImageResourceId;
	}

	public int getSoundResourceId()
	{
		return soundResourceId;
	}

	public void setSoundResourceId(int soundResourceId)
	{
		this.soundResourceId = soundResourceId;
	}

}
