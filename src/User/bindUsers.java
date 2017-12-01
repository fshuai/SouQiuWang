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

import jnr.ffi.Struct.caddr_t;

public class bindUsers extends HttpServlet {

	/**
	 *��ȡ΢���û�������Ϣ
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
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

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String openid = request.getParameter("openid");
		String nickname = request.getParameter("nickname");
		String sex = request.getParameter("sex");
		String province = request.getParameter("province");
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		int flag = isExist_bideeduser(openid, nickname, sex, country, province,
				city);
		out.print(flag);
		//out.print(openid);
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	/**
	 * �������ݿ����������0����users_weixin����û�и��û�������1��ʾusers_weixin�����и��û���ѯusers_bind����
	 * ����2��ʾuser_bind�����и��û�������3��ʾusers_bind����û�и��û�
	 * ����2��ת����ʾ�û��Ѿ��󶨵�ҳ�棬���������ת����½ҳ��
	 * @param openid
	 * @param nickname
	 * @param sex
	 * @param country
	 * @param province
	 * @param city
	 * @return
	 */
	public int isExist_bideeduser(String openid, String nickname, String sex,
			String country, String province, String city) {
		String oraUrl = "jdbc:oracle:thin:@202.114.18.76:1521:myoracle";
		String oraUser = "team";
		String oraPwd = "team";
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt=null;
		try {
			
			// ���Ȳ�ѯ���ݿ�����û�и��û�
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(oraUrl, oraUser, oraPwd);

			PreparedStatement pstmt = conn
					.prepareStatement("insert into users_weixin values(?,?,?,?,?,?)");

			pstmt.setString(1, openid);
			pstmt.setString(2, nickname);
			pstmt.setString(3, sex);
			pstmt.setString(4, country);
			pstmt.setString(5, province);
			pstmt.setString(6, city);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			//��bind���в�ѯ
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(oraUrl, oraUser, oraPwd);
				stmt = conn.createStatement();
				String sql1="select * from users_bind where openid="+"'"+openid+"'";
				rs = stmt.executeQuery(sql1);
				if (rs.next()) {
					System.out.println(rs.getString("user_name"));
					return 2;
				}
				else return 3;
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return 1;
		}
		return 0;
	}
}
