import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomMethods {
	String report;
	boolean sucess;

	
	
	public java.sql.Timestamp stringToTimeStamp(String date_string)
	{
		java.sql.Timestamp release_date=null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try{
		java.util.Date date = sdf.parse(date_string.trim());
		release_date=new java.sql.Timestamp(date.getTime());
		}catch(Exception e){System.out.println(e);}
		return release_date;
	}
	public Connection connectDb()
	{
		boolean sucess=false;
		Connection con=null;
		try
		{  
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			//step2 create  the connection object  
			con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:xe","deep1234","deep1234"); 
			sucess=true;
			  
		}
		catch(Exception e)
		{ 
			this.report="Connection Failed Try Restarting Application Or Check Adapter";
			System.out.println(e);
		}
		this.sucess=sucess;
		if(sucess)
		{
			this.report="Connection Sucessfull";
			return con;
		}
		else
			return null;
	}
	
	public java.sql.Timestamp calEndTime(java.sql.Timestamp start_date_time,int movie_id)
	{
		CustomMethods cm=new CustomMethods();
		Movie movie=new Movie();
		List<Movie> movie_data=new ArrayList<Movie>();
		movie_data.clear();
		movie_data=movie.getMovie(movie_id,null,null);
		if(movie_data==null)
		{
			return null;
		}
		else{
		String[] start_date=start_date_time.toString().split(" ");
		String[] start_time=start_date[1].split(":");
		String[] duration=new String[2];
		duration[0]=movie_data.get(0).duration[0];
		duration[1]=movie_data.get(0).duration[1];
		int hrs=Integer.parseInt(start_time[0])+Integer.parseInt(duration[0]);
		if(hrs>24)
		{
			int dif=hrs-24;
			hrs=dif;
		}
		int mns=Integer.parseInt(start_time[1])+Integer.parseInt(duration[1]);
		if(mns>=60)
		{
			int dif=mns/60;
			mns=mns%60;
			hrs=hrs+dif;
		}
		duration[0]=String.valueOf(hrs);
		duration[1]=String.valueOf(mns);
		return cm.stringToTimeStamp(start_date[0]+" "+duration[0]+":"+duration[1]);
		}
	}
	
	public java.sql.Timestamp getSystemDateTime()
	{
	      Date dNow = new Date( );
	      SimpleDateFormat ft = 
	      new SimpleDateFormat ("yyyy-MM-dd hh:mm");
	      return stringToTimeStamp(ft.format(dNow).toString());
	}
	

}
