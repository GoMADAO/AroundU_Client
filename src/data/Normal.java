package data;

public class Normal extends EventMSG{
	public Normal(String id, String text, String lat, String lng, 
			String timestamp, String topic) {
		super( id,  text,  lat,  lng,  timestamp);
		// TODO Auto-generated constructor stub
		this.topic = topic;
	}
	public String topic;
	public String total_likes;
	

}
