import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class GetConnection {
	
    public static JSONObject getCon(){
    	 String oraUrl="jdbc:oracle:thin:@202.114.18.76:1521:myoracle";		
		 String oraUser="team";  
		 String oraPwd="team";  
		 ResultSet rs=null;
		 Connection conn=null;  
		 Statement stmt=null;		 
		 JSONObject data = new JSONObject();
         JSONArray array1 = new JSONArray();
         JSONArray array2 = new JSONArray();
         JSONArray array3 = new JSONArray();
		 
		 try{	
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 conn = DriverManager.getConnection(oraUrl,oraUser,oraPwd);
			 System.out.println("Connection OK");
			 stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);  //滚动集,如果不滚动可用stmt=conn.createStatement();
			 
			 //cctv-5
			 //查询本时间点后的节目
			 rs=stmt.executeQuery("select * from tvlive_programs where PRGTIME > sysdate and PRGDATE = to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd') and TVID = '1'");
			 rs.last(); //游标跳到最后一行
			 int n1 = rs.getRow();   //得到数据集总行数
			 rs.close();
			 //查询当天 所有节目
			 rs=stmt.executeQuery("select * from tvlive_programs where TVID = '1' and PRGDATE = to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd') order by PRGTIME");       
	         rs.last();
	         int n2 = rs.getRow();   //数据集总行数
			 rs.absolute(n2-n1-1);     //定位行
			 while(rs.next()){
				 JSONObject obj= new JSONObject();
	        	 obj.put("time", rs.getTime("PRGTIME").toString().substring(0,5));      //日期原格式为22:46:00，转换为22:46
	        	 obj.put("program", rs.getString("PRGNAME"));
	        	 array1.put(obj);
			 }
			 rs.close();
			 
			//fengyunzuqiu
			 rs=stmt.executeQuery("select * from tvlive_programs where PRGTIME > sysdate and PRGDATE = to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd') and TVID = '2'");
			 rs.last();
			 int n3 = rs.getRow();   //数据集总行数
			 rs.close();
			 //查询当天 所有节目
			 rs=stmt.executeQuery("select * from tvlive_programs where TVID = '2' and PRGDATE = to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd') order by PRGTIME");       
	         rs.last();
	         int n4 = rs.getRow();   //数据集总行数
			 rs.absolute(n4-n3-1);     //定位行
			 while(rs.next()){
				 JSONObject obj= new JSONObject();
	        	 obj.put("time", rs.getTime("PRGTIME").toString().substring(0,5));      //日期原格式为22:46:00，转换为22:46
	        	 obj.put("program", rs.getString("PRGNAME"));
	        	 array2.put(obj);
			 }
			 rs.close();
			 
			//ouzhouzuqiu
			 rs=stmt.executeQuery("select * from tvlive_programs where PRGTIME > sysdate and PRGDATE = to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd') and TVID = '3'");
			 rs.last();
			 int n5 = rs.getRow();   //数据集总行数
			 rs.close();
			 //查询当天 所有节目
			 rs=stmt.executeQuery("select * from tvlive_programs where TVID = '3' and PRGDATE = to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd') order by PRGTIME");       
	         rs.last();
	         int n6 = rs.getRow();   //数据集总行数
			 rs.absolute(n6-n5-1);     //定位行
			 while(rs.next()){
				 JSONObject obj= new JSONObject();
	        	 obj.put("time", rs.getTime("PRGTIME").toString().substring(0,5));      //日期原格式为22:46:00，转换为22:46
	        	 obj.put("program", rs.getString("PRGNAME"));
	        	 array3.put(obj);
			 }
			 rs.close();
		 
	         data.put("0", array1);   //cctv-5
	         data.put("1", array2);   //fengyunzuqiu
	         data.put("2", array3);    //ouzhouzuqiu
	         
	         stmt.close();
	         conn.close();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return data;
    }


    
    public static void main(String[] args){
    	JSONObject data = GetConnection.getCon();
    	System.out.println(data);
    }
    
}
