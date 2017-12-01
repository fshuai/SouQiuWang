package VariousBalls;
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

public class FootBallServlet extends HttpServlet {
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
			Connect_football j=new Connect_football();
			JSONArray info=j.getInfo();
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
