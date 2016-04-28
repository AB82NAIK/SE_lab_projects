import java.sql.Connection;

public class LogIn extends SignUp{
	Connection con=null;
	String user_name;
	String password;
	boolean sucess=false;
	String report;
	boolean admin;
	
	public LogIn(String user_name,String password)
	{
		SignUp sign_up=getUser(user_name);
		
		
		if(sign_up.user_name!=null && sign_up.password!=null)
		{
			if(sign_up.password.equals(password))
			{
				this.report="Welcome "+sign_up.user_name;
				this.sucess=true;
				this.admin=sign_up.admin;
			}
			
			else
			{
				this.report="Entered Wrong User Name Or Password";
				this.sucess=false;
			}
		}
		else
		{
			this.report="User Name Or Password Empty";
			this.sucess=false;
		}
		
	}

}
