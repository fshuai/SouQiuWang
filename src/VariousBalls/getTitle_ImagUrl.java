package VariousBalls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import sun.net.www.protocol.http.HttpURLConnection;

public class getTitle_ImagUrl {

	/**
	 * @param args
	 */
	  static String title;
      static String imgUrl;
	   static String result;
      static String tem;
      static int start;
      static int end;
      static String baseURL="http://192.168.0.116/youku_address.php?addr=";
      //http://192.168.0.116/youku_address.php?addr=http://v.youku.com/v_show/i
	public static String getTitle(String path){
		 String t="";//ע������Ҫ���þֲ���������������Ƴ�ȫ�ֱ�������Ϊǰ������������һ�λỰ�У�������Ϣ���ܱ�����
		 try {	
			result="";
			 HttpURLConnection con=null;
			URL url=new URL(baseURL+path);
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
		 start=result.indexOf("http",result.indexOf("[img]"));
		 end=result.indexOf("\n",start);
		 imgUrl=result.substring(start,end);
		 start=result.indexOf("title");
		 start=start+10;//ȥ��[title]=>
		 end=result.indexOf("\n",start);
		 title=result.substring(start, end);
		 return imgUrl+","+title;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
           System.out.print(getTitle("http://v.youku.com/v_show/id_XODcyNzUzNDQ4.html"));
	}

}
