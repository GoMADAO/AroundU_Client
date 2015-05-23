package data;

import org.json.JSONException;
import org.json.JSONObject;

import util.Helper;

public abstract class EventMSG {
	public String id;
	public String text;
	public String lat, lng;
	public String timestamp;
	public String userid;
	public boolean isCached = false;
	
	public EventMSG(JSONObject json) throws JSONException{
		this.id = json.getString("id");
		this.text = json.getString("text");
		this.lat = json.getString("lat");
		this.lng = json.getString("lon");
		this.timestamp = json.getString("time");
		this.userid = json.getString("userid");
	}
	
	public EventMSG(String text, String abstr){
		this.text = text;
		this.userid = Helper.USERID;
	}
	
	
	
	public abstract int getType();
}
