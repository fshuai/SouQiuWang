package MyLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

public class myComment_oracle {
	/**
	 * �����������ݿ� create table comments_kwb( name varchar(20), videoid varchar(10)
	 * , videotag int , content varchar(200), com_time varchar(20), videotitle
	 * varchar(200))
	 * 
	 */
	String myname;
	int start;
	String oraUrl = "jdbc:oracle:thin:@202.114.18.76:1521:myoracle";
	String oraUser = "team";
	String oraPwd = "team";
	int len = 5;// ע���������õ�ʱ��Ҫ��pagesize��΢��Щ
	static JSONArray array;
	JSONObject j1;
	JSONObject jobject;
	int total;

	public myComment_oracle(String myname, String start) {
		// TODO Auto-generated constructor stub
		this.myname = myname;
		this.start = Integer.valueOf(start) + 1;
		array = new JSONArray();
		j1 = new JSONObject();
		jobject = new JSONObject();
	}

	public JSONObject getMyCommentMessage() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		ResultSet rs1 = null;
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(oraUrl, oraUser, oraPwd);
			stmt = conn.createStatement();
			rs1 = stmt
					.executeQuery("select count(*)from  comments_kwb where name='"
							+ myname + "'");

			while (rs1.next()) {
				total = Integer.valueOf(rs1.getObject(1).toString());// ����ͳ�Ƹ���
			}
			j1.put("total", total);
			rs = stmt
					.executeQuery("select * from(select row_number()over(order by com_time desc) r,name,videoid,videotag,content,com_time,videotitle from comments_kwb where name='"
							+ myname
							+ "')"
							+ " where r between "
							+ start
							+ " and " + (start + len - 1));
			int i = 1;
			while (rs.next() && i <= len) {
				JSONObject jo = new JSONObject();

				// jo.put("com_videotitle", "�й�Ů�ŵ�42�ε����ݼ�ѵ");
				jo.put("com_content", rs.getString("content"));
				jo.put("com_time", rs.getString("com_time"));
				jo.put("com_videotitle", rs.getString("videotitle"));
				array.put(jo);
				i++;
			}
			System.out.println(array.length());

			rs.close();
			conn.close();
			jobject.put("com_total", j1);
			jobject.put("com_message", array);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobject;
	}

	public static void main(String[] args) {
		myComment_oracle m = new myComment_oracle("kwb", "0");
		JSONObject jj = m.getMyCommentMessage();
		System.out.println(jj.toString());

	}
}
