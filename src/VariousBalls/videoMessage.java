package VariousBalls;

public class videoMessage {
	private String videoTitle;//��Ƶ����
	 private String videoURL;//������ַ
	 private String imageURl;//ͼ���ַ
	 private String videoID;//���ʾ�ͷ
	 String videoorigin;//������Դ
	 public videoMessage(){
		 
	 }
	  public videoMessage(String videoID,String videoTitle,String videoURL,String imageURl,String videoorigin ){
		  this.videoID=videoID;
		  this.imageURl=imageURl;
		  this.videoTitle=videoTitle;
		  this.videoURL=videoURL;
		   this.videoorigin=videoorigin;
		 
	  }
	public String getVideoTitle() {
		return videoTitle;
	}
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	public String getVideoURL() {
		return videoURL;
	}
	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}
	public String getImageURl() {
		return imageURl;
	}
	public void setImageURl(String imageURl) {
		this.imageURl = imageURl;
	}
	public String getVideoID() {
		return videoID;
	}
	public void setVideoTape(String videoID) {
		this.videoID = videoID;
	}
	public String getVideoOrigin() {
		return videoorigin;
	}
	public void setVideoOrigin(String videoorigin) {
		this.videoorigin = videoorigin;
	}
 
}
