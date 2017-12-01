import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import User.realAddress;

public class Address extends HttpServlet {

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
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
		String videourl = "";
		ResultSet rs = null;
		Connection conn = null;
		Statement stmt = null;
		try {
			final String videoid = request.getParameter("videoid");
			final String openid = request.getParameter("openid");
			String videourl1 = request.getParameter("videourl");
			final double weight=0.2;//这里默认weight为0.2
			new Thread() {
				public void run() {
					try {						
						String path="E:\\kwb\\LunWen\\User\\"+openid+".txt";
						File file =new File(path);
						if(file.exists()==false)
							file.createNewFile();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date now =new Date();
						String ctime =sdf.format(now);//当前时间
						String keyword=getInfo(videoid);
					
						if(keyword==null){
							keyword=" ";
						}
						else
							keyword=keyword.trim();
						BufferedWriter bu = new BufferedWriter(new FileWriter(path));
						bu.write("1#"+ videoid+"#"+keyword+"#"+ctime+"#"+weight);
						bu.flush();
						bu.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}.start();
			realAddress r = new realAddress();

			videourl = r.getVideoUrl(videourl1);
			PrintWriter out = response.getWriter();

			out.write(videourl);
			out.flush();

			out.close();// 关闭

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init(ServletConfig config) throws ServletException {
		// Put your code here
		super.init(config);
	}
	// 获取关键词
		private static String getInfo(String id) {
			// TODO Auto-generated method stub
			String oraUrl = "jdbc:oracle:thin:@202.114.18.76:1521:myoracle";
			String oraUser = "team";
			String oraPwd = "team";
			Connection conn = null;
			Statement stmt = null;
			String keyword = "";
			ResultSet rs = null;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(oraUrl, oraUser, oraPwd);
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select * from text_kwb  where videoid="
						+ Integer.parseInt(id));
				while (rs.next()) {
					keyword = rs.getString("keyword");

				}
				conn.close();
			} catch (Exception e) {
			}
			return keyword;
		}
  public static void main(String[]args){
	    String  t=getInfo("44758");
	     System.out.println(t);
  }
}
