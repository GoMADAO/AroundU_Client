package util;



import org.json.JSONException;
import org.json.JSONObject;

import data.EventMSG;
import data.Normal;

public class Server4Normal extends Server{
	
	private String service = "Writer";
	
	@Override
	public void insert(EventMSG msg, String lati, String longi){
		System.out.println("right call");
		JSONObject json = new JSONObject();
		JSONObject inner = new JSONObject();
		Normal n = (Normal)msg;
		
		try {
			json.put("OP", "insert");
			inner.put("text", n.text);
			inner.put("userid", Helper.USERID);
			inner.put("lat", lati);
			inner.put("lng", longi);
			inner.put("type", "normal");
			json.put("MSG", inner);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		callGet(json, service);
		
	}
	
	public void dislike(String id){
		//Normal n =(Normal)msg;
		JSONObject json = new JSONObject();
		try {
			JSONObject inner = new JSONObject();
			inner.put("type", "normal");
			inner.put("id", id);
			json.put("MSG", inner);
			json.put("OP", "dislike");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		callGet(json, service);
		
		
	}
	
	public void like(String id){
		
		JSONObject json = new JSONObject();
		try {
			JSONObject inner = new JSONObject();
			inner.put("type", "normal");
			inner.put("id", id);
			json.put("MSG", inner);
			json.put("OP", "like");
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		callGet(json, service);
	}
	
	
}
