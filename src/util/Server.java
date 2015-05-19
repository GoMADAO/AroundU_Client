package util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import data.EventMSG;








public class Server {
	
	
	public void insert(EventMSG msg,String lati, String longi){
		
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
	
	protected static String callGet(JSONObject data, String service){
		String result=null;
		StringBuffer url =  new StringBuffer();
		url.append(Helper.IP).append("/").append(service).append("?data=");
		url.append(data.toString());
		
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
