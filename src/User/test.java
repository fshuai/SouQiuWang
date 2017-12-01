package User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.out.println(getInfo("44680"));
	}
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
}
