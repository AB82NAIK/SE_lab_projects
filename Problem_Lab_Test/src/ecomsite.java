import java.io.*;
public class ecomsite 
{
	private int c;
	
	public static void main(String[] args) 
	{
		Item i1 = new Item("Moto_G", (float) 13000);
		
		ecomsite flipcart=new ecomsite();
		
		flipcart.choosePayment(3);
		flipcart.makePayment(i1.getitem_price());
	}
	
	protected void choosePayment(int c)
	{
		if(c==1)
		{
			System.out.println("!! CASH ON DELIVERY !!");
			
		}
		else if(c==2)
		{
	    	System.out.println("!! CREDIT CARD  !!");
		}
		else if(c==3)
		{
	    	System.out.println("!! DEBIT CARD !!");
		}
		else if(c==4)
		{
	    	System.out.println("!! NETBANKING !!");
		}
	}
	
	protected void makePayment(float p)
	{
		System.out.println("Toatal Amount Payable:-"+p);
	}

}
