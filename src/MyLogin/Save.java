package MyLogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Save extends HttpServlet {
	 String oraUrl="jdbc:oracle:thin:@202.114.18.76:1521:myoracle";		
	    String oraUser="team";  
	    String oraPwd="team";    
	public void destroy() {
		super.destroy();
		
	}

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
		    String name=request.getParameter("name");
		    name=new String(name.getBytes("iso-8859-1"),"utf-8");
		    String newname=request.getParameter("newname");
		    newname=new String(newname.getBytes("iso-8859-1"),"utf-8");
			int sex=Integer.valueOf(request.getParameter("sex"));
			String birthtime=request.getParameter("birthtime");
			birthtime=new String(birthtime.getBytes("iso-8859-1"),"utf-8");
			EditUser t=new EditUser(name, newname, sex, birthtime);
			PrintWriter out=response.getWriter();
			out.print(name+":"+newname+":"+sex+":"+birthtime);
			out.flush(); 
		    out.close();
	}
	
	public void init(ServletConfig config) throws ServletException {
		// Put your code here
		super.init(config);
	}


}
