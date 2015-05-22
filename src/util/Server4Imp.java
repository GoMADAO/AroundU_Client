package util;

import org.json.JSONException;
import org.json.JSONObject;

import data.EventMSG;
import data.Importance;

public class Server4Imp extends Server{
	private String service ="write";
	@Override
	public void insert(EventMSG msg,String lati, String longi){
JSONObject json = new JSONObject();
		
		Importance i =(Importance)msg;
		JSONObject inner = new JSONObject();
		try {
			json.put("MSG", inner);
			inner.put("type", "importance");
			inner.put("abstract", i.abstr);
			inner.put("text", i.text);
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
	
	public void report(String id){
		
		JSONObject json = new JSONObject();
		try {
			JSONObject inner = new JSONObject();
			inner.put("type", "importance");
			inner.put("id", id);
			json.put("MSG", inner);
			json.put("OP", "report");
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		callGet(json, service);
	}
}
