package MyLogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyStore extends HttpServlet {
	private String name;
	private String videoid;
	private String videotag;
	private String videotitle;
	private String imageurl;
	private String videourl;
	
	private String testid;
	
	private String testresult;
	

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the getpost method");
		out.println("server-name-->"+name);
		out.println("server-videoid-->"+videoid);
		out.println("server-videotag-->"+videotag);
		out.println("server-videotitle-->"+videotitle);
		out.println("server-imageurl-->"+imageurl);
		out.println("server-videourl-->"+videourl);
		out.println("server-testid-->"+testid);
		out.println("server-testresult-->"+testresult);
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//dopost函数调试成功
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		//Connection conn=null; 
		//PreparedStatement stmt=null;
		name=request.getParameter("name");
		videoid=request.getParameter("videoid");	
	    videotag=request.getParameter("videotag");			
		videotitle=request.getParameter("videotitle");
	    imageurl=request.getParameter("imageurl");
	    videourl=request.getParameter("videourl");
		videotitle=new String(videotitle.getBytes("iso-8859-1"),"utf-8");
		myStore_oracle so=new myStore_oracle(name, videoid, videotag, videotitle, imageurl, videourl);
		testid=so.getid();
		testresult=so.geterr();
		PrintWriter out = response.getWriter();
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
