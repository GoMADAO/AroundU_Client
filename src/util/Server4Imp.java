package util;

import org.json.JSONException;
import org.json.JSONObject;

import data.EventMSG;
import data.Importance;

public class Server4Imp extends Server{
	private String service ="Writer";
	@Override
	public void insert(EventMSG msg,String lati, String longi){
JSONObject json = new JSONObject();
		
		Importance i =(Importance)msg;
		JSONObject inner = new JSONObject();
		try {
			json.put("MSG", inner);
			inner.put("type", "importance");
			inner.put("abstract", i.abstr);
			inner.put("detail", i.text);
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
	
	public void report(){
		
	}
}
