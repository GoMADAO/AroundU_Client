package com.example.aroundu_client;

import util.Helper;
import util.Server4Emer;
import util.Server4Imp;
import data.Emergency;
import data.Importance;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class NewEmergencyActivity extends Activity {

	Server4Emer server = null;
	boolean showMap = false;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_emergency);
		
		EditText abstr = (EditText) findViewById(R.id.emer_abs_input);
		EditText detail = (EditText) findViewById(R.id.emer_text_input);
		Switch map_on = (Switch) findViewById(R.id.map_on_turn);
		Button send_btn = (Button) findViewById(R.id.emer_send_btn);
	
		
		map_on.setChecked(showMap);//default: hide location
		map_on.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton button, boolean checked) {
				showMap = checked;
			}
			
		});
		
		EditText[] texts = {abstr, detail};
		
		send_btn.setTag(texts);
		send_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				EditText[] store = (EditText[])v.getTag();
				String abstr = store[0].getText().toString();
				String detail = store[1].getText().toString();
				if(!abstr.trim().isEmpty()&&!detail.trim().isEmpty()){
					Emergency emer = new Emergency(detail,abstr);
					emer.mapOn = showMap;
					server = new Server4Emer();
					server.insert(emer,""+Helper.lat,""+Helper.lng);
					Helper.cacheEmergency.add(emer);
					Intent intent = new Intent(NewEmergencyActivity.this, FeedsActivity.class);
					startActivity(intent);
					finish();
				}else{
					Toast.makeText(getBaseContext(), "You can't send empty contents!", Toast.LENGTH_SHORT).show();
				}
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_emergency, menu);
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
