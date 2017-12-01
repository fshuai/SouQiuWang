import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class TvForecastServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
		response.setContentType("text/html;charset=GBK");
		JSONObject data  = GetConnection.getCon();
		try{
			PrintWriter out = response.getWriter();
			out.print(data);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		/*
		try{
			PrintWriter out = response.getWriter();
			//List<List<String>> data = GetConnection.getCon();
			int tag = 1;
			for(List l:data){
				out.println("channel:");
				for(int i = 0; i < l.size(); i++)
					out.write(l.get(i)+",");
				
			}
			//out.write(data.get(2).get(2));
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	*/
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		   throws ServletException, IOException {
		doGet(request,response);
	}
    
}
