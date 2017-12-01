package BasketBall;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import VariousBalls.Badmintain_Tape_Preview_Oracle;

public class Basket_Tape_Preview_Oracle {
	/********************篮球的精彩镜头与视频预览*********************/
	int videoID;
	JSONObject JTape_Preview=new JSONObject();
	 String baseAddress="http://202.114.18.70";//篮球的基地址发生变化
	 String oraUrl="jdbc:oracle:thin:@202.114.18.77:1521:myoracle";		
	 String oraUser="team";  
	 String oraPWD="team";  
	 ResultSet rs=null; 
	 Connection conn=null;  
	 Statement stmt=null;
 public Basket_Tape_Preview_Oracle(int videoID){
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
     conn=DriverManager.getConnection(oraUrl,oraUser,oraPWD);  
     stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);  
     rs=stmt.executeQuery("select * from Basket_VIDEO_HIGHLIGHT where videoid="+videoID);
     //rs=stmt.executeQuery("select * from VOLLEY_VIDEO_HIGHLIGHT where videoid between '"+5560+"' and '"+5600+"'");
     while(rs.next()){
    	 JSONObject jo=new JSONObject();
    	 startframe=rs.getInt("startframe");
    	 endframe=rs.getInt("endframe");
    	 framerate=rs.getFloat("framerate");
    	 starttime=(int)(startframe/framerate);
    	 totaltime=(int)((endframe-startframe)/framerate);
    	 String imageUrl=rs.getString("picaddress");//图片地址，这里的图片都存在Linux系统下，即http://202.114.18.70
	        //故将图片Url换成Linux下的Url
    	 imageUrl=getRealImageUrl(imageUrl);
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
     conn=DriverManager.getConnection(oraUrl,oraUser,oraPWD);  
     stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);  
     rs=stmt.executeQuery("select t3.framerate,t1.framefile,t2.startframe,t2.duration from Basket_E2LSH_KEYFRAME t1,Basket_E2LSH_SHOT t2,Basket_VIDEO t3 where t2.videoid=t3.videoid and t2.shotid=t1.shotid and t3.videoid="+videoID);
    //int i=0;
   
     while(rs.next()){
    	// i++;
    	 JSONObject jo=new JSONObject();
    	 starttime=rs.getInt("startframe")/rs.getFloat("framerate");
    	 duration=rs.getFloat("duration");   
    	 String imageUrl=rs.getString("framefile");//图片地址，这里的图片都存在Linux系统下，即http://202.114.18.70
	        //故将图片Url换成Linux下的Url
    	imageUrl=getRealImageUrl(imageUrl);
      
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
 public String getRealImageUrl(String originimageurl){
	 int start_index,end_index;
     String videioid;//视频id
     String filename;//帧名
     String thousand = "0";
     String million = "0";
     String billion = "0";
     if((start_index=originimageurl.indexOf("Video_"))!=-1){
         try
         {
             start_index += 6;
             end_index = originimageurl.indexOf("\\",start_index);
             videioid = originimageurl.substring(start_index, end_index);
             filename = originimageurl.substring(end_index + 1);
             //根据视频id确定文件的存放结构,即billion/million/thousand/filename
             int len = videioid.length();
             if (len > 3 && len <= 6)
             {
                 thousand = videioid.substring(0, len - 3);
             }
             else if (len > 6 && len <= 9)
             {
                 million = videioid.substring(0, len - 6);
                 thousand = videioid.substring(len - 6, 3);
             }
             else if (len > 9 && len <= 12)
             {
                 billion = videioid.substring(0, len - 9);
                 million = videioid.substring(len - 9, 3);
                 thousand = videioid.substring(len - 6, 3);
             }
             else
             {
                 //overflow 不可能到这一步,因为到这一步说明视频id已经到百亿了，有生之年是不能的
                 return originimageurl;
             }
         }
         catch (Exception e)
         {
             return originimageurl;
         }
         originimageurl=baseAddress+"/Basketball_Images/Image/"+billion+"/"+million+"/"+thousand+"/Video_"+videioid+"/"+ filename;

     }
     return originimageurl;
 }


//用于测试
public static void main(String args[]){
		int videoId=10000;
		Basket_Tape_Preview_Oracle v=new Basket_Tape_Preview_Oracle(videoId);
	  JSONObject list2=v.getInfo();
		try {
			System.out.println(list2.length());
			JSONArray a=list2.getJSONArray("videoPreview");
			for(int i=0;i<a.length();i++){
				System.out.println(a.get(i));
			}
			System.out.println("videoTape=>"+list2.getJSONArray("videoTape"));
			System.out.println("videoPreview=>"+list2.getJSONArray("videoPreview"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}

}
