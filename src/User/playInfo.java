package User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.python.google.common.cache.Weigher;

import More.Update_Oracle;

public class playInfo extends HttpServlet {
	/*
	 * 
	 * create table videovector_kwb( videoid int primary key, deviceid
	 * varchar2(50), username char(20), playtime float, playnumber int );;
	 * insert into videovector_kwb(videoid, username,playinfo)
	 * values('1234','kwb','中超 恒大 国安') select * from videovector_kwb where
	 * username='kwb';
	 */

	private int videoID; // 传入进来的参数videoID
	private String tag = "";

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
		String mode = request.getParameter("mod");
		String id = request.getParameter("playid");
		PrintWriter out = response.getWriter();
		if (mode.equals("0")) {// 找出类似视频
			String keyword = getInfo(id);
			out.print(keyword);
		} else if (mode.equals("1")) {// mode=1 为播放
			String name = request.getParameter("username");
			if (name == null || name.equals(""))
				name = "default";
			String deviceid = request.getParameter("deviceid");
			String playtime = request.getParameter("playtime");
			String totaltime = request.getParameter("totaltime");
			try {
				writePlayConfigXML(mode, deviceid, name, id,
						Integer.parseInt(playtime), Integer.parseInt(totaltime));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			out.print("用户名:" + name + "设备:" + deviceid + "视频id:" + id + "播放时间:"
					+ playtime + " 总时间:" + totaltime + "关键词" + getInfo(id));
		} else {// 转发QQ好友，微信好友，QQ空间
			String name = request.getParameter("username");
			if (name == null || name.equals(""))
				name = "default";
			String deviceid = request.getParameter("deviceid");
			try {
				writeShareConfigXML(mode, deviceid, name, id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("用户名:" + name + "设备:" + deviceid + "视频id:" + id + "关键词"
					+ getInfo(id) + " mode:" + mode);
		}
		out.flush();
		out.close();

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

	// 用户播放信息写入txt文件中
	public static void writePlayConfigXML(String mode, String deviceid,
			String name, String videoid, int playtime, int totaltime)
			throws Exception {
		String targetfile = "E:\\kwb\\LunWen\\User\\" + deviceid + "#" + name
				+ ".txt";
		String info = getInfo(videoid);// 得出用户关键词

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String ctime = sdf.format(now);// 当前时间
		if (info == null) {
			info = "";
		}
		try {
			BufferedWriter bufferwriter = new BufferedWriter(new FileWriter(
					targetfile, true));
			double rate = 0;
			if (playtime < 0.00000001) {// 只是浏览
				rate = 0.1;
			} else
				rate = (double) playtime / totaltime;

			if (rate > 0.75)// 如果播放超过了3/4那么算起播放完成
				rate = 1;

			bufferwriter.write(mode + "#" + videoid + "#" + info.trim() + "#"
					+ ctime + "#" + rate + '\n');
			bufferwriter.flush();
			bufferwriter.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// 转发
	private void writeShareConfigXML(String mode, String deviceid, String name,
			String videoid) {
		String targetfile = "E:\\kwb\\LunWen\\User\\" + deviceid + "#" + name
				+ ".txt";
		String info = getInfo(videoid);// 得出用户关键词
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String ctime = sdf.format(now);// 当前时间

		if (info == null) {
			info = " ";
		}
		try {

			double rate = 0.5;// 转发默认0.5
			if(mode.equals("3")){//转发到空间的权重不一样
				rate=0.8;
			}
			BufferedWriter bufferwriter = new BufferedWriter(new FileWriter(
					targetfile, true));
			bufferwriter.write(mode + "#" + videoid + "#" + info.trim() + "#"
					+ ctime + "#" + rate + '\n');
			bufferwriter.flush();
			bufferwriter.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/************* 下面目前没有 ***********/
	// 用户播放信息写入数据库中
	public static void writeConfigOracle(String deviceid, String name,
			String videoid, int playtime, double totaltime) throws Exception {
		if (name == null || name.equals(""))
			name = "default";
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
			String sql = "select count(*) from videovector_kwb  where videoid="
					+ videoid + " and deviceid='" + deviceid
					+ "' and username = '" + name + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int total = 0;
			while (rs.next()) {
				total = Integer.valueOf(rs.getObject(1).toString());// 用于统计个数
			}
			if (total != 0) {
				sql = "update videovector_kwb  set playnumber=(playnumber+1),playtime=(playtime+"
						+ playtime
						+ ") where videoid="
						+ videoid
						+ " and deviceid='"
						+ deviceid
						+ "' and username = '"
						+ name + "'";
				stmt.execute(sql);
				conn.commit();
				conn.close();
			} else {
				PreparedStatement ps = conn
						.prepareStatement("insert into videovector_kwb(videoid,deviceid,username,playtime,playnumber,totaltime) values(?,?,?,?,?,?)");
				ps.setString(1, videoid);
				ps.setString(2, deviceid);
				ps.setString(3, name);
				ps.setInt(4, playtime);
				ps.setInt(5, 1);// 如果用户第一次播放视频
				ps.setDouble(6, totaltime);
				ps.execute();
				conn.commit();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void init(ServletConfig config) throws ServletException {
		// Put your code here
		super.init(config);
	}

}
