package User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WeiXinLoginServlet extends HttpServlet {


	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.flush();
		out.close();
	}
	/**
	 * 查询搜球网用户表中有没有该用户信息
	 */

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		int flag=isExistUsers_Kwb(username,password);
		out.print(flag);
		out.flush();
		out.close();
	}
	/**
	 * 返回0表示登陆成功，返回1表示用户名或密码错误，返回2表示用户名不存在，返回-1表示查询出错
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	
	private int isExistUsers_Kwb(String username,String password){
		String oraUrl = "jdbc:oracle:thin:@202.114.18.76:1521:myoracle";
		String oraUser = "team";
		String oraPwd = "team";
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(oraUrl, oraUser, oraPwd);
			stmt = conn.createStatement();
			String sql1="select * from users_kwb where username="+"'"+username+"'";
			rs = stmt.executeQuery(sql1);
			if (rs.next()) {
				System.out.println(rs.getString("username"));
				if(rs.getString("password").equals(password)){
					return 0;//表示登陆成功
				}
				return 1;//用户名或密码错误
			}
			else return 2;//表示用户名不存在
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	
	}


	public void init() throws ServletException {
		// Put your code here
	}

}
