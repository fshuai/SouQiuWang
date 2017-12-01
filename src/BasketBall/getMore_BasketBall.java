package BasketBall;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import More.getMore;
import More.getMore_football;

public class getMore_BasketBall {

	static String baseAddress="http://202.114.18.70";
	 static int len=8;//ԭ���ǹ̶�ס��ָÿ��������ؾ���8��,������������һ���Զ��������ں�����len=total�Ϳ�����
		
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
		 /******      *****   ****�����Ǹ����ƶ��кŵõ�����******        *******/
		  try  
	       {   Class.forName("oracle.jdbc.driver.OracleDriver");
	           conn=DriverManager.getConnection(oraUrl,oraUser,oraPwd);  
	          // stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);  
	          stmt=conn.createStatement();
	         
	          rs1=stmt.executeQuery("select count(*)from  Basket_VIDEO t ,Basket_VIDEO_DYNAMIC t1 where t.videoid=t1.videoid");
	          while(rs1.next()){
	           total=Integer.valueOf( rs1.getObject(1).toString());//����ͳ�Ƹ���
	          }
	          j1.put("total", total);
	          rs=stmt.executeQuery("select * from (select row_number() over(order by videodatecome asc) r, t.videoorigin,t.videoid,totalduration,videofile,videoaddr,videodatecome,videoname ,clickNum from Basket_VIDEO t, Basket_VIDEO_DYNAMIC t1 where (t.videoid=t1.videoid)and (t.videoid>10003))"
	 +" where r between "+start+" and "+start+len); //������һЩ��Ƶ�Ų��������������������һЩ������videoid>10003
	          int i=1;  
	 while(rs.next()&&i<=len){
		    JSONObject jo=new JSONObject();
	        imageUrl=rs.getString("videoaddr");//ͼƬ��ַ
	        imageUrl=getRealImageUrl(imageUrl);//������մ������ʵͼƬ��ַ
	        videoUrl=rs.getString("videofile");//��Ƶ��ַ
	        videoTitle=rs.getString("videoname");//��Ƶ����
	        videoID=rs.getString("videoid");//���ʾ�ͷ
	        clicknum=rs.getInt("clickNum");
	        totalduration =rs.getInt("totalduration")/1000;//���������
	        videodateCome=rs.getString("videodatecome");
	        videodateCome=videodateCome.substring(0,videodateCome.indexOf(" "));//��ȥ�����ʱ-��-��
	        if(videoTitle.contains("�����߲���"))
	       videoTitle=videoTitle.substring(0, videoTitle.indexOf("�����߲���")); 
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
			jobject.put("More", array);
			 jobject.put("total", j1);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobject;	
	}
		public static String getRealImageUrl(String originimageurl){
			 int start_index,end_index;
		     String videioid;//��Ƶid
		     String filename;//֡��
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
		             //������Ƶidȷ���ļ��Ĵ�Žṹ,��billion/million/thousand/filename
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
		                 //overflow �����ܵ���һ��,��Ϊ����һ��˵����Ƶid�Ѿ��������ˣ�����֮���ǲ��ܵ�
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

	//��������,�������ʽ
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   getMore_BasketBall t=new getMore_BasketBall ();
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
