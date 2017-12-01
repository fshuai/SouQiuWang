package Leaque;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Club {
	 static String baseAddress="http://202.114.18.70";
	 static int len=10;//每次加载10个
	 static String oraUrl="jdbc:oracle:thin:@202.114.18.76:1521:myoracle";		
     static String oraUser="team";  
	 static String oraPwd="team";  
	  public  static JSONObject getClubInfo(int clubid,int start){
		    ResultSet rs=null; 
		    ResultSet rs1=null;
			Connection conn=null;  
		    Statement stmt=null;
		    String imageUrl="";
		    String videoUrl="";
		    String videoTitle="";
		    String videodateCome="";
		    String videoorigin="";
		    int totalduration=0;
		    int clicknum=0;
		    int total = 0;
		    String videoID=""; 
		    JSONArray array=new JSONArray();
		    JSONObject jobject=new JSONObject();
		    JSONObject j1=new JSONObject();	
		    try  
		       {   Class.forName("oracle.jdbc.driver.OracleDriver");
		           conn=DriverManager.getConnection(oraUrl,oraUser,oraPwd);  
		          stmt=conn.createStatement();		        
		          rs1=stmt.executeQuery("select count(*) from(select rownum no,TempTable.* from(select video.videoid,videoaddr,videoname,videodatecome,totalduration,to_char(VIDEODATEUPLOAD,'YYYY-MM-DD') publishTime,CLICKNUM,videoorigin from video,VIDEO_DYNAMIC where video.videoid in (select videoid from team_video where  teamid="+clubid+") and video.videoid=video_dynamic.videoid order by videodatecome desc) TempTable)"); 		        	        	            
		         while(rs1.next())
		           total=Integer.valueOf( rs1.getObject(1).toString());//用于统计个数		          
		             j1.put("total", total);		           
		          rs=stmt.executeQuery("select * from(select rownum no,TempTable.* from(select video.videoid,videoaddr,videofile,videoname,videodatecome,totalduration,to_char(VIDEODATEUPLOAD,'YYYY-MM-DD') publishTime,CLICKNUM,videoorigin from video,VIDEO_DYNAMIC where video.videoid in (select videoid from team_video where  teamid=" + clubid + ") and video.videoid=video_dynamic.videoid order by videodatecome desc) TempTable)where no>=" + start + " and no<=" + start+len); 		              		          
		          int i=1;  
		    while(rs.next()&&i<=len){
			    JSONObject jo=new JSONObject();
		        imageUrl=rs.getString("videoaddr");//图片地址
		        imageUrl=imageUrl.replace("\\", "/");
		        imageUrl=baseAddress+imageUrl; 
                videoUrl=rs.getString("videofile");//视频地址
		        videoTitle=rs.getString("videoname");//视频标题
		        videoID=rs.getString("videoid");//精彩视频
		        clicknum=rs.getInt("clickNum");
		        totalduration =rs.getInt("totalduration")/1000;//传入的是秒
		        videodateCome=rs.getString("videodatecome");
		        videodateCome=videodateCome.substring(0,videodateCome.indexOf(" "));//除去后面的时-分-秒
		        if(videoTitle.contains("—在线播放"))
		        videoTitle=videoTitle.substring(0, videoTitle.indexOf("—在线播放"));    
		        videoorigin=rs.getString("videoorigin");
		         jo.put("imageUrl", imageUrl);
		         jo.put("videoUrl", videoUrl);
		         jo.put("videoTitle", videoTitle);
		         jo.put("videoID", videoID);
		         jo.put("clicknum", clicknum);
		         jo.put("totalduration", totalduration);
		         jo.put("videodateCome", videodateCome);
		         jo.put("videoorigin", videoorigin);
		         array.put(jo);
			     i++;
			  }			  
		        conn.close();
		       
		       }catch(Exception e){
		    	   
		       	e.printStackTrace();
		       
		       }
			  try {
				jobject.put("League", array);
				 jobject.put("total", j1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return jobject;
												  
	   }
	   
	  
		//测试所用,看输入格式
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			 Club t=new  Club  ();
			   JSONObject list2=t.getClubInfo(481,0);
			   JSONArray a = null;
			try {
				a = (JSONArray) list2.get("League");
				System.out.println(list2.get("total"));
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  for(int i=0;i<a.length();i++)
				 try {
					System.out.println(a.getJSONObject(i));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
}
