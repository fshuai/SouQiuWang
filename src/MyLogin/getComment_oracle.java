package MyLogin;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class getComment_oracle {
    String videoid;
    String videotag;
     int  start;
     String oraUrl="jdbc:oracle:thin:@202.114.18.76:1521:myoracle";		
	    String oraUser="team";  
	    String oraPwd="team"; 
    int len=5;//注意这里设置的时候要比pagesize稍微大些
    static JSONArray array;
    JSONObject j1;
    JSONObject jobject;
    int total;
	public getComment_oracle(String videoid, String videotag,String start) {
		// TODO Auto-generated constructor stub
	 this.videoid=videoid;
	 this.videotag=videotag;
	 this.start=Integer.valueOf(start)+1;//因为数组的起始位置都是从0开始，而数据库是从1开始的，所以数据库中起始位置要加1，末尾位置要减少1
	 array=new  JSONArray();
	 j1=new JSONObject();
	 jobject=new JSONObject();
	}
	public JSONObject getCommentMessage() {
		// TODO Auto-generated method stub
		
		  ResultSet rs=null;
		  ResultSet rs1=null;
		   Connection conn=null;  
		    Statement stmt=null;
		    try  
	        {   Class.forName("oracle.jdbc.driver.OracleDriver");
	            conn=DriverManager.getConnection(oraUrl,oraUser,oraPwd);  
	           stmt=conn.createStatement();
	           rs1=stmt.executeQuery("select count(*)from  comments_kwb where videoid='"+videoid+"' and "+"videotag='"+videotag+"'");
	        		                       
		          while(rs1.next()){
		           total=Integer.valueOf( rs1.getObject(1).toString());//用于统计个数
		          }
		          j1.put("total", total);
	        rs=stmt.executeQuery("select * from(select row_number()over(order by com_time desc) r,name,videoid,videotag,content,com_time from comments_kwb where videoid='"+videoid+"' and "+"videotag='"+videotag+"')"
	        		   +" where r between "+start+" and "+(start+len-1));                      
	             int i=1;
	        while(rs.next()&&i<=len){
	    	JSONObject jo=new JSONObject();
	    	jo.put("com_name", rs.getString("name"));
	    	jo.put("com_content", rs.getString("content"));
	    	jo.put("com_time", rs.getString("com_time"));
	    	array.put(jo);
	    	i++;
	     }	 
	     System.out.println(array.length());
	    
		 rs.close(); 
	    conn.close();
	    jobject.put("com_total", j1);
	    jobject.put("com_message", array);
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
		return jobject;

}
	public static void main(String[]args) throws Throwable{
		
		getComment_oracle o=new getComment_oracle("6893","1","0");
		JSONObject jj=o.getCommentMessage();
		System.out.print( jj);
		JSONArray ar=jj.getJSONArray("com_message");
		System.out.println(ar.length());
		
	}
}