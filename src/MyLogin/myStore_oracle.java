package MyLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class myStore_oracle {
	//fss修改，进行用户保存数据库操作
	//插入操作总是出错，找出原因
	 String oraUrl="jdbc:oracle:thin:@202.114.18.76:1521:myoracle";		
	    String oraUser="team";  
	    String oraPwd="team"; 
	private String id;
	private String err="correct";
	
	public myStore_oracle(String name,String videoid,String videotag,String videotitle,String imageurl,String videourl){
		id=videoid;//测试
		Connection conn=null; 
	  	PreparedStatement stmt=null;
	  	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制
	  	Date date=new Date();
	  	String regtime=sdf.format(date);
	  	//String sql=insert into ind_store (user_name,video_tag,video_url,video_title,play_time,image_url,video_id) values
	  			
	  	try  
        {   Class.forName("oracle.jdbc.driver.OracleDriver");
            conn=DriverManager.getConnection(oraUrl,oraUser,oraPwd);    
           stmt=conn.prepareStatement
            		("insert into ind_store (user_name,video_tag,video_url,video_title,play_time,image_url,video_id) values(?,?,?,?,?,?,?)");
            stmt.setString(1, name);
            stmt.setString(2, videotag);
            stmt.setString(3, videourl);
            stmt.setString(4, videotitle);
            stmt.setString(5, regtime);
            stmt.setString(6, imageurl);
            stmt.setString(7, videoid);
            stmt.executeUpdate();
            stmt.close();
            conn.close();   
 
       }catch (Exception e) {
    	   // TODO: handle exception
    	   e.printStackTrace();
    	   err="an error occurred";
       }	

	}
	
	public String geterr(){
		return err;
	}
	
	public String getid(){
		return id;
	}
	
}
