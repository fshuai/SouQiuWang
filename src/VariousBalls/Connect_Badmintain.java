package VariousBalls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Connect_Badmintain {
	 public static List<videoMessage> getInfo(){
   	  String oraUrl="jdbc:oracle:thin:@202.114.18.77:1521:myoracle";		
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
   	List<videoMessage>message=new ArrayList<videoMessage>();
		  try  
	        {   Class.forName("oracle.jdbc.driver.OracleDriver");
	            conn=DriverManager.getConnection(oraUrl,oraUser,oraPwd);  
	            // stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);  
	            stmt=conn.createStatement();
	            rs=stmt.executeQuery("select * from BADMINTAIN_VIDEO t order by videodatecome desc");                   
	           
	  while(rs.next()&&i<=4){
	        
		     
		  imageUrl=rs.getString("videoaddr");//图片地址
	         videoUrl=rs.getString("videofile");//视频地址
	         videoTitle=rs.getString("videoname");//视频标题
	         videoID=rs.getString("videoid");//精彩镜头
	         videoorigin=rs.getString("videoorigin");
	           
	         message.add(new videoMessage(videoID,videoTitle,videoUrl,imageUrl,videoorigin));
		     i++;
		  }	
	   
	    conn.close();
	        }catch(Exception e){
	        	try{
		        	 i--;
		        	 rs.next();	
		        	 imageUrl=rs.getString("videoaddr");//图片地址
			         videoUrl=rs.getString("videofile");//视频地址
			         videoTitle=rs.getString("videoname");//视频标题
			         videoID=rs.getString("videoid");//精彩镜头
			         videoorigin=rs.getString("videoorigin");
			           
			         message.add(new videoMessage(videoID,videoTitle,videoUrl,imageUrl,videoorigin));
		        	}catch(Exception e1){
		        		e1.printStackTrace();
		        	}  
	        }
		return message;
		

		
	}
public static void main(String[] args) {
	// TODO Auto-generated method stub
	String[]str=null;
	Connect_Badmintain j=new Connect_Badmintain();
	List<videoMessage>l=j.getInfo();
	str=new String[l.size()];
	for(int i=0;i<l.size();i++){
	
		//str[i]=l.get(i).getImageUrl_Title()+"\t"+l.get(i).getVideoURL()+"\t"+l.get(i).getVideoID();
	 System.out.println(str[i]);
	}
}
   
}