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

import org.json.JSONArray;
import org.json.JSONObject;

public class getComment extends HttpServlet {

	 //根据传入进来的参数videoID和videotag，来获取评论

   JSONObject array=new JSONObject();
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
		    String videoid =request.getParameter("videoid");
			String videotag=request.getParameter("videotag");
			String start=request.getParameter("start");
			getComment_oracle gc=new getComment_oracle(videoid,videotag,start);
			array=gc.getCommentMessage();
			PrintWriter out=response.getWriter();
			out.print(array);
			out.flush(); 
		    out.close();
	}
	
	public void init(ServletConfig config) throws ServletException {
		// Put your code here
		super.init(config);
	}


}
