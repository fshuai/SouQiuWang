package VariousBalls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Connect_Tennis {
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
		            stmt=conn.createStatement();
		            rs=stmt.executeQuery("select * from Tennis_VIDEO t order by videodatecome desc");                   
		           
		  while(rs.next()&&i<=4){
		        
			     i++;
			     imageUrl=rs.getString("videoaddr");//ͼƬ��ַ
			    
		         videoUrl=rs.getString("videofile");//��Ƶ��ַ
		         videoTitle=rs.getString("videoname");//��Ƶ����
		         videoID=rs.getString("videoid");//���ʾ�ͷ
		         videoorigin=rs.getString("videoorigin");
		           
		         message.add(new videoMessage(videoID,videoTitle,videoUrl,imageUrl,videoorigin));
			  }	
		   
		    conn.close();
		        }catch(Exception e){
		        	try{
			        	 i--;
			        	 rs.next();	
			        	  imageUrl=rs.getString("videoaddr");//ͼƬ��ַ			  
					         videoUrl=rs.getString("videofile");//��Ƶ��ַ
					         videoTitle=rs.getString("videoname");//��Ƶ����
					         videoID=rs.getString("videoid");//���ʾ�ͷ
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
		Connect_Tennis j=new Connect_Tennis();
		List<videoMessage>l=j.getInfo();
		str=new String[l.size()];
		for(int i=0;i<l.size();i++){
		
			str[i]=l.get(i).getImageURl()+"\t"+l.get(i).getVideoTitle()+"\t"+l.get(i).getVideoURL()+"\t"+l.get(i).getVideoOrigin();
			 System.out.println(str[i]);
			
		}
	}
	    
}

