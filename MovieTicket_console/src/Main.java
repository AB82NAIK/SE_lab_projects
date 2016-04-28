import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CustomMethods cm=new CustomMethods();
		List<Movie> movie_data=new ArrayList<Movie>();
		List<MovieEvent> event_data=new ArrayList<MovieEvent>();
		List screen_data=new ArrayList();
		ArrayList<Integer> booked_seat_data=new ArrayList<Integer>();
		ArrayList<Integer> available_seat_no=new ArrayList<Integer>();
		MovieEvent me=new MovieEvent();
		MovieEvent me2=new MovieEvent();
		Ticket t=new Ticket();
		Screen s=new Screen();
		Screen s2=new Screen();
		Movie m2=new Movie();
		Movie m=new Movie();
		String[] duration=new String[2];
		java.sql.Timestamp date;
		SignUp signup=new SignUp();
		
		
		//#######################################################################################################################
		//-------------------add movie-------------------------//
		/*m.name="BAJIRAO MASTANI";
		m.ratting="9";
		m.genere="ACTION DRAMA";
		m.release_date=cm.stringToTimeStamp("2016-03-08 9:00");
		duration[0]="1";
		duration[1]="15";
		m.duration=duration;
		m.screen_type="3d";
		m.language="HINDI";
		m.addMovie(m);*/
		
		//-------------------update movie-------------------------//
		/*m.id=22;
		m.name="10000 BC";
		m.ratting="9";
		m.genere="FANTASY";
		m.release_date=cm.stringToTimeStamp("2016-11-11 9:00");
		duration[0]="1";
		duration[1]="15";
		m.duration=duration;
		m.screen_type="3d";
		m.language="ENGLISH";
		m2.updateMovie(m);*/
		
		//---------------------delete movie------------------------//
		/*m2.deleteMovie(22);
		System.out.println(m2.report);*/
		
		//---------------------get movie--------------------------//
		/*String[] ss={"11","2016"};
		movie_data=m2.getMovie(29,null,null);
        for(int i=0;i<movie_data.size();i++)
        {
        	System.out.println(movie_data.get(i).name);
        }*/
        
        
		
		//#######################################################################################################################
        //---------------------create movie event-----------------------//
		/*me2.movie_id=29;
		me2.start_time=cm.stringToTimeStamp("2016-04-17 05:33");
		me2.screen_no=1;
		me2.price=(float) 400.0;
		me.createMovieEvent(me2);
		System.out.println(me.report);*/
		
		//---------------------update movie event-----------------------//
		/*me2.id=22;
		me2.movie_id=23;
		me2.start_time=cm.stringToTimeStamp("2016-11-11 10:30");
		me2.screen_no=2;
		me2.price=(float) 8000.0;
		me.updateMovieEvent(me2);*/
		
		//----------------------delete movie event---------------------//
		//me.deleteMovieEvent(22);
		
		//----------------------get movie event------------------------//
		/*event_data=me.getMovieEvent(-1,1,null);
        for(int i=0;i<event_data.size();i++)
        {
        	System.out.println(event_data.get(i).movie_id);
        }*/
		//-----------------------get available seats for event-------------------------//
		//event_data=me.getMovieEvent(26,null);
		//System.out.println("available seats for event="+event_data.get(0).seats_available);
		//------------------------update available seats-------------------------------//
		//me.updateAvailableSeats(26);
		//--------------------------get available seat numbers---------------------------//
		
		/*available_seat_no=me.getAvailableSeatNo(26);
        for(int i=0;i<available_seat_no.size();i++)
        {
        	System.out.println(available_seat_no.get(i));
        }*/
		
		//#######################################################################################################################
		
		//---------------------book ticket--------------------------------//
		//t.bookTicket(26,1);
		
		
		//----------------------get booked tickets------------------------//
		/*booked_seat_data=t.getBookedTickets(28);
		if(booked_seat_data.size()!=0)
        for(int i=0;i<booked_seat_data.size();i++)
        {
        	System.out.println(booked_seat_data.get(i));
        }
		else
		{
			System.out.println("event doesnot exist");
		}
		*/
		//#######################################################################################################################
		//---------------------set screen---------------------------------//
		/*s2.screen_no=3;
		s2.screen_type="3d";
		s2.total_seats=200;
		s.setScreen(s2);
		System.out.println(s.report);*/
		
		//---------------------delete screen----------------------------//
		//s.deleteScreen(3);
		
		//---------------------update screen----------------------------//
		/*s2.screen_no=1;
		s2.screen_type="3d";
		s2.total_seats=1000;
		s.updateScreen(s2);*/
		//---------------------get screen------------------------------//
		/*screen_data=s.getScreen(3,null);
        for(int i=0;i<screen_data.size();i++)
        {
        	System.out.println(screen_data.get(i).screen_type);
        }*/
		//---------------------delete seats--------------------------//
		//s.deleteSeats(100,1);
		//--------------------get deleted seats-----------------------//
		/*screen_data=s.getDeletedSeats(4);
		System.out.println(screen_data.size());
        for(int i=0;i<screen_data.size();i++)
        {
        	System.out.println(screen_data.get(i).seat_no);
        }*/
		//------------------------get available seat numbers-----------------------------//
		/*screen_data=s.getAvailableSeatsNo(1);
		System.out.println("screen daata="+screen_data.size());
       /* for(int i=0;i<screen_data.size();i++)
        {
        	//System.out.println(screen_data.get(i).seat_no);
        }*/
		//--------------------get total screen seats------------------//
		//System.out.println(s.getTotalSeats(1));
		
		//-----------------------get total available seats------------------//
		//System.out.println(s.getAvailableSeats(1));
		//-----------------------undo delete seats---------------------------//
		//s.undoDeleteSeat(33, 1);
		//---------------------------date comparision-------------------------//
		/*java.sql.Timestamp date1,date2;
		date1=cm.stringToTimeStamp("2016-11-12 00:00");
		date2=cm.stringToTimeStamp("2016-11-11 00:00");
		//1 if greater , 0 if equal ,-1 if less.
		System.out.println(date2.compareTo(date1));*/
		
		//System.out.println(cm.stringToTimeStamp("2016-11-11 00:00"));
			
		//-------------------------------get current date time------------------------------------//
		/*date=cm.getSystemDateTime();
		System.out.println(date);*/
		
		//--------------------------------------Sign Up class........................................//
		
		//------------------------add user----------------------//
		//signup.addUser("deepraj","deep1234");
		//--------------------get user--------------------------//
		/*signup=signup.getUser("deepraj");
		System.out.println("user="+signup.user_name+" pass="+signup.password);*/
		//----------------------------log in class-------------------------------------------------------//
		//------------login---------------------------------//
		
		/*LogIn login=new LogIn("deepraj","deep1234");
		System.out.println("sucess="+login.sucess);
		if(login.sucess)
		{
			System.out.println("welcome user");
		}
		else
			System.out.println("wrong username or password");*/
		//-----------------------------------------------------
		/*String a="ss";
		try
		{
			int b=Integer.parseInt(a);
			System.out.println("b="+b);
		}
		catch(Exception e)
		{
			System.out.println("not number");
		}*/

		//---------------------------------------------------------
		/*Screen screen=new Screen();
		List delete_seats=new ArrayList();
		
		for(int i=1;i<11;i++)
			delete_seats.add(i);
		
		List valid_seats=new ArrayList();
		valid_seats=screen.validateDeleteSeats(delete_seats, 6);
		
		for(int i=0;i<valid_seats.size();i++)
			System.out.println(valid_seats.get(i));*/
		
	}

	
}
