package MyLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditUser {
	 String oraUrl="jdbc:oracle:thin:@202.114.18.76:1521:myoracle";		
	    String oraUser="team";  
	    String oraPwd="team"; 
     String  sql="";
	public EditUser(String name, String newname, int sex, String birthtime) {
		// TODO Auto-generated constructor stub
		    Connection conn=null; 
		    PreparedStatement stmt=null;
		    PreparedStatement stmt1=null;						
            sql="update users_kwb SET nickname='"+newname+"',gender='"+sex+"', birthtime='"+birthtime+"'where username='"+name+"'";
			  	 
		
               try  
	        {   
            	Class.forName("oracle.jdbc.driver.OracleDriver");
	            conn=DriverManager.getConnection(oraUrl,oraUser,oraPwd);    
	            stmt=conn.prepareStatement(sql);	         
	            stmt.executeUpdate();
	          
	            stmt.close();
	            conn.close();   
	 
	}catch (Exception e) {
	// TODO: handle exception
	 e.printStackTrace();
	}
		  
	  }
 public static void main(String[]args){
	 EditUser e=new EditUser("wx", "wx", 2, "1995");
 }

	}


