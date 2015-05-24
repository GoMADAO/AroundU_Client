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
	  
	  startService(new Intent(this, GPSService.class));
	  Intent intent = new Intent(this, LoginActivity.class);
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
