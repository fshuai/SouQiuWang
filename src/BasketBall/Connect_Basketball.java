package BasketBall;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

public class Connect_Basketball {
	public static List<videoMessage> getInfo(){
	   	     String oraUrl="jdbc:oracle:thin:@202.114.18.77:1521:myoracle";		
	  	     String oraUser="team";  
	  	     String oraPwd="team";  
	  	     ResultSet rs=null; 
	  		 Connection conn=null;  
	  		 Statement stmt=null;
	  		 String imageUrl_Title="";
	  		 String videoUrl="";
	  	     String videoID=""; 
	  	     String imageurl="";
	  	     String videotitle="";
	  	     String videoorigin = null;
	  	   int i=1;  
	  List<videoMessage>message=new ArrayList<videoMessage>();
	 
			  try  
		        {   Class.forName("oracle.jdbc.driver.OracleDriver");
		            conn=DriverManager.getConnection(oraUrl,oraUser,oraPwd);  
		            // stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);  
		            stmt=conn.createStatement();
		            rs=stmt.executeQuery("select * from Basket_VIDEO t where videoid >="+10003);                         
		  while(rs.next()&&i<=4){		           
		         videoUrl=rs.getString("videofile");//视频地址		     
		         imageurl=rs.getString("videoaddr");//图片地址		
		         imageurl=getRealImageUrl(imageurl);
		         videotitle=rs.getString("videoname");//视频标题
		         videoID=rs.getString("videoid");//精彩镜头  
		         videoorigin=rs.getString("videoorigin");
		         imageUrl_Title=imageurl+","+videotitle;
		       message.add(new videoMessage(videoID,videoUrl,imageUrl_Title,videoorigin));
			     i++;
			  }	
		   
		    conn.close();
		        }catch(Exception e){
		        	try{
		        		i--;
		        		 rs.next();	
		        		videoUrl=rs.getString("videofile");//视频地址		     
				         imageurl=rs.getString("videoaddr");//图片地址		
				         imageurl=getRealImageUrl(imageurl);
				         videotitle=rs.getString("videoname");//视频标题
				         videoID=rs.getString("videoid");//精彩镜头  
				         videoorigin=rs.getString("videoorigin");
				         imageUrl_Title=imageurl+","+videotitle;
				       message.add(new videoMessage(videoID,videoUrl,imageUrl_Title,videoorigin));
		        	}catch (Exception e1) {
						// TODO: handle exception
					e1.printStackTrace();
		        	}
		        }
			return message;
			

			
		}
	public static String getRealImageUrl(String originimageurl){
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
	         String baseAddress="http://202.114.18.70";
	         originimageurl=baseAddress+"/Basketball_Images/Image/"+billion+"/"+million+"/"+thousand+"/Video_"+videioid+"/"+ filename;

	     }
	     return originimageurl;
	 }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[]str=null;
		Connect_Basketball j=new Connect_Basketball();
		List<videoMessage>l=j.getInfo();
		str=new String[l.size()];
		for(int i=0;i<l.size();i++){
			
			str[i]=l.get(i).getImageUrl_Title()+"\t"+l.get(i).getVideoURL()+"\t"+l.get(i).getVideoID()+"\t"+l.get(i).getVideoOrigin();
		 System.out.println(str[i]);
		}
	}
	   
	}