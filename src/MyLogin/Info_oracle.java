package MyLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

public class Info_oracle {
	 String oraUrl="jdbc:oracle:thin:@202.114.18.76:1521:myoracle";		
	    String oraUser="team";  
	    String oraPwd="team";  
    String name;
	String sex="";
	String birthtime="";
	String nickname="";
	Connection conn=null; 
	Statement stmt=null;
	ResultSet rs=null;
	JSONObject jo;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
      JSONObject j= new Info_oracle("wx").getInfo();
      System.out.println(j.toString());
	}
	  
     
public Info_oracle(String name){
  this.name=name;
  jo=new JSONObject();
 
  
}
public JSONObject getInfo() {
	// TODO Auto-generated method stub
	 String  sql="select * from users_kwb where username='"+name+"'"; 
	 try  
	    {   Class.forName("oracle.jdbc.driver.OracleDriver");
	        conn=DriverManager.getConnection(oraUrl,oraUser,oraPwd);    
	        stmt=conn.createStatement();
	        rs=stmt.executeQuery(sql);
	        while(rs.next()){
	        	sex=rs.getString("gender");
	        	birthtime=rs.getString("birthtime");
	        	nickname=rs.getString("nickname");
	        	if(nickname==null)
	        		nickname="";
	        	if(birthtime==null)
	        		birthtime="";
	        	jo.put("sex", sex);
	        	jo.put("nickname", nickname);
	        	jo.put("birthtime", birthtime);
	        }
	        rs.close(); 
	        conn.close();   

	}catch (Exception e) {
	//TODO: handle exception
	e.printStackTrace();
	}
	return jo;
}
}
