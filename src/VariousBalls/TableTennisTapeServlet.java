package VariousBalls;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class TableTennisTapeServlet extends HttpServlet {
	private int videoID; //传入进来的参数videoID
    private JSONObject jo=new JSONObject();//用于保存精彩镜头中的数据和用于保存视频预览中的数据
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
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
		try{
			InputStream  in =request.getInputStream();
			DataInputStream obj=new DataInputStream(in);
			videoID=obj.readInt();
			obj.close();
			TableTennis_Tape_Preview_Oracle conn=new TableTennis_Tape_Preview_Oracle(videoID);
			jo=conn.getInfo();
		    PrintWriter out = response.getWriter();
		    out.print(jo);
		   out.flush(); 
		   out.close();
		 }catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init(ServletConfig config) throws ServletException {
		// Put your code here
		super.init(config);
	}

}
