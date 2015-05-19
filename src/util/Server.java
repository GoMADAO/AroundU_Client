package util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;

import org.json.JSONException;
import org.json.JSONObject;








public class Server {
	
	
	public void insert(){
		
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
		
		
		StringBuffer url =  new StringBuffer("http://");
		url.append(Helper.IP).append(":8080/finalproj/read?data=");
		url.append(json.toString());
		
		
		System.out.println(url.toString());
		
		URL obj ;
		String result=null;
		HttpURLConnection con = null;
		try {
			
			obj = new URL(url.toString());
			 con =  (HttpURLConnection) obj.openConnection();
			int responseCode = con.getResponseCode();
			
			System.out.println(responseCode);
			result =convertStreamToString( con.getInputStream());
			
			
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
