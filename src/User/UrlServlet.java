package User;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UrlServlet extends HttpServlet {

	String videourl;
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		try {
			doPost(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
		  response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			try{      			       
				     InputStreamReader in=new InputStreamReader( request.getInputStream());
				       BufferedReader buff=new BufferedReader(in);
				       String l="";
				       String res="";
				       while((l=buff.readLine())!=null){
				       res=l;
				      }
				      
				      in.close();
				      buff.close();
			
				     realAddress t=new realAddress();
		  	          videourl =t.getVideoUrl(res);
				PrintWriter out = response.getWriter();	
			
				StringBuffer sb=null; 
				sb= new StringBuffer();//数据缓存            
	  	          //注意对于给定的优酷视频的网页，需要将其转换成对应的视频播放地址
			         
	  	            sb.append(videourl);              		                               
	           out.write(new String(sb)); 
	           out.flush(); 
	  	           
	           out.close();//关闭    	        
			}catch(Exception e){	
			}	 
	    }  
	  
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init(ServletConfig config) throws ServletException {
		// Put your code here
		super.init(config);
	}


}
