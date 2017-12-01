package HomePage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import sun.tools.jar.resources.jar;

public class Connect_football {

 String baseAddress="http://202.114.18.70";
 JSONArray ja=new JSONArray(); 
	    public  JSONArray getInfo(){
	    	String oraUrl="jdbc:oracle:thin:@202.114.18.76:1521:myoracle";		
	   	    String oraUser="team";  
	   	    String oraPwd="team";  
	   	    ResultSet rs=null; 
	   		Connection conn=null;  
	   		Statement stmt=null;
	   	    String imageUrl="";
		    String videoUrl="";
		    String videoTitle="";
		    String videoID=""; 
		    String videoorigin="";
	   	     int i=1;  
			  try  
		        {   Class.forName("oracle.jdbc.driver.OracleDriver");
		            conn=DriverManager.getConnection(oraUrl,oraUser,oraPwd);  	    
		            stmt=conn.createStatement();
		            rs=stmt.executeQuery("select * from VIDEO t order by videodatecome desc");                   
		           
		  while(rs.next()&&i<=4){
			  JSONObject jo=new JSONObject();
			     
			    imageUrl=rs.getString("videoaddr");//图片地址
			    imageUrl=imageUrl.replace("\\", "/");//即把/Images//Image/Video_6461/frame45.jpg换成
			    
			     imageUrl=baseAddress+imageUrl; 
		         videoUrl=rs.getString("videofile");//视频地址
		         videoTitle=rs.getString("videoname");//视频标题
		         videoID=rs.getString("videoid");//精彩镜头
		         videoorigin=rs.getString("videoorigin");
		         jo.put("imageurl", imageUrl);
		         jo.put("videourl", videoUrl);
		         jo.put("videotitle", videoTitle);
		         jo.put("videoorigin", videoorigin);
		         jo.put("videoid", videoID);
		         ja.put(jo);
			     i++;
			  }	
		   
		    conn.close();
		        }catch(Exception e){
		        	try {
	        			  i--;
						  rs.next();						
						  imageUrl=rs.getString("videoaddr");//图片地址
						  imageUrl=imageUrl.replace("\\", "/");//即把/Images//Image/Video_6461/frame45.jpg换成
						    
						     imageUrl=baseAddress+imageUrl; 
					         videoUrl=rs.getString("videofile");//视频地址
					         videoTitle=rs.getString("videoname");//视频标题
					         videoID=rs.getString("videoid");//精彩镜头
					         videoorigin=rs.getString("videoorigin");
					         JSONObject jo=new JSONObject();
					         jo.put("imageurl", imageUrl);
					         jo.put("videourl", videoUrl);
					         jo.put("videotitle", videoTitle);
					         jo.put("videoorigin", videoorigin);
					         jo.put("videoid", videoID);
					         ja.put(jo);				        
				    	} catch (Exception e1) {
						e1.printStackTrace();
					}  		
		        }
			    return ja;		
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	}
	    
}
