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

public class login extends HttpServlet {
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
		    Connection conn=null; 
		    PreparedStatement stmt=null;
		    String name=request.getParameter("name");
			String password=request.getParameter("password");
			login_oracle t=new login_oracle(name, password);
			String status=t.getStatus();
			PrintWriter out=response.getWriter();
			out.print(status);
			out.flush(); 
		    out.close();
	}
	
	public void init(ServletConfig config) throws ServletException {
		// Put your code here
		super.init(config);
	}


}
