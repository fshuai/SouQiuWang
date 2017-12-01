package HomePage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

public class Connect_Volleyball {
	 JSONArray ja=new JSONArray(); 
	 String baseAddress="http://202.114.18.70";
	 String oraUrl="jdbc:oracle:thin:@202.114.18.96:1521:orcl";		
	 String oraUser="volleyball";  
	 String oraPwd="adminvolleyball"; 
	    public  JSONArray getInfo(){
	  	
	 	     
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
		            rs=stmt.executeQuery("select * from VOLLEY_VIDEO t order by videodatecome desc");                   	            
		  while(rs.next()&&i<=4){	        		
			  imageUrl=rs.getString("videoaddr");//ͼƬ��ַ
			  imageUrl=imageUrl.replace("Images", "Volleyball_Images" );
 	            imageUrl=imageUrl.replace("\\", "/");//����/Images//Image/Video_6461/frame45.jpg����
	           imageUrl=baseAddress+imageUrl; 
		             //ע����ڸ������ſ���Ƶ����ҳ����Ҫ����ת���ɶ�Ӧ����Ƶ���ŵ�ַ      
		         videoUrl=rs.getString("videofile");//��Ƶ��ַ
		        
		         videoTitle=rs.getString("videoname");//��Ƶ����
		         videoID=rs.getString("videoid");//���ʾ�ͷ
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
		       //��������쳣���Ͳ��������ú����ȥ����ǰ��ľ�ok��
		        		try {
		        			 i--;
		        			 imageUrl=rs.getString("videoaddr");//ͼƬ��ַ
		        			 imageUrl=imageUrl.replace("Images", "Volleyball_Images" );
		        	            imageUrl=imageUrl.replace("\\", "/");//����/Images//Image/Video_6461/frame45.jpg����                     
		                      imageUrl=baseAddress+imageUrl;  //ע����ڸ������ſ���Ƶ����ҳ����Ҫ����ת���ɶ�Ӧ����Ƶ���ŵ�ַ      
		    		         videoUrl=rs.getString("videofile");//��Ƶ��ַ
		    		         videoTitle=rs.getString("videoname");//��Ƶ����
		    		         videoID=rs.getString("videoid");//���ʾ�ͷ
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
	    public static void main(String[]args){
	    	System.out.println(new Connect_Volleyball().getInfo());
	    }
	    
	}
