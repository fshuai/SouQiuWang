package BasketBall;

public class videoMessage {
 private String videoURL;//视屏地址
 private String imageURl_Title;//图像地址he视频标题
 private String videoID;//精彩镜头
 private String videoorigin;
 public videoMessage(){
	 
 }
  public videoMessage(String videoID,String videoURL,String imageURl_Title,String videoorigin ){
	  this.videoID=videoID;
	  this.imageURl_Title=imageURl_Title;
	  this.videoURL=videoURL;
	  this.videoorigin=videoorigin;
	 
  }
public String getImageUrl_Title() {
	return imageURl_Title;
}
public void setImageUrl_Title(String videoTitle) {
	this.imageURl_Title = videoTitle;
}
public String getVideoURL() {
	return videoURL;
}
public void setVideoURL(String videoURL) {
	this.videoURL = videoURL;
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
