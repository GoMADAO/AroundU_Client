package util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class Server4Normal extends Server{
	
	@Override
	public void insert(){
		
	}
	
	public void dislike(String id){
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
		
		StringBuffer sb = new StringBuffer();
		send(sb.toString());
		
		
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
		StringBuffer sb = new StringBuffer();
		send(sb.toString());
		
	}
	
	private void send(String url){
		HttpURLConnection con = null;
		URL obj ;
		//String rt = null;
		try {
			//TODO url
			obj = new URL(url);
			con =  (HttpURLConnection) obj.openConnection();
			//rt = convertStreamToString(con.getInputStream());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			con.disconnect();
		}
		//return rt;
	}
}
