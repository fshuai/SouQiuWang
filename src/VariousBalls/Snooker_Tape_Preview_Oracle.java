package VariousBalls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Snooker_Tape_Preview_Oracle {
	/*********************台球的精彩镜头与视频预览*********************/
	int videoID;
	JSONObject JTape_Preview=new JSONObject();
	String baseAddress="http://202.114.18.70";
	String oraUrl="jdbc:oracle:thin:@202.114.18.96:1521:orcl";		
	String oraUser="snooker";  
	String oraPwd="snooker";  
	 ResultSet rs=null; 
	 Connection conn=null;  
	 Statement stmt=null;
public Snooker_Tape_Preview_Oracle(int videoID){
	this.videoID=videoID;
	JTape_Preview=new JSONObject();
}
public JSONObject getInfo(){
	try {
		JTape_Preview.put("videoTape", getTapeInfo());
		JTape_Preview.put("videoPreview", getPreviewInfo());
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return JTape_Preview;
}
public JSONArray getTapeInfo(){
	int startframe,endframe;
	float framerate;
	int starttime,totaltime;
	JSONArray array=new JSONArray();
 try{  
	 Class.forName("oracle.jdbc.driver.OracleDriver");
     conn=DriverManager.getConnection(oraUrl,oraUser,oraPwd);  
     stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);  
     rs=stmt.executeQuery("select * from SNOOKER_VIDEO_HIGHLIGHT where videoid="+videoID+"order by startframe asc");
     while(rs.next()){
    	 JSONObject jo=new JSONObject();
    	 startframe=rs.getInt("startframe");
    	 endframe=rs.getInt("endframe");
    	 framerate=rs.getFloat("framerate");
    	 starttime=(int)(startframe/framerate);
    	 totaltime=(int)((endframe-startframe)/framerate);
    	 String imageUrl=rs.getString("picaddress");//图片地址，这里的图片都存在Linux系统下，即http://202.114.18.70
	        //故将图片Url换成Linux下的Url
        imageUrl=imageUrl.replace("Images", "Snooker_Images" );
        imageUrl=imageUrl.replace("\\", "/");//即把/Images//Image/Video_6461/frame45.jpg换成
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
public JSONArray getPreviewInfo(){
	// TODO Auto-generated method stub
	float starttime;
	float duration;
	String framefile;
	JSONArray array1=new JSONArray();
 try{  
	 Class.forName("oracle.jdbc.driver.OracleDriver");
     conn=DriverManager.getConnection(oraUrl,oraUser,oraPwd);  
     stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);  
     rs=stmt.executeQuery("select t3.framerate,t1.framefile,t2.startframe,t2.duration from SNOOKER_E2LSH_KEYFRAME t1,SNOOKER_E2LSH_SHOT t2,SNOOKER_VIDEO t3 where t2.videoid=t3.videoid and t2.shotid=t1.shotid and t3.videoid="+videoID+"order by startframe asc");
    //int i=0;
   
     while(rs.next()){
    	// i++;
    	 JSONObject jo=new JSONObject();
    	 starttime=rs.getInt("startframe")/rs.getFloat("framerate");
    	 duration=rs.getFloat("duration");   
    	 String imageUrl=rs.getString("framefile");//图片地址，这里的图片都存在Linux系统下，即http://202.114.18.70
	        //故将图片Url换成Linux下的Url
          imageUrl=imageUrl.replace("Images", "Snooker_Images" );
          imageUrl=imageUrl.replace("\\", "/");
         imageUrl=baseAddress+imageUrl; 
    	 jo.put("BegTime", starttime);
         jo.put("duration", duration);
    	 jo.put("framefile",imageUrl );
    	array1.put(jo);
      
     } 
     rs.close();
     conn.close();
    //System.out.println("leng:"+i);
        }catch(Exception e){
      
        	e.printStackTrace();
        	
        }
	return array1;
	
}
//用于测试
public static void main(String args[]){
		int videoId=6446;
		Snooker_Tape_Preview_Oracle v=new Snooker_Tape_Preview_Oracle(videoId);
	  JSONObject list2=v.getInfo();
		try {
			System.out.println(list2.length());
			JSONArray a=list2.getJSONArray("videoPreview");
			for(int i=0;i<a.length();i++){
				System.out.println(a.get(i));
			}
			//System.out.println("videoTape=>"+list2.getJSONArray("videoTape"));
			//System.out.println("videoPreview=>"+list2.getJSONArray("videoPreview"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
}