package MyLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class upload_oracle {
	String oraUrl="jdbc:oracle:thin:@202.114.18.76:1521:myoracle";		
    String oraUser="team";  
    String oraPwd="team"; 
     String  sql="";
    public upload_oracle(){
    	
    }
	public void upload(String filename) {
		// TODO Auto-generated method stub
		// TODO Auto-generated constructor stub
	      Connection conn=null; 
	      PreparedStatement stmt=null;
	      String name=filename.substring(0,filename.indexOf("."));
		   sql="update users_kwb SET imgurl='"+filename+"' where username='"+name+"'";		
		 
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
	new upload_oracle().upload("");
}
}
