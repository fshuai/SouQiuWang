import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class videoPriview_oracle {
//select * from VOLLEY_E2LSH_SHOT t where videoid=6697;
	int videoID;
	String baseAddress="http://202.114.18.70";
	 String oraUrl="jdbc:oracle:thin:@202.114.18.96:1521:orcl";		
	 String oraUser="volleyball";  
	 String oraPWD="adminvolleyball";  
	 ResultSet rs=null; 
	 Connection conn=null;  
	 Statement stmt=null;
	
	 public static void main(String args[]){
			int videoId=6699;
			videoPriview_oracle  t=new videoPriview_oracle (videoId);
			 JSONArray list2=t.getPreviewInfo();
			  for(int i=0;i<list2.length();i++)
				try {
					//System.out.println(list2.length());
					System.out.println(list2.getJSONObject(i));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	  }
public videoPriview_oracle(int videoID ){
	this.videoID=videoID;
	
}
public JSONArray getPreviewInfo(){
	// TODO Auto-generated method stub
	float starttime;
	float duration;
	String framefile;
	JSONArray array=new JSONArray();
 try{  
	 Class.forName("oracle.jdbc.driver.OracleDriver");
     conn=DriverManager.getConnection(oraUrl,oraUser,oraPWD);  
     stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);  
     rs=stmt.executeQuery("select t3.framerate,t1.framefile,t2.startframe,t2.duration from VOLLEY_E2LSH_KEYFRAME t1,VOLLEY_E2LSH_SHOT t2,volley_VIDEO t3 where t2.videoid=t3.videoid and t2.shotid=t1.shotid and t3.videoid="+videoID);
    int i=0;
   
     while(rs.next()){
    	 i++;
    	 JSONObject jo=new JSONObject();
    	 starttime=rs.getInt("startframe")/rs.getFloat("framerate");
    	 duration=rs.getFloat("duration");   
    	 String imageUrl=rs.getString("framefile");//图片地址，这里的图片都存在Linux系统下，即http://202.114.18.70
	        //故将图片Url换成Linux下的Url
 	    imageUrl=imageUrl.replace("Images", "Volleyball_Images" );
        imageUrl=imageUrl.replace("\\", "/");//即把/Images//Image/Video_6461/frame45.jpg换成
         //http://202.114.18.70/Volleyball_Images//Image/Video_6461/frame45.jpg
         imageUrl=baseAddress+imageUrl; 
    	 jo.put("BegTime", starttime);
         jo.put("duration", duration);
    	 jo.put("framefile",imageUrl );
    	array.put(jo);
      
     } 
     rs.close();
     conn.close();
    System.out.println("leng:"+i);
        }catch(Exception e){
      
        	e.printStackTrace();
        	
        }
 
	
	
	return array;

	
}
}
