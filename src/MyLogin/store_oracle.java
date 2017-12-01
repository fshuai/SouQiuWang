package MyLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;


public class store_oracle {
	 String oraUrl="jdbc:oracle:thin:@202.114.18.76:1521:myoracle";		
	    String oraUser="team";  
	    String oraPwd="team"; 
     
    public  store_oracle(String name,String videoid,String videotag,String content,String videotitle){
    	Connection conn=null; 
  	  PreparedStatement stmt=null;
  	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制
  	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
  		Date date=new Date();
  		String regtime=sdf.format(date);
  	 String  sql="insert into comments_kwb(name,videoid,videotag,content,com_time,videotitle)values ('"
  		+name+"','"+videoid+"',"+videotag+",'"+content+"','"+regtime+"','"+videotitle+"')";
  	 try  
          {   Class.forName("oracle.jdbc.driver.OracleDriver");
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
    	
 	new store_oracle("kwb","6893","1","这其实还是不错","中国热");
 	new store_oracle("kwb","6893","0","这其实还是不错","中国热");
 	new store_oracle("kwb","6893","7","这其实还是不错","中国热");
    } 	
}
