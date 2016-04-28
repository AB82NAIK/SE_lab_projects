import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignUp {

	String user_name;
	String password;
	boolean user_exist=false;
	Connection con=null;
	String report;
	boolean sucess;
	int type_value;
	boolean admin;
	public boolean addUser(String user_name,String password,String confirm_pass,boolean admin)
	{
		CustomMethods cm=new CustomMethods();
		Connection con=null;
		boolean sucess=false;
		SignUp signup=new SignUp();
		signup=signup.getUser(user_name);
		int user_type=2;//local user;
		if(admin)
		{
			user_type=1;//admin user;
		}
			
		if(user_name.isEmpty()||password.isEmpty()||confirm_pass.isEmpty())
		{
			sucess=false;
			this.report="Empty Fields";
		}
		else if(!password.equals(confirm_pass))
		{
			sucess=false;
			this.report="Password Does Not Match";
		}
		else if(signup.user_exist)
		{
			sucess=false;
			this.report="User Already Exist";
		}
		else
		{
			try
			{
				con=cm.connectDb();
				if(!cm.sucess)
					this.report=cm.report;
				else
				{
					String insertString="insert into LOGIN (USER_NAME,USER_PASSWORD,TYPE) values (?,?,?)";
					PreparedStatement insertStmt=con.prepareStatement(insertString); 
					insertStmt.setString(1,user_name);
					insertStmt.setString(2,password);
					insertStmt.setInt(3, user_type);
					int insert=insertStmt.executeUpdate();
					if(insert>0)
					{
						this.report="User Sucessfully Created";
						sucess=true;
					}
					else
						this.report="User Creation Failed";
					con.close();
				}
				
			}
			catch(Exception e)
			{
				this.report="User Creation Failed";
				System.out.println(e);
			}
		}
		
		this.sucess=sucess;
		return sucess;
	}
	
	public SignUp getUser(String user_name)
	{
		CustomMethods cm=new CustomMethods();
		Connection con=null;
		
		SignUp sign_up=new SignUp();
		sign_up.user_name=null;
		sign_up.password=null;
		boolean sucess=false;
		try
		{
			con=cm.connectDb();
			if(!cm.sucess)
				this.report=cm.report;
			else
			{
				String searchString="select * from LOGIN where USER_NAME=?";
				PreparedStatement searchStmt=con.prepareStatement(searchString); 
				searchStmt.setString(1,user_name);
				ResultSet rs=searchStmt.executeQuery();
				while(rs.next())
				{
					sign_up.user_name=rs.getString(2);
					sign_up.password=rs.getString(3);
					sign_up.type_value=rs.getInt(4);
					sucess=true;
					sign_up.user_exist=true;
				}
				if(sucess)
				{
					this.report="Get User Sucessfull";
					if(sign_up.type_value==1)
					{
						sign_up.admin=true;
					}
					else
						sign_up.admin=false;
				}
				else
					this.report="Get User Failed";
				con.close();
			}
		}
		catch(Exception e)
		{
			this.report="getUser() failed or no result";
			System.out.println(e);
		}
		

		
		return sign_up;
	}
	

}
