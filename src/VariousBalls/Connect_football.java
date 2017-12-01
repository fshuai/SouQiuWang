package VariousBalls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Connect_football {
	    JSONArray ja=null;
	    String baseAddress="http://202.114.18.70";
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
	    	ja=new JSONArray();
			  try  
		        {   Class.forName("oracle.jdbc.driver.OracleDriver");
		            conn=DriverManager.getConnection(oraUrl,oraUser,oraPwd);  
		            // stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);  
		            stmt=conn.createStatement();
		            rs=stmt.executeQuery("select * from VIDEO t order by videodatecome desc");                   
		           
		  while(rs.next()&&i<=4){
		       JSONObject jo=new JSONObject(); 
			     
			    imageUrl=rs.getString("videoaddr");//图片地址
			    imageUrl=imageUrl.replace("\\", "/");//即把/Images//Image/Video_6461/frame45.jpg换成
	             //http://202.114.18.70/Volleyball_Images//Image/Video_6461/frame45.jpg
	            imageUrl=baseAddress+imageUrl; 
		        videoUrl=rs.getString("videofile");//视频地址
		        videoTitle=rs.getString("videoname");//视频标题
		        videoID=rs.getString("videoid");//精彩镜头
		        videoorigin=rs.getString("videoorigin");
		        jo.put("imageurl", imageUrl);
		         jo.put("videourl", videoUrl);
		        jo.put("videotitle", videoTitle);
		        jo.put("videoid", videoID);
		        jo.put("videoorigin", videoorigin);			 
			    ja.put(jo);
			     i++;
			  }	
		   
		    conn.close();
		        }catch(Exception e){
		        	try {
	        			 i--;
						 rs.next();	
						 JSONObject jo=new JSONObject(); 
						  imageUrl=rs.getString("videoaddr");//图片地址
						  imageUrl=imageUrl.replace("\\", "/");//即把/Images//Image/Video_6461/frame45.jpg换成
				             //http://202.114.18.70/Volleyball_Images//Image/Video_6461/frame45.jpg
				          imageUrl=baseAddress+imageUrl; 
					      videoUrl=rs.getString("videofile");//视频地址
					      videoTitle=rs.getString("videoname");//视频标题
					      videoID=rs.getString("videoid");//精彩镜头
					      videoorigin=rs.getString("videoorigin");
					           
					      jo.put("imageurl", imageUrl);
					      jo.put("videotitle", videoTitle);
					      jo.put("videoid", videoID);
					      jo.put("videourl", videoUrl);
					      jo.put("videoorigin", videoorigin);
					      ja.put(jo);
				    	} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  		
		        }
			return ja;
			

			
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connect_football j=new Connect_football();
		System.out.println(j.getInfo());
	}
	    
}
