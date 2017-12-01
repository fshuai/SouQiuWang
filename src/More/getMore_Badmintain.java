package More;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class getMore_Badmintain {
	static String baseAddress="http://202.114.18.77:8885";//羽毛球的基地址发生变化
	 static int len=8;//原来是固定住，指每次向其加载就是8个,如果是排球可以一次性读出来，在后面让len=total就可以了
		
		public static JSONObject getMessage(int start){
			 String oraUrl="jdbc:oracle:thin:@202.114.18.77:1521:myoracle";		
		   	    String oraUser="team";  
		   	     String oraPwd="team";  
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
		 /******      *****   ****下面是根据制定行号得到数据******        *******/
	  //select t.videoid,totalduration,videofile,videoaddr,videodatecome,videoname ,clickNum from VOLLEY_VIDEO t, VOLLEY_VIDEO_DYNAMIC t1 where t.videoid=t1.videoid
		  //  order by videodatecome desc;
		  try  
	       {   Class.forName("oracle.jdbc.driver.OracleDriver");
	           conn=DriverManager.getConnection(oraUrl,oraUser,oraPwd);  
	          // stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);  
	          stmt=conn.createStatement();
	         
	          rs1=stmt.executeQuery("select count(*)from  Badmintain_VIDEO t ,Badmintain_VIDEO_DYNAMIC t1 where t.videoid=t1.videoid");
	          while(rs1.next()){
	           total=Integer.valueOf( rs1.getObject(1).toString());//用于统计个数
	          }
	          j1.put("total", total);
	          rs=stmt.executeQuery("select * from (select row_number() over(order by videodatecome desc) r,t.videoorigin, t.videoid,totalduration,videofile,videoaddr,videodatecome,videoname ,clickNum from Badmintain_VIDEO t, Badmintain_VIDEO_DYNAMIC t1 where t.videoid=t1.videoid)"
	 +" where r between "+start+" and "+start+len); 
	          int i=1;  
	 while(rs.next()&&i<=len){
		    JSONObject jo=new JSONObject();
	        imageUrl=rs.getString("videoaddr");//图片地址
	        imageUrl=imageUrl.replace("\\", "/");
	         imageUrl=baseAddress+imageUrl; 
	        videoUrl=rs.getString("videofile");//视频地址
	        videoTitle=rs.getString("videoname");//视频标题
	        videoID=rs.getString("videoid");//精彩镜头
	        clicknum=rs.getInt("clickNum");
	        totalduration =rs.getInt("totalduration")/1000;//传入的是秒
	        videodateCome=rs.getString("videodatecome");
	        videodateCome=videodateCome.substring(0,videodateCome.indexOf(" "));//除去后面的时-分-秒
	        videoorigin=rs.getString("videoorigin");
	        if(videoTitle.contains("—在线播放"))
	       videoTitle=videoTitle.substring(0, videoTitle.indexOf("—在线播放"));    
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
			jobject.put("More", array);
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
		   getMore_Badmintain t=new getMore_Badmintain();
		   JSONObject list2=t.getMessage(1);
		   JSONArray a = null;
		try {
			a = (JSONArray) list2.get("More");
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
