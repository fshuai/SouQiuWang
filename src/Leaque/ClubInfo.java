package Leaque;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClubInfo {
 public static void main(String[]args) throws Exception{
	 String cmd = "cmd /c start python E:/TV_SearchAndRecommand/MyEclipse1/TextIdentify/src/IdentifyText.py";// pass  
     try {  
         Process p = Runtime.getRuntime().exec(cmd);   
	 
         // 构造一个写出流并指定输出文件保存路径  
         BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream())); 
         FileWriter fw = new FileWriter(new File("E：//2.txt"));  
           
         String line = null;  
           
         // 循环读取  
         while ((line = reader.readLine()) != null) {  
          // 循环写入  
          fw.write(line + "\n");  
         }  
           
         // 刷新输出流  
         fw.flush();  
           
         // 关闭输出流  
         fw.close();  
         p.waitFor(); 
//		 p.getOutputStream().close();  
     }catch (Exception e) {
		// TODO: handle exception
	}
//	 Process  pr = Runtime.getRuntime().exec("E:/TV_SearchAndRecommand/MyEclipse1/TextIdentify/src/exe.bat");
//	 String[] commands = {"cmd", "/c","E:/TV_SearchAndRecommand/MyEclipse1/TextIdentify/src/exe.bat"};
//	 Process  pr = Runtime.getRuntime().exec(commands);
//	 int exitVal = pr.waitFor();
//     System.out.println("Exited with error code " + exitVal);
//     String cmd = "cmd /c start python E:/TV_SearchAndRecommand/MyEclipse1/TextIdentify/src/IdentifyText.py";// pass  
//     try {  
//         Process ps = Runtime.getRuntime().exec(cmd);  
//         ps.waitFor();  
//     } catch (IOException ioe) {  
//         ioe.printStackTrace();  
//     }  
//     catch (InterruptedException e) {  
//         // TODO Auto-generated catch block  
//         e.printStackTrace();  
//     }  
//     System.out.println("child thread donn");  
//	 getInfo();
 }
  public static String getInfo(){
	  StringBuffer sb=new StringBuffer();
	  String cmd = "cmd /c start python E:/TV_SearchAndRecommand/MyEclipse1/TextIdentify/src/IdentifyText.py";// pass  
	     try {  
	         Process ps = Runtime.getRuntime().exec(cmd);  
	         ps.waitFor();  
	     } catch (IOException ioe) {  
	         ioe.printStackTrace();  
	     }  
	     catch (InterruptedException e) {  
	         // TODO Auto-generated catch block  
	         e.printStackTrace();  
	     }  
	     System.out.println("child thread donn");  
	     try {
	       InputStreamReader isr=new InputStreamReader(new FileInputStream("E://2.txt"),"utf-8");
		   BufferedReader br= new BufferedReader(isr);
		   String line="";
		 
	 	    while((line=br.readLine())!=null){
	 	      sb.append(line+"\n");				 			     
	 	    }	
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     System.out.println(new String(sb));
		return new String(sb) ; 
	  
	  
  }      
 
}
