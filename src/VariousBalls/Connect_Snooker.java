package VariousBalls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Connect_Snooker {
	JSONArray ja=new JSONArray(); 
	 String baseAddress="http://202.114.18.70";
	    public  JSONArray getInfo(){
	  	  String oraUrl="jdbc:oracle:thin:@202.114.18.96:1521:orcl";		
	 	    String oraUser="snooker";  
	 	     String oraPwd="snooker";  
	 	   ResultSet rs=null; 
	 	   int i=1;
	 		 Connection conn=null;  
	 		 Statement stmt=null;
	 		  String imageUrl="";
			    String videoUrl="";
			    String videoTitle="";
			    String videoID=""; 
			    String videoorigin="";
	  	
			  try  
		        {   Class.forName("oracle.jdbc.driver.OracleDriver");
		            conn=DriverManager.getConnection(oraUrl,oraUser,oraPwd);  	        
		            stmt=conn.createStatement();
		            rs=stmt.executeQuery("select * from SNOOKER_VIDEO t order by videodatecome desc");                   	            
		  while(rs.next()&&i<=4){	        		
			  imageUrl=rs.getString("videoaddr");//图片地址
			  imageUrl=imageUrl.replace("Images", "Snooker_Images" );
	          imageUrl=imageUrl.replace("\\", "/");//即把/Images//Image/Video_6461/frame45.jpg换成
	           imageUrl=baseAddress+imageUrl; 
		             //注意对于给定的优酷视频的网页，需要将其转换成对应的视频播放地址      
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
		       
			     i++;
			  }	
		      conn.close();
		        }catch(Exception e){
		       //如果出现异常，就捕获它，用后面的去代替前面的就ok了
		        		try {
		        			 i--;
		        			 imageUrl=rs.getString("videoaddr");//图片地址
		        			 imageUrl=imageUrl.replace("Images", "Snooker_Images" );
		                     imageUrl=imageUrl.replace("\\", "/");//即把/Images//Image/Video_6461/frame45.jpg换成	                     
		                      imageUrl=baseAddress+imageUrl;  //注意对于给定的优酷视频的网页，需要将其转换成对应的视频播放地址      
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
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}  		
			
		        }
			return ja;

		}
}
