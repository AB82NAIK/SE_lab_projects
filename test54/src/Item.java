import java.io.*;

public class Item
{
	String item_name;
	float item_price;
	
	protected void setitem_name(String name)
	{
		this.item_name=name;
	}
	
	protected String getitem_name()
	{
		return this.item_name;
	}

	protected void setitem_price(float price)
	{
		this.item_price=price;
	}
	
	protected Float getitem_price()
	{
		return this.item_price;
	}
	
	public Item(String Iname,Float Iprice)
	{
		item_name=Iname;
		item_price=Iprice;
	}
	
}
