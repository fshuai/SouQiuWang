package MyLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import VariousBalls.videoMessage;

public class login_oracle {
    String status="";//Ĭ�ϵ�½�ɹ�
    String oraUrl="jdbc:oracle:thin:@202.114.18.76:1521:myoracle";		
	    String oraUser="team";  
	    String oraPwd="team";  
	public login_oracle(String name, String password) {
		// TODO Auto-generated constructor stub
		Connection conn=null; 
		Statement stmt=null;  
		  try  
	        {   Class.forName("oracle.jdbc.driver.OracleDriver");
	            conn=DriverManager.getConnection(oraUrl,oraUser,oraPwd);  
	            // stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);  
	            stmt=conn.createStatement();
	          ResultSet  rs=stmt.executeQuery("select * from users_kwb where username='"+name+"'");
	          if(rs.next()){
	             String Password=rs.getString("password");
	        	     if(password.equals(Password))
	        		   status="1";//��½�ɹ�
	        	     else 
	        		   status="2";//�������
	          }
	          else
	        	  status="3";//û�в鵽��¼��Ϊnull
	        	setStatus(status);
	        	conn.close();
	        }catch(Exception e){
	        	status="3";//δע��
	        }
	        

}
	public void setStatus(String status) {
		// TODO Auto-generated method stub
		this.status=status;
	}
	public String getStatus(){
		return this.status;
	}
}