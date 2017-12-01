package VariousBalls;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.StringTokenizer;

import sun.net.www.http.HttpClient;
import sun.net.www.protocol.http.HttpURLConnection;


public class transferURL {
       static String title;
       static String imgUrl;
	   static String result;
       static String tem;
       static String []str; 
       static String [][]temp;
       //static LinkedList<String>l=new LinkedList<String>();
       static int start;
       static int end;
       static String baseURL="http://192.168.0.128/youku_address.php?addr=";
       //http://192.168.0.116/youku_address.php?addr=http://v.youku.com/v_show/id_XODMzNjExMDYw.html
       public transferURL(){	   
       }
       //测试所用
       public static void main(String[]args){
    	  System.out.println(getVideoUrl("http://v.youku.com/v_show/id_XOTU3NDE2NzM2.html")); 
       }
	public static String getVideoUrl(String videoURL) {
		// TODO Auto-generated method stub
		 String t="";//注意这里要设置局部变量，而不能设计成全局变量，因为前部的连接是在一次会话中，否则信息不能被覆盖
		 try {	
			result="";
			 HttpURLConnection con=null;
			URL url=new URL(baseURL+videoURL);
		     con=(HttpURLConnection)url.openConnection();
			InputStreamReader in=new InputStreamReader(con.getInputStream(),"utf-8") ;
			BufferedReader buf=new BufferedReader(in);
			while((tem=buf.readLine())!=null){
				result+=tem+"\n";			
			}
			in.close();
			con.disconnect();	
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();	
	}
		start=result.indexOf("normal");
	    start=result.indexOf("(", start);
	    end=result.indexOf(")",start);
	    result=result.substring(start+1, end);
	    str=result.split("\n");
	    temp=new String[str.length][2];////////////
	    int i=0;
	    for(String strAdd:str){     
	    	if(strAdd.length()<10)
	    		continue;
	    	start=0;
	    	start=strAdd.indexOf("=>", start);//开始匹配位置
	    	if(start<0)
	    	continue;
	    	start+=3;
	    	end=strAdd.indexOf(" ", start);
	    	if(end<0)
	    	continue;
	    	//l.add(strAdd.substring(start, end));
	    	  temp[i][0]=strAdd.substring(start, end);///////
	    	//getSecondes
	    	 start=end+1;
		        end=strAdd.indexOf(" ",start);
		      String   strURl=strAdd.substring(end+1);
		      temp[i][1]=strURl;/////
		      t+=temp[i][0]+" ";//路径
			  t+=temp[i][1]+"=>"; //时间
		      i++;
	    } 	   	             
 return t;
	}
}
