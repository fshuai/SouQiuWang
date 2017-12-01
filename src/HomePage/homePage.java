package HomePage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;


public class homePage extends HttpServlet {
   
	String baseAddress="http://202.114.18.70";
	 String videourl;
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		JSONObject info=new JSONObject();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		try{
			PrintWriter out = response.getWriter();
			Connect_football football=new Connect_football();
			Connect_Snooker   snooker=new Connect_Snooker();
			Connect_Volleyball volleyball=new Connect_Volleyball();
			JSONArray ja=football.getInfo();      //×ãÇò    		                     
			JSONArray ja1=snooker.getInfo();     //Ì¨Çò
			JSONArray ja2=volleyball.getInfo();  //ÅÅÇò
            info.put("football", ja);
            info.put("snooker", ja1);
            info.put("volleyball", ja2);
            out.write(info.toString());  
            out.flush();  
            out.close();//¹Ø±Õ  
		}catch(Exception e){	
		}	
	}
	  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	        doGet(request, response);  
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