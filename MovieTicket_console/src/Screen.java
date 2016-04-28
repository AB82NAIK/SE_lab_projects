import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Screen {
	
	int screen_no;
	int seat_no;
	int total_seats;
	int total_screens;
	String screen_type;
	String report;
	boolean sucess; 
	
	public boolean setScreen(Screen screen)
	{
		CustomMethods cm=new CustomMethods();
		Connection con=null;
		boolean sucess=false;
		if(!validateScreen(screen))
		{
			sucess=false;
			this.report="Invalid Screen Parameters";
		}
		else
		{
			try{
				con=cm.connectDb();
				if(!cm.sucess)
					this.report=cm.report;
				else
				{
					String insertString="insert into SCREEN(SCREEN_NO,TOTAL_SEATS,SCREEN_TYPE) values (?,?,?)";
					PreparedStatement insertStmt=con.prepareStatement(insertString);
					insertStmt.setInt(1,screen.screen_no);
					insertStmt.setInt(2,screen.total_seats);
					insertStmt.setString(3,screen.screen_type);
					int insert=insertStmt.executeUpdate();
					if(insert>0)
					{
						sucess=true;
						this.report="Screen Sucessfully Added";
					}
					else
					{
						this.report="Screen Adding Failed";
					}
					con.close();
				}
				
			}
			catch(Exception e)
			{ 
				this.report="Screen Adding Failed";
				System.out.println(e);
			}	
		}
		
		this.sucess=sucess;
		return sucess;
	}
	public boolean updateScreen(Screen screen)
	{
		CustomMethods cm=new CustomMethods();
		Connection con=null;
		boolean sucess=false;
		if(!validateScreen(screen))
		{
			sucess=false;
			this.report="Invalid Screen Parameters";
		}
		else
		{		
			try{
				con=cm.connectDb();
				if(!cm.sucess)
					this.report=cm.report;
				else
				{
					String updateString="update SCREEN set TOTAL_SEATS=?,SCREEN_TYPE=? where SCREEN_NO=?";
					PreparedStatement updateStmt=con.prepareStatement(updateString);
					updateStmt.setInt(1,screen.total_seats);
					updateStmt.setString(2, screen.screen_type);
					updateStmt.setInt(3,screen.screen_no);
					int update=updateStmt.executeUpdate();
					if(update>0)
					{
						this.report="Screen Sucessfully Updated";
						sucess=true;
					}
					else
						this.report="Screen Updation Failed";
					con.close();
				}
				
			}
			catch(Exception e)
			{ 
				this.report="Screen Updation Failed";
				System.out.println(e);
			}
		}
		this.sucess=sucess;
		return sucess;
		
	}
	public boolean deleteScreen(int screen_no)
	{
		CustomMethods cm=new CustomMethods();
		Connection con=null;
		boolean sucess=false;
		
		try{
			con=cm.connectDb();
			if(!cm.sucess)
				this.report=cm.report;
			else
			{
				String deleteString="delete from SCREEN where SCREEN_NO=?";
				PreparedStatement deleteStmt=con.prepareStatement(deleteString);
				deleteStmt.setInt(1,screen_no);
				int delete=deleteStmt.executeUpdate();
				if(delete>0)
				{
					this.report="Screen Sucessfully Deleted";
					sucess=true;
				}
				else
					this.report="Screen Deletion Failed Or No Record To Delete";
				con.close();
			}
		}
		catch(Exception e)
		{ 
			this.report="Screen Deletion Failed Or No Record To Delete";
			System.out.println(e);
		}
		this.sucess=sucess;
		return sucess;
	}
	
	public List<Screen> getScreen(int screen_no,String screen_type)
	{
		CustomMethods cm=new CustomMethods();
		Connection con=null;
		List<Screen> screen_list=new ArrayList <Screen>();
		
		screen_list.clear();
		String searchString;
		boolean sucess=false;

		if(screen_type!=null)
			searchString="select * from SCREEN where SCREEN_TYPE= ?";
		else
		{
			if(screen_no<0)
				searchString="select * from SCREEN";
			else
				searchString="select * from SCREEN where SCREEN_NO= ?";
		}
		try{
			con=cm.connectDb();
			if(!cm.sucess)
				this.report=cm.report;
			else
			{
				PreparedStatement searchStmt=con.prepareStatement(searchString);
				ResultSet rs;
				if(screen_type!=null)
				{
					searchStmt.setString(1,screen_type);
					rs=searchStmt.executeQuery();
				}
				else
				{
					if(screen_no<0)
					{
						rs=searchStmt.executeQuery();
					}
					else
					{	
						searchStmt.setInt(1,screen_no);
						rs=searchStmt.executeQuery();
					}
				}
				while(rs.next())
				{
					Screen screen=new Screen();
					screen.screen_no=rs.getInt(1);
					screen.total_seats=rs.getInt(2);
					screen.screen_type=rs.getString(3);
					screen_list.add(screen);
					sucess=true;
				}
		        if(sucess)
		        	this.report="Screen Search Sucessfull";
		        else
		        	this.report="Screen Search Failed";
				con.close();
			}
		}
		catch(Exception e)
		{
			this.report="Screen Search Failed";
			System.out.println(e);
		}

        
        this.sucess=sucess;
		return screen_list;

	}
	
	public boolean deleteSeats(int seat_no,int screen_no)
	{
		CustomMethods cm=new CustomMethods();
		Connection con=null;
		boolean sucess=false;
		
		try{
			con=cm.connectDb();
			if(!cm.sucess)
				this.report=cm.report;
			else
			{
				String insertString="insert into DELETED_SEATS values(?,?)";
				PreparedStatement insertStmt=con.prepareStatement(insertString);
				insertStmt.setInt(1,screen_no);
				insertStmt.setInt(2,seat_no);
				int insert=insertStmt.executeUpdate();
				if(insert>0)
				{
					this.report="Seat Deletion Sucessfull";
					sucess=true;
				}
				else
					this.report="Seat Deletion Failed";
				con.close();
			}
			
		}
		catch(Exception e)
		{ 
			this.report="Seat Deletion Failed";
			System.out.println(e);
		}

	
		this.sucess=sucess;
		return sucess;
	}
	
	public List<Screen> getDeletedSeats(int screen_no)
	{
		CustomMethods cm=new CustomMethods();
		Connection con=null;
		List<Screen> screen_list=new ArrayList <Screen>();
		
		screen_list.clear();
		boolean sucess=false;
		try
		{
			con=cm.connectDb();
			if(!cm.sucess)
				this.report=cm.report;
			else
			{
				String searchString="select * from DELETED_SEATS where SCREEN_NO=?";
				PreparedStatement searchStmt=con.prepareStatement(searchString);
				searchStmt.setInt(1,screen_no);
				ResultSet rs=searchStmt.executeQuery();
				while(rs.next())
				{
					Screen screen=new Screen();
					screen.screen_no=rs.getInt(1);
					screen.seat_no=rs.getInt(2);
					screen_list.add(screen);
					sucess=true;
				}
				if(sucess)
					this.report="Deleted Seats Search Sucessfull";
				else
					this.report="Deleted Seats Search failed";
				con.close();
			}
		}
		catch(Exception e)
		{ 
			this.report="Deleted Seats Search failed";
			System.out.println(e);
		}

		
		this.sucess=sucess;
		return screen_list;
	}
	

	
	public int getAvailableSeats(int screen_no)
	{

		int available_seats=0;
		int deleted_seats=0;
		int total_seats=0;
		List<Screen> screen_list=new ArrayList <Screen>();
		screen_list.clear();
		screen_list=getScreen(screen_no,null);
		total_seats=screen_list.get(0).total_seats;
		
		screen_list.clear();
		screen_list=getDeletedSeats(screen_no);
		
		deleted_seats=screen_list.size();
		available_seats=total_seats-deleted_seats;
		return available_seats;

	}
	
	public List getAvailableSeatNo(int screen_no)
	{
		List available_seat_no=new ArrayList();
		List deleted_seat_no=new ArrayList();
		List<Screen> screen_list=new ArrayList <Screen>();
		
		Screen screen=new Screen();
		screen_list.clear();
		screen_list=screen.getScreen(screen_no, null);
		int total_seats=screen_list.get(0).total_seats;
		
		screen=new Screen();
		screen_list.clear();
		screen_list=screen.getDeletedSeats(screen_no);
		
		for(int i=1;i<=total_seats;i++)
		{
			available_seat_no.add(i);
		}
		for(int i=0;i<screen_list.size();i++)
		{
			deleted_seat_no.add(screen_list.get(i).seat_no);
		}
		
		for(int i=0;i<deleted_seat_no.size();i++)
		{
			for(int j=0;j<available_seat_no.size();j++)
			{
				if(deleted_seat_no.get(i)==available_seat_no.get(j))
				{
					available_seat_no.remove(j);
				}
			}
	
		}
		return available_seat_no;
	}
	
	public boolean undoDeleteSeat(int seat_no,int screen_no)
	{
		CustomMethods cm=new CustomMethods();
		Connection con=null;
		boolean sucess=false;
		
		String deleteString="delete from DELETED_SEATS where SCREEN_NO=? and SEAT_NO=?";
		try{
			con=cm.connectDb();
			if(!cm.sucess)
				this.report=cm.report;
			else
			{
				PreparedStatement deleteStmt=con.prepareStatement(deleteString);
				deleteStmt.setInt(1,screen_no);
				deleteStmt.setInt(2,seat_no);
				int delete=deleteStmt.executeUpdate();
				if(delete>0)
				{
					this.report="Undo Deleted Seats Sucessfull";
					sucess=true;
				}
				else
					this.report="Undo Deleted Seats Failed";
				con.close();
			}
		}
		catch(Exception e)
		{
			this.report="Undo Deleted Seats Failed";
			System.out.println(e);
		}
		
		this.sucess=sucess;
		return sucess;
	}
	
	public boolean validateScreen(Screen screen)
	{
		if(screen.screen_no<0)
			return false;
		else if(screen.screen_type.isEmpty())
			return false;
		else if(screen.total_seats<=0)
			return false;
		else
			return true;
			
	}
	
	public List validateDeleteSeats(List deleting_seat,int screen_no)
	{
		CustomMethods cm=new CustomMethods();
		List valid_seats=new ArrayList();
		MovieEvent movie_event=new MovieEvent();
		List<MovieEvent> event_list=new ArrayList<MovieEvent>();
		
		event_list=movie_event.getMovieEvent(-1,screen_no,null);
		for(int i=0;i<event_list.size();i++)
		{
			if(cm.getSystemDateTime().compareTo(event_list.get(i).start_time)>0)
			{
				event_list.remove(i);
			}
		}
		
		Ticket ticket=new Ticket();
		List<Integer> booked_seats=new ArrayList<Integer>();
		
		for(int i=0;i<event_list.size();i++)
		{
			booked_seats.clear();
			booked_seats=ticket.getBookedTickets(event_list.get(i).id);
			for(int k=0;k<booked_seats.size();k++)
			{
				deleting_seat.remove(booked_seats.get(k));
			}
		}
		
		return deleting_seat;

	}
	


}
