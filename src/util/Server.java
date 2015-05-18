package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;







public class Server {
	
	
	public void insert(){
		
	}
	
	public void run(){
		select();
	}
	
	public String select(){
		String url = "http://129.236.235.212:8080/finalproj/read";
		URL obj ;
		String result=null;
		HttpURLConnection con = null;
		try {
			obj = new URL(url);
			 con =  (HttpURLConnection) obj.openConnection();
			
			
			int responseCode = con.getResponseCode();
			System.out.println(responseCode);
			result =convertStreamToString( con.getInputStream());
			
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		        sb.append(line + "\n");
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        is.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		return sb.toString();
		}
}
