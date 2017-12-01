package MyLogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Store extends HttpServlet { 
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
		    String name=request.getParameter("name");
		    String videoid=request.getParameter("videoid");	
			String videotag=request.getParameter("videotag");			
			String content=request.getParameter("content");
			String videotitle=request.getParameter("videotitle");
			content=new String(content.getBytes("iso-8859-1"),"utf-8");
			videotitle=new String(videotitle.getBytes("iso-8859-1"),"utf-8");
			store_oracle s=new store_oracle(name, videoid, videotag, content,videotitle);
			PrintWriter out=response.getWriter();
			out.println(name+videoid+videotag+content+videotitle);
			out.flush(); 
		    out.close();
	}
	
	public void init(ServletConfig config) throws ServletException {
		// Put your code here
		super.init(config);
	}


}
