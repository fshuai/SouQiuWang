package BasketBall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import VariousBalls.transferURL;

public class BasketURLServlet extends HttpServlet {
	String videourl;//其它的球都是通过Url来获取真实视频,而篮球是通过videoID；
	public void destroy() {
		super.destroy(); 
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
				       String videoID="";
				       String videoid="";
				       while((videoID=buff.readLine())!=null){
				       videoid=videoID;
				      }
				      
				      in.close();
				      buff.close();
			
				      transferURL t=new transferURL();
		  	          videourl =t.getVideoUrl(videoid);
				PrintWriter out = response.getWriter();	
			
				StringBuffer sb=null; 
				sb= new StringBuffer();//数据缓存            
	  	          //注意对于给定的优酷视频的网页，需要将其转换成对应的视频播放地址
			         
	  	            sb.append("{"+videourl+"}");              		                               
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
