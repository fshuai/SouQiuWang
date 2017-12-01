package Leaque;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class TextIdentifyServlet extends HttpServlet {

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			doPost(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		try{
			 String title =request.getParameter("title");
			 title=new String(title.getBytes("iso-8859-1"),"utf-8");
			 String targetfile="E://1.txt";
             OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(targetfile),"utf-8");
	         BufferedWriter buffer= new BufferedWriter(new FileWriter(targetfile));
		     buffer.write(title);
		     buffer.flush();
		     buffer.close();
  		    ClubInfo c=new ClubInfo();
    	    String result=c.getInfo();
		   
//			String id =request.getParameter("id");
//			
//            String result=getInfo(id);
            PrintWriter out = response.getWriter();
	        out.print(result);
	        out.flush(); 
 	        out.close();
		 }catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
  
	private String getInfo(String id) {
		// TODO Auto-generated method stub
		 String oraUrl="jdbc:oracle:thin:@202.114.18.96:1521:orcl";		
		 String oraUser="volleyball";  
		 String oraPwd="adminvolleyball";
		 Connection conn=null;  
 		 Statement stmt=null;
 		  String keyword="";
		  
		    ResultSet rs=null; 
		  try  
	        {   Class.forName("oracle.jdbc.driver.OracleDriver");
	            conn=DriverManager.getConnection(oraUrl,oraUser,oraPwd);  	        
	            stmt=conn.createStatement();
	            rs=stmt.executeQuery("select * from text_kwb  where videoid="+Integer.parseInt(id));                   	            
	    while(rs.next()){	        		
		     keyword=rs.getString("keyword");
	     
		   
		  }	
	      conn.close();
	        }catch(Exception e){
	        }
		return keyword;
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
 
}
