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
	 * ��ѯ�������û�������û�и��û���Ϣ
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
	 * ����0��ʾ��½�ɹ�������1��ʾ�û�����������󣬷���2��ʾ�û��������ڣ�����-1��ʾ��ѯ����
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
					return 0;//��ʾ��½�ɹ�
				}
				return 1;//�û������������
			}
			else return 2;//��ʾ�û���������
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	
	}


	public void init() throws ServletException {
		// Put your code here
	}

}
