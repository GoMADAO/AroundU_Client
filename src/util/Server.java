package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
		try {
			json.put("range", Helper.getRange());
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
	
	protected static String convertStreamToString(InputStream is) {
		/*
		 * To convert the InputStream to String we use the BufferedReader.readLine()
		 * method. We iterate until the BufferedReader return null which means
		 * there's no more data to read. Each line will appended to a StringBuilder
		 * and returned as String.
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		
		String line = null;
		try {
		    while ((line = reader.readLine()) != null) {
		        sb.append(line).append("\n");
		    }
		    
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        is.close();
		        reader.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		return sb.toString();
		}
}
