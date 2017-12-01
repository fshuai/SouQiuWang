package User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WeiXinRegisterServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String gender1=request.getParameter("gender");
		int gender=Integer.valueOf(gender1);
		PrintWriter out = response.getWriter();
		int flag=insertRegister(username, password, email, gender);
		out.print(flag);
		out.flush();
		out.close();
	}
	/**
	 * 返回0代表插入成功
	 * 返回1代表插入失败
	 * @param username
	 * @param password
	 * @param email
	 * @param gender
	 * @return
	 */
	private int insertRegister(String username,String password,String email,int gender){
		String oraUrl = "jdbc:oracle:thin:@202.114.18.76:1521:myoracle";
		String oraUser = "team";
		String oraPwd = "team";
		Connection conn = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(oraUrl, oraUser, oraPwd);
			PreparedStatement pstmt = null;
			pstmt = conn.prepareStatement("insert into users_kwb values(?,?,?,?,?,?,?,?)");
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			pstmt.setInt(3, gender);
			pstmt.setString(4,email);
			pstmt.setString(5,null);
			pstmt.setString(6,null);
			pstmt.setString(7,null);
			pstmt.setString(8, null);
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
