package More;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Update_Oracle {
  //���ڲ�ͬ��videoid����ͬ��tag�����Ӳ�ͬ�����ݿ�
	 int  tag;
	 int  videoID;
	 public Update_Oracle(String videoID,String tag) {
		// TODO Auto-generated constructor stub
	  this.tag=Integer.valueOf(tag);
	  this.videoID=Integer.valueOf(videoID.trim());
	  if(this.tag==1){
		 updateTheClickNum1();	  
	  }
	  if(this.tag==2){
		  updateTheClickNum2();
	  }
	  if(this.tag==3){
		 updateTheClickNum3();
	  }
	  if(this.tag==4){
		  updateTheClickNum4();
	  }
	  if(this.tag==5){
		  updateTheClickNum5();
	  }
	  if(this.tag==6){
		  updateTheClickNum6(); 
	  }
	  if(this.tag==7){
		  updateTheClickNum7();
	  }
	 }
	 ///��������
	 public void updateTheClickNum1(){
		 String oraUrl="jdbc:oracle:thin:@202.114.18.96:1521:orcl";		
		 String oraUser="volleyball";  
		 String oraPWD="adminvolleyball";
		 Connection conn=null;  
		 PreparedStatement stmt=null;
		 String  sql="update VOLLEY_VIDEO_DYNAMIC set clicknum=clicknum+1 where videoid='"+videoID+"'";
		 try  
	        {   Class.forName("oracle.jdbc.driver.OracleDriver");
	            conn=DriverManager.getConnection(oraUrl,oraUser,oraPWD);    
	            stmt=conn.prepareStatement(sql);
	            stmt.executeUpdate();
	            stmt.close();
	            conn.close();          
	 }catch (Exception e) {
		// TODO: handle exception
		 e.printStackTrace();
	}
}
	 //��������
	 public void updateTheClickNum2(){
		 String oraUrl="jdbc:oracle:thin:@202.114.18.76:1521:myoracle";		
	   	    String oraUser="team";  
	   	     String oraPwd="team";
	   	  Connection conn=null; 
	   	  PreparedStatement stmt=null;
			 String  sql="update VIDEO_DYNAMIC set clicknum=clicknum+1 where videoid='"+videoID+"'";
			 try  
		        {   Class.forName("oracle.jdbc.driver.OracleDriver");
		            conn=DriverManager.getConnection(oraUrl,oraUser,oraPwd);    
		            stmt=conn.prepareStatement(sql);
		            stmt.executeUpdate();
		            stmt.close();
		            conn.close();   
		 
	 }catch (Exception e) {
		// TODO: handle exception
		 e.printStackTrace();
	}
}
	//����̨��
	 public void updateTheClickNum3(){
		 String oraUrl="jdbc:oracle:thin:@202.114.18.96:1521:orcl";		
	 	    String oraUser="snooker";  
	 	     String oraPwd="snooker"; 
		 Connection conn=null; 
		 PreparedStatement stmt=null;
		 String  sql="update SNOOKER_VIDEO_DYNAMIC set clicknum=clicknum+1 where videoid='"+videoID+"'";
		 try  
	        {   Class.forName("oracle.jdbc.driver.OracleDriver");
	            conn=DriverManager.getConnection(oraUrl,oraUser,oraPwd);    
	            stmt=conn.prepareStatement(sql);
	            stmt.executeUpdate();
	            stmt.close();
	            conn.close();  
		 
	 }catch (Exception e) {
		// TODO: handle exception
		 e.printStackTrace();
	}
		 
}
	 //���ڸ�����ë��
	 public void updateTheClickNum4(){
		 String oraUrl="jdbc:oracle:thin:@202.114.18.77:1521:myoracle";		
	   	    String oraUser="team";  
	   	     String oraPwd="team";
	   	  Connection conn=null; 
	   	  PreparedStatement stmt=null;
			 String  sql="update Badmintain_VIDEO_DYNAMIC set clicknum=clicknum+1 where videoid='"+videoID+"'";
			 try  
		        {   Class.forName("oracle.jdbc.driver.OracleDriver");
		            conn=DriverManager.getConnection(oraUrl,oraUser,oraPwd);    
		            stmt=conn.prepareStatement(sql);
		            stmt.executeUpdate();
		            stmt.close();
		            conn.close();   
		 
	 }catch (Exception e) {
		// TODO: handle exception
		 e.printStackTrace();
	}
}
	//���ڸ���ƹ����
		 public void updateTheClickNum5(){
			 String oraUrl="jdbc:oracle:thin:@202.114.18.77:1521:myoracle";		
		   	    String oraUser="team";  
		   	     String oraPwd="team";
		   	  Connection conn=null; 
		   	  PreparedStatement stmt=null;
				 String  sql="update tts_VIDEO_DYNAMIC set clicknum=clicknum+1 where videoid='"+videoID+"'";
				 try  
			        {   Class.forName("oracle.jdbc.driver.OracleDriver");
			            conn=DriverManager.getConnection(oraUrl,oraUser,oraPwd);    
			            stmt=conn.prepareStatement(sql);
			            stmt.executeUpdate();
			            stmt.close();
			            conn.close();   
			 
		 }catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
	}
		//���ڸ�������
		 public void updateTheClickNum6(){
			 String oraUrl="jdbc:oracle:thin:@202.114.18.77:1521:myoracle";		
		   	    String oraUser="team";  
		   	     String oraPwd="team";
		   	  Connection conn=null; 
		   	  PreparedStatement stmt=null;
				 String  sql="update Tennis_VIDEO_DYNAMIC set clicknum=clicknum+1 where videoid='"+videoID+"'";
				 try  
			        {   Class.forName("oracle.jdbc.driver.OracleDriver");
			            conn=DriverManager.getConnection(oraUrl,oraUser,oraPwd);    
			            stmt=conn.prepareStatement(sql);
			            stmt.executeUpdate();
			            stmt.close();
			            conn.close();   
			 
		 }catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
	}
		//���ڸ�������
		 public void updateTheClickNum7(){
			 String oraUrl="jdbc:oracle:thin:@202.114.18.77:1521:myoracle";		
		   	    String oraUser="team";  
		   	     String oraPwd="team";
		   	  Connection conn=null; 
		   	  PreparedStatement stmt=null;
				 String  sql="update Basket_VIDEO_DYNAMIC set clicknum=clicknum+1 where videoid='"+videoID+"'";
				 try  
			        {   Class.forName("oracle.jdbc.driver.OracleDriver");
			            conn=DriverManager.getConnection(oraUrl,oraUser,oraPwd);    
			            stmt=conn.prepareStatement(sql);
			            stmt.executeUpdate();
			            stmt.close();
			            conn.close();   
			 
		 }catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
	}
	 //���ڲ���
	 public static void main(String[]args){
		 Update_Oracle t=new Update_Oracle("10004","7" );

	 }
}