package User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WeiXinBindServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String openid=request.getParameter("openid");
		String username=request.getParameter("username");
		int flag=insertBind(openid, username);
		out.print(flag);
		out.flush();
		out.close();
	}
	/**
	 * 正确插入返回0，否则返回1
	 * @param openid
	 * @param username
	 * @return
	 */
	
	private int insertBind(String openid,String username){
		String oraUrl = "jdbc:oracle:thin:@202.114.18.76:1521:myoracle";
		String oraUser = "team";
		String oraPwd = "team";
		Connection conn = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(oraUrl, oraUser, oraPwd);
			PreparedStatement pstmt = null;
			pstmt = conn.prepareStatement("insert into users_bind values(?,?)");
			pstmt.setString(1,openid);
			pstmt.setString(2,username);
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
			return 1;
		}
		return 0;
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
