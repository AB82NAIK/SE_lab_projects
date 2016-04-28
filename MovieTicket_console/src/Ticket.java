import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Ticket extends MovieEvent{
	int id;
	int event_id;
	java.sql.Timestamp book_time_date;
	int seat_no;
	boolean sucess;
	String report;
	
	public boolean bookTicket(int event_id,int seat_no)
	{
		CustomMethods cm=new CustomMethods();
		Connection con=null;
		boolean sucess=false;
		book_time_date=cm.getSystemDateTime();
		
		
		try{
			cm=new CustomMethods();
			con=cm.connectDb();
			if(!cm.sucess)
				this.report=cm.report;
			else
			{
				String insertString="insert into TICKET (EVENT_ID,BOOK_TIME_DATE,SEAT_NO) values (?,?,?)";
				PreparedStatement insertStmt=con.prepareStatement(insertString);
				insertStmt.setInt(1,event_id);
				insertStmt.setTimestamp(2,book_time_date);
				insertStmt.setInt(3,seat_no);
				int insert=insertStmt.executeUpdate();
				con.close();
				if(insert>0)
				{
					this.report="Ticket Sucessfully Booked";
					sucess=true;
					updateAvailableSeats(event_id);
				}
				else
					this.report="Ticket Booking Failed";
			}
			
			
		}
		catch(Exception e)
		{ 
			this.report="Ticket Booking Failed";
			System.out.println(e);
		}
		this.sucess=sucess;
		return sucess;
	}
	
	public ArrayList<Integer> getBookedTickets(int event_id)
	{
		CustomMethods cm=new CustomMethods();
		Connection con=null;
		ArrayList<Integer> booked_seat_list=new ArrayList<Integer>();
		booked_seat_list.clear();
		boolean sucess=false;
		try{
			con=cm.connectDb();
			if(!cm.sucess)
				this.report=cm.report;
			else
			{
				String searchString="select * from TICKET where EVENT_ID=?";
				PreparedStatement searchStmt=con.prepareStatement(searchString);
				searchStmt.setInt(1,event_id);
				ResultSet rs=searchStmt.executeQuery();
				while(rs.next())
				{
					booked_seat_list.add(rs.getInt(4));
					sucess=true;
				}
				con.close();
				if(sucess)
					this.report="Ticket Search Sucessfull";
				else
					this.report="Ticket Search Failed";
			}
		}
		catch(Exception e)
		{ 
			this.report="Ticket Search Failed";
			System.out.println(e);
		}
		
		this.sucess=sucess;
		return booked_seat_list;

	}
	


}
