package MyLogin;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import More.Update_Oracle;

public class Register extends HttpServlet {
	private int videoID; //传入进来的参数videoID
	private String tag="";
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
			int sex=Integer.valueOf(request.getParameter("sex"));
			String email=request.getParameter("email");
			String deviceid=request.getParameter("deviceid");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 24小时制
			Date date = new Date();
			String regtime = sdf.format(date);
			register_oracle t=new register_oracle(name, password, sex, email,regtime,deviceid);
			PrintWriter out=response.getWriter();
			out.print(name+":"+password+":"+email+":"+sex+":"+deviceid);
			out.flush(); 
		    out.close();
	}
	
	public void init(ServletConfig config) throws ServletException {
		// Put your code here
		super.init(config);
	}


}
