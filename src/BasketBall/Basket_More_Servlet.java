package BasketBall;

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

import More.getMore_TableTennis;

public class Basket_More_Servlet extends HttpServlet {

	 private int start; //传入进来的参数videoID
	   
	   private JSONObject jo=new JSONObject();//用于保存更多视频信息内容中的数据
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
				start=obj.readInt();
				obj.close();
				getMore_BasketBall conn=new getMore_BasketBall();
				
				jo= conn.getMessage(start);
			    PrintWriter out = response.getWriter();
			    out.print(jo);
			    out.flush(); 
			    out.close();
			 }catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		public void init(ServletConfig config) throws ServletException {
			// Put your code here
			super.init(config);
		}
}
