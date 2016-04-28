import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class MovieEvent extends Movie{
	int id;
	int movie_id;
	java.sql.Timestamp start_time;
	java.sql.Timestamp end_time;
	int screen_no;
	int seats_available;
	float price;
	String report;
	boolean sucess;

	
	public boolean createMovieEvent(MovieEvent movieEvent)
	{

		CustomMethods cm=new CustomMethods();
		List<Movie> movie_data=new ArrayList<Movie>();
		Connection con=null;
		boolean sucess=false;
		Screen screen=new Screen();
		
		movie_data.clear();
		movie_data=getMovie(movieEvent.movie_id,null,null);
		

		movieEvent.end_time=cm.calEndTime(movieEvent.start_time,movieEvent.movie_id);
		movieEvent.seats_available=screen.getAvailableSeats(movieEvent.screen_no);
		
		if(movieEvent.start_time.compareTo(cm.getSystemDateTime())<0)
		{
			sucess=false;
			this.report="event date time less than current date time";
		}
		else if(!validateShowDateTime(movieEvent))
		{
			sucess=false;
			this.report="event date time colliding with other event";
		}
		else if(movie_data.get(0).release_date.compareTo(movieEvent.start_time)>0)
		{
			sucess=false;
			this.report="event creation before release date";
		}
		else
		{
		
			try
			{
				cm=new CustomMethods();
				con=cm.connectDb();
				if(!cm.sucess)
				{
					this.report=cm.report;
				}
				else
				{
					String insertString="insert into MOVIE_EVENT (MOVIE_ID,START_TIME,END_TIME,SCREEN_NO,PRICE,SEATS_AVAILABLE) values (?,?,?,?,?,?)";
					PreparedStatement insertStmt=con.prepareStatement(insertString);
					insertStmt.setInt(1,movieEvent.movie_id);
					insertStmt.setTimestamp(2,movieEvent.start_time);
					insertStmt.setTimestamp(3,movieEvent.end_time);
					insertStmt.setInt(4,movieEvent.screen_no);
					insertStmt.setFloat(5,movieEvent.price);
					insertStmt.setInt(6,screen.getAvailableSeats(movieEvent.screen_no));
					int insert=insertStmt.executeUpdate();
					if(insert>0)
					{
						this.report="Event Sucesfully Created";
						sucess=true;
					}
					else
					{
						this.report="Event Creation Failed";
					}
					con.close();
				}
			}
			catch(Exception e)
			{
				this.report="Event Creation Failed";
				System.out.println(e);
			}
		}
			
		this.sucess=sucess;
		return sucess;
	}
	

	public boolean deleteMovieEvent(int event_id)
	{
		boolean sucess=false;
		CustomMethods cm=new CustomMethods();
		Connection con=null;
		try{
			con=cm.connectDb();
			if(!cm.sucess)
			{
				this.report=cm.report;
			}
			else
			{
				String delete_string="delete from MOVIE_EVENT where EVENT_ID=?";
				PreparedStatement delete_stmt=con.prepareStatement(delete_string);  
				delete_stmt.setInt(1,event_id); 
				int delete=delete_stmt.executeUpdate(); 
				if(delete>0)
				{
					sucess=true;
					this.report="Event Sucessfully Deleted";
				}
				else
				{
					this.report="Event Deletion Failed";
				}
				con.close();
			}
		}
		catch(Exception e)
		{ 
			this.report="Event Deletion Failed";
			System.out.println(e);
		}
		this.sucess=sucess;
		return sucess;
	}
	
	public boolean updateMovieEvent(MovieEvent movieEvent)
	{
		boolean sucess=false;
		CustomMethods cm=new CustomMethods();
		List<Movie> movie_data=new ArrayList<Movie>();
		Connection con=null;
		
		movieEvent.end_time=cm.calEndTime(movieEvent.start_time,movieEvent.movie_id);
		
		if(movieEvent.start_time.compareTo(cm.getSystemDateTime())<0)
		{
			sucess=false;
			this.report="event date time less than current date time";
		}
		else if(!validateShowDateTime(movieEvent))
		{
			sucess=false;
			this.report="event date time colliding with other event";
		}
		else if(movie_data.get(0).release_date.compareTo(movieEvent.start_time)>0)
		{
			sucess=false;
			this.report="event creation before release date";
		}
		else
		{
	
			try{
				cm=new CustomMethods();
				con=cm.connectDb();
				if(!cm.sucess)
				{
					this.report=cm.report;
				}
				else
				{
					String update_string="update MOVIE_EVENT set START_TIME=?,END_TIME=?,SCREEN_NO=?,PRICE=?,MOVIE_ID=? where EVENT_ID=?";
					PreparedStatement update_stmt=con.prepareStatement(update_string);  
					update_stmt.setTimestamp(1,movieEvent.start_time);
					update_stmt.setTimestamp(2,movieEvent.end_time);
					update_stmt.setInt(3,movieEvent.screen_no);
					update_stmt.setFloat(4,movieEvent.price);
					update_stmt.setInt(5,movieEvent.movie_id);
					update_stmt.setInt(6,movieEvent.id);
					int update=update_stmt.executeUpdate(); 
					if(update>0)
					{
						this.report="Event Sucessfully Updated";
						sucess=true;
					}
					else
						this.report="Event Updation Failed";
					con.close();
				}
			}
			catch(Exception e)
			{ 
				this.report="Event Updation Failed";
				System.out.println(e);
			}
		}
		this.sucess=sucess;
		return sucess;
	}
	
	public List<MovieEvent> getMovieEvent(int event_id,int screen_no,String start_date)
	{
		CustomMethods cm=new CustomMethods();
		List<MovieEvent> movieEvent_data=new ArrayList<MovieEvent>();
		Connection con=null;
		String searchString="select * from MOVIE_EVENT";
		if(event_id>=0 && screen_no<0 && start_date==null)
		{
			searchString="select * from MOVIE_EVENT where EVENT_ID=?";
		}
		if(event_id<0 && screen_no>0 && start_date==null)
		{
			searchString="select * from MOVIE_EVENT where SCREEN_NO=?";
		}
		if(event_id<0 && screen_no<0 && start_date!=null)
		{
			searchString="select * from MOVIE_EVENT where trunc(START_TIME)=to_date(?,'yyyy-mm-dd')";
		}
		
		
		boolean sucess=false;
		try{
			con=cm.connectDb();
			if(!cm.sucess)
			{
				this.report=cm.report;
			}
			else
			{
				PreparedStatement stmt2=con.prepareStatement(searchString);
				if(event_id>=0 && screen_no<0 && start_date==null)
					stmt2.setInt(1,event_id);

				if(event_id<0 && screen_no>0 && start_date==null)
				{
					stmt2.setInt(1,screen_no);
				}
				
				if(event_id<0 && screen_no<0 && start_date!=null)
				{
					stmt2.setString(1,start_date);
				}
				ResultSet rs=stmt2.executeQuery();
				while(rs.next())
				{
					MovieEvent movie_event=new MovieEvent();
					movie_event.id=rs.getInt(1);
					movie_event.movie_id=rs.getInt(2);
					movie_event.start_time=rs.getTimestamp(3);
					movie_event.end_time=rs.getTimestamp(4);
					movie_event.screen_no=rs.getInt(5);
					movie_event.price=rs.getFloat(6);
					movie_event.seats_available=rs.getInt(7);
					movieEvent_data.add(movie_event);
					sucess=true;
	
				}	
				if(sucess)
				{
					this.report="Event Search Sucess";
				}
				else
				{
					this.report="Event Search Failed Or No Result";
				}
				 con.close();
			}
		}
		catch(Exception e)
		{
			this.report="Event Search Failed Or No Result";
			System.out.println(e);
		}	

		this.sucess=sucess;
		return movieEvent_data;

	}
	
	public boolean updateAvailableSeats(int event_id)
	{
		boolean sucess=false;
		CustomMethods cm=new CustomMethods();
		Connection con=null;
		List<MovieEvent> movieEvent_data=new ArrayList<MovieEvent>();
		Screen screen=new Screen();
		Ticket ticket=new Ticket();
		
		movieEvent_data=getMovieEvent(event_id,-1,null);
		
		int available_seats_screen=screen.getAvailableSeats(movieEvent_data.get(0).screen_no);
		
		int booked_seats_event=ticket.getBookedTickets(event_id).size();
		
		int available_seats_event=available_seats_screen-booked_seats_event;
		
		try{
			con=cm.connectDb();
			if(!cm.sucess)
				this.report=cm.report;
			else
			{
				String updateString="update MOVIE_EVENT set SEATS_AVAILABLE=? where EVENT_ID=?";
				PreparedStatement updateStmt=con.prepareStatement(updateString);
				updateStmt.setInt(1,available_seats_event);
				updateStmt.setInt(2, event_id);
				int update=updateStmt.executeUpdate();
				if(update>0)
				{
					this.report="Update Available Seats Sucessfull.";
					sucess=true;
				}
				else
				{
					this.report="Update Available Seats Failed.";
				}
				
				con.close();
			}
		}
		catch(Exception e)
		{
			this.report="Update Available Seats Failed.";
			System.out.println(e);
		}
		this.sucess=sucess;
		return sucess;
	}
	
	public ArrayList<Integer> getAvailableSeatNo(int event_id)
	{
		Screen screen=new Screen();
		Ticket ticket=new Ticket();
		List<MovieEvent> movieEvent_data=new ArrayList<MovieEvent>();
		List<Screen> screen_list=new ArrayList<Screen>();
		ArrayList<Integer> booked_seats=new ArrayList<Integer>();
		ArrayList<Integer> available_seat_no=new ArrayList<Integer>();
		List<Screen> deleted_seats=new ArrayList<Screen>();
		movieEvent_data.clear();
		

		movieEvent_data=getMovieEvent(event_id,-1,null);
		

		booked_seats=ticket.getBookedTickets(event_id);
		
		screen_list.clear();
		screen_list=screen.getScreen(movieEvent_data.get(0).screen_no,null);
		
		int total_seats=screen_list.get(0).total_seats;
		for(int i=1;i<=total_seats;i++)
		{
			available_seat_no.add(i);
		}

		for(int k=0;k<booked_seats.size();k++)
		{
			for(int i=0;i<available_seat_no.size();i++)
			{
				if(available_seat_no.get(i)==booked_seats.get(k))
				{
					available_seat_no.remove(i);
				}
			}
		}

		deleted_seats=screen.getDeletedSeats(movieEvent_data.get(0).screen_no);

		for(int k=0;k<deleted_seats.size();k++)
		{
			for(int i=0;i<available_seat_no.size();i++)
			{
				if(available_seat_no.get(i)==deleted_seats.get(k).seat_no)
				{
					available_seat_no.remove(i);
				}
				
			}
		}
		
		return available_seat_no;
			
	}
	
	public boolean validateShowDateTime(MovieEvent user_event)
	{
		boolean flag=true;
		CustomMethods cm=new CustomMethods();
		Connection con=null;
		MovieEvent movie_event=new MovieEvent();
		List<MovieEvent> event_data=new ArrayList<MovieEvent>();

		event_data.clear();
		event_data=movie_event.getMovieEvent(-1,-1,null);
		
		for(int i=0;i<event_data.size();i++)
		{
			if(event_data.get(i).screen_no==user_event.screen_no)
			{
				if(user_event.id!=event_data.get(i).id)
				{
					if(user_event.start_time.compareTo(event_data.get(i).start_time)>0)
					{
						
						if(user_event.start_time.compareTo(event_data.get(i).end_time)<0)
						{
							
							flag=false;
						}
					}
					
					java.sql.Timestamp end_time=cm.calEndTime(user_event.start_time, user_event.movie_id);
					if(end_time.compareTo(event_data.get(i).start_time)>0)
					{
						if(end_time.compareTo(event_data.get(i).end_time)<0)
						{
							
							flag=false;
						}
					}
					if(user_event.start_time.equals(event_data.get(i).start_time)||end_time.equals(event_data.get(i).end_time))
					{
						flag=false;
					}
				}
			}

		}
		event_data.clear();

		
		return flag;
	}

}
