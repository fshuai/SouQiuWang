package BasketBall;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import VariousBalls.Connect_football;
import VariousBalls.videoMessage;

public class BasketServlet extends HttpServlet {
	String baseAddress="http://202.114.18.70";
	 String videourl;
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		try{
			PrintWriter out = response.getWriter();
			Connect_Basketball j=new Connect_Basketball();
			List<BasketBall.videoMessage>l= j.getInfo();
			String[]str=new String[l.size()];
			StringBuffer sb = new StringBuffer();//数据缓存   
         //输出格式
          for(int i=0;i<l.size();i++){
          	String videoID=l.get(i).getVideoID();
           
 	            sb.append(videoID+","+l.get(i).getImageUrl_Title()+","+l.get(i).getVideoURL()+","+l.get(i).getVideoOrigin());
 	            sb.append(';');///根据分号来解析          
      		    		                     
          }           
          out.write(new String(sb));  
          out.flush();  
          out.close();//关闭  
		}catch(Exception e){	
		}	
	}
	  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	        doGet(request, response);  
	    }  
	public void init(ServletConfig config) throws ServletException {
		// Put your code here
		super.init(config);
	}

}
