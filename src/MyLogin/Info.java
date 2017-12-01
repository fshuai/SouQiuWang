package MyLogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class Info extends HttpServlet {

	 //根据传入进来的参数videoID和videotag，来获取评论
	
	   String oraUrl="jdbc:oracle:thin:@202.114.18.96:1521:orcl";		
	   String oraUser="snooker";  
	   String oraPwd="snooker";  
	   JSONObject jo=new JSONObject();
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
			    Connection conn=null; 
			    PreparedStatement stmt=null;
			    String myname =request.getParameter("name");
				myname=new String(myname.getBytes("iso-8859-1"),"utf-8");				
			   Info_oracle gc=new Info_oracle(myname);
				jo=gc.getInfo();
				PrintWriter out=response.getWriter();
				out.print(jo);
				out.flush(); 
			    out.close();
		}
		
		public void init(ServletConfig config) throws ServletException {
			// Put your code here
			super.init(config);
		}


	}
