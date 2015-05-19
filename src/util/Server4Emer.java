package util;


import org.json.JSONException;
import org.json.JSONObject;

import data.Emergency;
import data.EventMSG;
import data.Importance;

public class Server4Emer extends Server{
	private String service = "Writer";
	@Override
	public void insert(EventMSG msg,String lati, String longi){
		JSONObject json = new JSONObject();
		
		Emergency e = (Emergency)msg;
		JSONObject inner = new JSONObject();
		try {
			json.put("MSG", inner);
			inner.put("type", "emergency");
			inner.put("abstract", e.abstr);
			inner.put("detail", e.text);
			inner.put("map", (e.mapOn)?1:0);
			inner.put("lat", lati);
			inner.put("lng", longi);
			inner.put("userid", Helper.USERID);
			json.put("OP", "insert");
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		callGet(json, service);
		
		
	}
	
	public void report(EventMSG msg){
		Emergency e = (Emergency)msg;
		JSONObject json = new JSONObject();
		try {
			JSONObject inner = new JSONObject();
			inner.put("type", "normal");
			inner.put("id", e.id);
			json.put("MSG", inner);
			json.put("OP", "like");
			
			
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
	}
	
	
}
