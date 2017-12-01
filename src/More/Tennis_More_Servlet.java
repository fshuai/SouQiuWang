package More;

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

public class Tennis_More_Servlet extends HttpServlet {
	private int start; //��������Ĳ���videoID 
	   private JSONObject jo=new JSONObject();//���ڱ��������Ƶ��Ϣ�����е�����
		public void destroy() {
			super.destroy(); // Just puts "destroy" string in log
	
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
				getMore_Tennis conn=new getMore_Tennis();
				
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
