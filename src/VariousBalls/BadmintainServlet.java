package VariousBalls;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BadmintainServlet extends HttpServlet {
	String baseAddress="http://202.114.18.77:8885";//��ë��Ļ���ַ�����仯
	 String videourl;
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		try{
			PrintWriter out = response.getWriter();
			Connect_Badmintain j=new Connect_Badmintain();
			List<videoMessage>l=j.getInfo();
			String[]str=new String[l.size()];
			StringBuffer sb = new StringBuffer();//���ݻ���  
         //�����ʽ
          for(int i=0;i<l.size();i++){
        	  String videoID=l.get(i).getVideoID();
         	  String imageUrl=l.get(i).getImageURl();//ͼƬ��ַ�������ͼƬ������Linuxϵͳ�£���http://202.114.18.70
     	        //�ʽ�ͼƬUrl����Linux�µ�Url
         	 imageUrl=imageUrl.replace("\\", "/");//����/Images//Image/Video_6461/frame45.jpg����
             //http://202.114.18.70/Volleyball_Images//Image/Video_6461/frame45.jpg
             imageUrl=baseAddress+imageUrl; 
	             //ע����ڸ������ſ���Ƶ����ҳ����Ҫ����ת���ɶ�Ӧ����Ƶ���ŵ�ַ      
         	
	            sb.append(videoID+","+imageUrl+","+l.get(i).getVideoTitle()+","+l.get(i).getVideoURL()+","+l.get(i).getVideoOrigin());
	            sb.append(';');///���ݷֺ�������          
     		    //sb.append("\n");       		                        
          }           
          out.write(new String(sb));  
          out.flush();  
          out.close();//�ر�  
		}catch(Exception e){	
		}	
	}
	  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	        doGet(request, response);  
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