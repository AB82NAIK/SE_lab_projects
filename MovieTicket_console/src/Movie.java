import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class Movie {
	
	String name;
	int id;
	String genere;
	String screen_type;
	String language;
	java.sql.Timestamp release_date;
	String ratting;
	String[] duration=new String[2];
	String report;
	boolean sucess;
	

	
	public List<Movie> getMovie(int mv_id,String mv_name,String[] month_year)
	{
		String searchString=null;
		boolean sucess=false;
		if(mv_id>=0 && mv_name==null && month_year==null)
		{
			searchString="select * from MOVIE where MOVIE_ID=?";
		}
		if(mv_id<0 && mv_name==null && month_year==null)
		{
			searchString="select * from MOVIE";
		}
		if(mv_id<0 && mv_name!=null && month_year==null)
		{
			searchString="select * from MOVIE where M_NAME=?";
		}
		if(month_year!=null && mv_id<0 && mv_name==null)
		{
			searchString="select * from MOVIE where EXTRACT(MONTH FROM RELEASE_DATE)=? and EXTRACT(YEAR FROM RELEASE_DATE)=?";
		}
		CustomMethods cm=new CustomMethods();
		Connection con=null;
		List<Movie> data=new ArrayList<Movie>();
		
		try{

			con=cm.connectDb();
			if(!cm.sucess)
			{
				this.report=cm.report;
			}
			else
			{
				PreparedStatement stmt2=con.prepareStatement(searchString);
				if(mv_id>=0 && mv_name==null && month_year==null)
					stmt2.setInt(1,mv_id);
				if(mv_id<0 && mv_name!=null && month_year==null)
				{
					stmt2.setString(1,mv_name);
				}
				
				if(month_year!=null && mv_id<0 && mv_name==null)
				{
					stmt2.setString(1,month_year[0]);
					stmt2.setString(2,month_year[1]);
				}
				ResultSet rs=stmt2.executeQuery();
				while(rs.next())
				{
					Movie movie=new Movie();
					movie.id=rs.getInt(1);
					movie.name=rs.getString(2);
					movie.ratting=rs.getString(3);
					movie.genere=rs.getString(4);
					String[] mduration=rs.getString(5).split(":");
					movie.duration[0]=mduration[0];
					movie.duration[1]=mduration[1];
					movie.release_date=rs.getTimestamp(6);
					movie.screen_type=rs.getString(7);
					movie.language=rs.getString(8);
					data.add(movie);
					sucess=true;
	
				}		
				if(sucess)
				{
					this.report="Movie Search Sucessfull";	
				}
				 con.close();
			}
		}
		catch(Exception e)
		{
			this.report="Movie Search Failed Or No Results";
			System.out.println(e);
		}
			
		this.sucess=sucess;
		return data;
       
	}
	
	public boolean addMovie(Movie movie)
	{
		CustomMethods cm=new CustomMethods();
		Connection con=null;
		boolean sucess=false;
		
		if(movie.release_date.compareTo(cm.getSystemDateTime())<0)
		{
			this.report="Release Date Time Less Than Current Date Time";
			sucess=false;
		}
		else
		{
			try
			{
				cm=new CustomMethods();
				con=cm.connectDb();
				if(!cm.sucess)
					this.report=cm.report;
				else
				{
					String mduration=movie.duration[0]+":"+movie.duration[1];	
					String insertString="insert into MOVIE (M_NAME,RATTING,GENERE,M_DURATION,RELEASE_DATE,SCREEN_TYPE,LANGUAGE) values (?,?,?,?,?,?,?)";
					PreparedStatement insertStmt=con.prepareStatement(insertString); 
					insertStmt.setString(1,movie.name);
					insertStmt.setString(2,movie.ratting);
					insertStmt.setString(3,movie.genere);
					insertStmt.setString(4,mduration);
					insertStmt.setTimestamp(5,movie.release_date);
					insertStmt.setString(6,movie.screen_type);
					insertStmt.setString(7,movie.language);
					int insert=insertStmt.executeUpdate();
					if(insert>0)
					{
						this.report="Movie Sucessfully Added";
						sucess=true;
					}
					else
					{
						this.report="Add Movie Failed";
					}
					con.close();
				}
			}
			catch(Exception e)
			{
				this.report="Add Movie Failed";
				System.out.println(e);
			}
		}
		
		this.sucess=sucess;
		return sucess;
		
	}
	
	public boolean deleteMovie(int movie_id)
	{
		CustomMethods cm=new CustomMethods();
		Connection con=null;
		boolean sucess=false;
		try{
			con=cm.connectDb();
			if(!cm.sucess)
			{
				this.report=cm.report;
			}
			else
			{
				String delete_string="delete from MOVIE where MOVIE_ID=?";
				PreparedStatement delete_stmt=con.prepareStatement(delete_string);  
				delete_stmt.setInt(1,movie_id);
				int delete=delete_stmt.executeUpdate(); 
				if(delete>0)
				{
					this.report="Movie Deletion Sucessfull";
					sucess=true;
				}
				else
					this.report="Movie Deletion Failed";
				
				con.close();
			}
		}
		catch(Exception e)
		{ 
			this.report="Movie Deletion Failed";
			System.out.println(e);
		}
		
		this.sucess=sucess;
		return sucess;
	}
	
	public boolean updateMovie(Movie movie)
	{
		CustomMethods cm=new CustomMethods();
		Connection con=null;
		boolean sucess=false;
		if(movie.release_date.compareTo(cm.getSystemDateTime())<0)
		{
			this.report="Release Date Time Less Than Current Date Time";
			sucess=false;
		}
		else
		{
			try{
				con=cm.connectDb();
				if(!cm.sucess)
					this.report=cm.report;
				else
				{
					String update_string="update MOVIE set M_NAME=?,RATTING=?,GENERE=?,M_DURATION=?,RELEASE_DATE=?,SCREEN_TYPE=?,LANGUAGE=? where MOVIE_ID=?";
					PreparedStatement update_stmt=con.prepareStatement(update_string);  
					update_stmt.setString(1,movie.name);
					update_stmt.setString(2,movie.ratting);
					update_stmt.setString(3,movie.genere);
			        String mduration=movie.duration[0]+":"+movie.duration[1];
			        update_stmt.setString(4,mduration);
			        update_stmt.setTimestamp(5,movie.release_date);
			        update_stmt.setString(6,movie.screen_type);
			        update_stmt.setString(7,movie.language);
			        update_stmt.setInt(8,movie.id);
					int update=update_stmt.executeUpdate(); 
					if(update>0)
					{
						this.report="Movie Sucessfully Updated";
						sucess=true;
					}
					else
						this.report="Movie Updation Failed";
					con.close();
				}
			}
			catch(Exception e)
			{ 
				this.report="Movie Updation Failed";
				System.out.println(e);
			}
		}
		
		this.sucess=sucess;
		return sucess;
	}
	

	
	
}
