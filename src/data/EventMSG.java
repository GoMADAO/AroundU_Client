package data;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class EventMSG {
	public String id;
	public String text;
	public String lat, lng;
	public String timestamp;
	
	public EventMSG(JSONObject json) throws JSONException{
		this.id = json.getString("id");
		this.text = json.getString("text");
		this.lat = json.getString("lat");
		this.lng = json.getString("lon");
		this.timestamp = json.getString("time");
	}
	
	public abstract int getType();
}
