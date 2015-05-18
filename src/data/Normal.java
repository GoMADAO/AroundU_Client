package data;

public class Normal extends EventMSG{
	public Normal(String id, String text, String lat, String lng, 
			String timestamp, String topic, String likes ) {
		super( id,  text,  lat,  lng,  timestamp);
		this.topic = topic;
		this.likeNum = likes;
	}
	public String topic;
	public String likeNum;
	
}
