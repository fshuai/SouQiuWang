package MyLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class register_oracle {
	String oraUrl = "jdbc:oracle:thin:@202.114.18.76:1521:myoracle";
	String oraUser = "team";
	String oraPwd = "team";

	/**
	 * ������ 1. create table users_kwb( username varchar(20) primary key, password
	 * varchar(20) not null, gender int, email varchar(20), regtime varchar(20),
	 * birthtime varchar(20), nickname varchar(20) ); ); 2.�޸ĺ������豸�� create
	 * table users_kwb( username varchar(20) primary key, password varchar(20)
	 * not null, gender int, email varchar(20), regtime varchar(20), birthtime
	 * varchar(20), nickname varchar(20), deviceid varchar(30),  ); 
	 * 3.���յı��create table users_kwb( username varchar(20)
	 * primary key, password varchar(20)not null, gender int, email varchar(20),
	 * regtime varchar(20), birthtime varchar(20), nickname varchar(20)
	 *  deviceid varchar(30) );
	 * 
	 * @param name
	 * @param password
	 * @param sex
	 * @param email
	 */
    public static void main(String[]args){
    	new register_oracle("kk", "1111", 0, "1", "2012-02", "aaa");
    }
	public register_oracle(String name, String password, int sex, String email
			,String time,String deviceid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String oraUrl = "jdbc:oracle:thin:@202.114.18.76:1521:myoracle";
		String oraUser = "team";
		String oraPwd = "team";
		ResultSet rs = null;
		try{
		// ���Ȳ�ѯ���ݿ�����û�и��û�
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(oraUrl, oraUser, oraPwd);

		PreparedStatement pstmt = conn
				.prepareStatement("insert into users_kwb values(?,?,?,?,?,?,?,?)");

		pstmt.setString(1, name);
		pstmt.setString(2, password);
		pstmt.setInt(3, sex);
		pstmt.setString(4, email);
		pstmt.setString(5, time);
		pstmt.setString(6, time);//�ó�������==ע������
		pstmt.setString(7,name);//��name==nickname,��ʼ��
		pstmt.setString(8, deviceid);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
