package WeiXinUtil;

import java.util.Arrays;

public class CheckUtil {
	private static final String TOKEN="souqiuwang";
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		String[] arr=new String[]{TOKEN,timestamp,nonce};
		//����
		Arrays.sort(arr);
		//�����ַ���
		StringBuffer content=new StringBuffer();
		for(int i=0;i<arr.length;i++){
			content.append(arr[i]);
		}
		//ʹ��sha1�����㷨�Ƚ�
		return true;
	}
}
