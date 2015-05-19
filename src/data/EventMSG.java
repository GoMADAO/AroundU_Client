package data;

public abstract class EventMSG {
	public String id;
	public String text;
	public String lat, lng;
	public String timestamp;
	
	public EventMSG(String id, String text, String lat, String lng, String timestamp){
		this.id = id;
		this.text = text;
		this.lat = lat;
		this.lng = lng;
		this.timestamp = timestamp;
	}
	
	public abstract int getType();
}
