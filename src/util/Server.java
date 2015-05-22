package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import data.EventMSG;








public class Server {
	
	
	public void insert(EventMSG msg,String lati, String longi){
		System.out.println("wrong");
	}
	
	public String select(String lati, String longi){
		
		JSONObject json = new JSONObject();
		String r = Float.toString(Helper.getRange());
		try {
			json.put("range", r);
			json.put("lon", longi);
			json.put("lat", lati);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		String result = callGet( json, "read");

		return result;
	}
	
	public void createUser(){
		JSONObject json = new JSONObject();
		try {
			json.put("userid", Helper.USERID);
			json.put("OP", "insert");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		callGet(json, "User");
	}
	
	public void userBlock(){
		JSONObject json = new JSONObject();
		try {
			json.put("userid", Helper.USERID);
			json.put("OP", "block");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		callGet(json, "User");
	}
	
	
	public void userUnBlock(){
		JSONObject json = new JSONObject();
		try {
			json.put("userid", Helper.USERID);
			json.put("OP", "unblock");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		callGet(json, "User");
	}
	
	
	public void getUser(){
		JSONObject json = new JSONObject();
		try {
			json.put("userid", Helper.USERID);
			json.put("OP", "select");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 String output = callGet(json, "User");
		 try {
			JSONObject rt= new JSONObject(output);
			Helper.isACTIVE = rt.getBoolean("isactive");
			Helper.isBLOCK = rt.getBoolean("isblock");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	
	protected static String callGet(JSONObject data, String service){
		String result=null;
		StringBuffer url =  new StringBuffer();
		url.append(Helper.IP).append("/").append(service).append("?data=");
		try {
			url.append(URLEncoder.encode(data.toString(), "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("URL:"+url.toString());
		
		URL obj ;
		int responseCode = 0;
		HttpURLConnection con = null;
		try {
			
			obj = new URL(url.toString());
			 con =  (HttpURLConnection) obj.openConnection();
			 responseCode = con.getResponseCode();
			
			System.out.println(responseCode);
			result =convertStreamToString( con.getInputStream());
			
			System.out.println(result);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			con.disconnect();
		}
		
		return result;
		
	}
	
	
	protected static String convertStreamToString(InputStream is) throws IOException  {
		int i;
		StringBuffer sb = new StringBuffer();
		while((i = is.read()) != -1){
			sb.append((char)i);
		}
		

	    return sb.toString();
		
	}
		
}
