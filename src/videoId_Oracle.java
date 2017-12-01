import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class videoId_Oracle {
	private int videoID; 
	String baseAddress="http://202.114.18.70";
	 String oraUrl="jdbc:oracle:thin:@202.114.18.96:1521:orcl";		
	 String oraUser="volleyball";  
	 String oraPWD="adminvolleyball";  
	 ResultSet rs=null; 
	 Connection conn=null;  
	 Statement stmt=null;
	 JSONArray array=new JSONArray();
	  //���ڲ���
	  public static void main(String args[]){
			int videoId=6697;
		  videoId_Oracle t=new videoId_Oracle(videoId);
		  JSONArray list2=t.getInfo();
		  for(int i=0;i<list2.length();i++)
			try {
				//System.out.println(list2.length());
				System.out.println(list2.getJSONObject(i));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	  }
    public videoId_Oracle(int videoID){
    	this.videoID=videoID;
    }
    public JSONArray getInfo(){
    	int startframe,endframe;
    	float framerate;
    	int starttime,totaltime;
     try{  
    	 Class.forName("oracle.jdbc.driver.OracleDriver");
	     conn=DriverManager.getConnection(oraUrl,oraUser,oraPWD);  
	     stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);  
	     rs=stmt.executeQuery("select * from VOLLEY_VIDEO_HIGHLIGHT where videoid="+videoID);
	     //rs=stmt.executeQuery("select * from VOLLEY_VIDEO_HIGHLIGHT where videoid between '"+5560+"' and '"+5600+"'");
	     while(rs.next()){
	    	 JSONObject jo=new JSONObject();
	    	 startframe=rs.getInt("startframe");
	    	 endframe=rs.getInt("endframe");
	    	 framerate=rs.getFloat("framerate");
	    	 starttime=(int)(startframe/framerate);
	    	 totaltime=(int)((endframe-startframe)/framerate);
	    	 String imageUrl=rs.getString("picaddress");//ͼƬ��ַ�������ͼƬ������Linuxϵͳ�£���http://202.114.18.70
 	        //�ʽ�ͼƬUrl����Linux�µ�Url
     	    imageUrl=imageUrl.replace("Images", "Volleyball_Images" );
            imageUrl=imageUrl.replace("\\", "/");//����/Images//Image/Video_6461/frame45.jpg����
             //http://202.114.18.70/Volleyball_Images//Image/Video_6461/frame45.jpg
             imageUrl=baseAddress+imageUrl; 
	    	 jo.put("starttime", starttime);
             jo.put("totaltime", totaltime);
	    	jo.put("picaddress",imageUrl );
	    	array.put(jo);
	      
	     } 
	     rs.close();
	     conn.close();
	        }catch(Exception e){
	      
	        	e.printStackTrace();
	        	
	        }
    	
    	return array;
    }
}
