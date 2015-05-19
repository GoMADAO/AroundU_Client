package com.example.aroundu_client;

import org.json.JSONException;
import org.json.JSONObject;

import data.EventMSG;
import data.Normal;
import util.GPSService;
import util.Helper;
import util.Server;
import util.Server4Normal;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  
	  
	  StrictMode.ThreadPolicy policy = new StrictMode.
			  ThreadPolicy.Builder().permitAll().build();
			  StrictMode.setThreadPolicy(policy); 
	  
	  
	  setContentView(R.layout.activity_main);
	  System.out.println(Helper.getRange());
	  Button login = (Button) findViewById(R.id.btn);
	  
	  startService(new Intent(MainActivity.this, GPSService.class));
	  
//	  System.out.println("lat:"+Helper.lat+" lng:"+Helper.lng);
//	  Server server = new Server4Normal();
//	  EventMSG msg= new Normal("first text", null);
//	
//	  server.insert(msg,"40.8438597", "-73.936510314");
	
	  login.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			gotoFeeds(null);
		}
	});
	}
	public void gotoFeeds(View v){
		Intent intent = new Intent(this, FeedsActivity.class);
		
		//TODO check login
		/**
		 *  facebook login
		 *  
		 *  get user_id & user_name & isBlock & isActive & (lat,lng)
		 */
		System.out.println("lat:"+Helper.lat+" lng:"+Helper.lng);
		//fake data
		String user_id = "yf2338";
		String user_name = "AlexandraVon";
		boolean isBlock = false;
		boolean isActive = true;
		String lat = "40.8438597";
		String lng = "-73.9365103,14";
		
//		Server server = new Server();
//		String result = server.select("40.8438597", "-73.9365103,14");
//		if(result!=null)
//			System.out.println(result);
		
		Helper.USERID = user_id;
		Helper.USERNAME = user_name;
		Helper.isBLOCK = isBlock;
		Helper.isACTIVE = isActive;
		
		intent.putExtra("lat", lat);
		intent.putExtra("lng", lng);
		
		
		startActivity(intent);
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	  // Inflate the menu; this adds items to the action bar if it is present.
	  getMenuInflater().inflate(R.menu.main, menu);
	  return true;
	}
		
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	  // Handle action bar item clicks here. The action bar will
	  // automatically handle clicks on the Home/Up button, so long
	  // as you specify a parent activity in AndroidManifest.xml.
	  int id = item.getItemId();
	  if (id == R.id.action_settings) {
	    return true;
	  }
	  return super.onOptionsItemSelected(item);
	}
}
