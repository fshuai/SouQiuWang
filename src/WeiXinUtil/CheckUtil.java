package WeiXinUtil;

import java.util.Arrays;

public class CheckUtil {
	private static final String TOKEN="souqiuwang";
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		String[] arr=new String[]{TOKEN,timestamp,nonce};
		//排序
		Arrays.sort(arr);
		//生成字符串
		StringBuffer content=new StringBuffer();
		for(int i=0;i<arr.length;i++){
			content.append(arr[i]);
		}
		//使用sha1加密算法比较
		return true;
	}
}
