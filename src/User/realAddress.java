package User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import sun.net.www.protocol.http.HttpURLConnection;

public class realAddress {
	 StringBuffer sb;
	 String tem;
	 String[] str;
	 int start;
	 int end;
	static String baseURL = "http://192.168.0.115/youku_address.php?addr=";

	public realAddress() {
		// TODO Auto-generated constructor stub
	}

	// http://192.168.0.128/youku_address.php?addr=http://v.youku.com/v_show/id_XMTUxNTMzNjY5Ng==.html?from=y1.7-1.2
	// ��������
	public static void main(String[] args) {
		System.out
				.println(new realAddress().getVideoUrl("http://v.youku.com/v_show/id_XMTUxNjA3MDgyNA==.html?from=y1.7-1.2"));
	}

	public  String getVideoUrl(String videoURL) {
		// TODO Auto-generated method stub
		String t = "";// ע������Ҫ���þֲ���������������Ƴ�ȫ�ֱ�������Ϊǰ������������һ�λỰ�У�������Ϣ���ܱ�����
		try {
			sb=new StringBuffer();
			HttpURLConnection con = null;
			URL url = new URL(baseURL + videoURL);
			con = (HttpURLConnection) url.openConnection();
			InputStreamReader in = new InputStreamReader(con.getInputStream(),
					"utf-8");
			BufferedReader buf = new BufferedReader(in);
			while ((tem = buf.readLine()) != null) {
				sb.append(tem);
				sb.append('\n');
			}
			in.close();
			con.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String result=new String(sb);
		start = result.indexOf("3gp");
		start = result.indexOf("(", start);
		end = result.indexOf(")", start);
		result = result.substring(start + 1, end);
		str = result.split("\n");
		for (String strAdd : str) {
			if (strAdd.length() < 10)
				continue;
			start = 0;
			start = strAdd.indexOf("=>", start);// ��ʼƥ��λ��
			if (start < 0)
				continue;
			start += 3;
			end = strAdd.indexOf(" ", start);
			if (end < 0)
				continue;
			t= strAdd.substring(start, end);// /////��Ƶ��ַ
			
		}
		return t;// ������û�пո��
	}
}
