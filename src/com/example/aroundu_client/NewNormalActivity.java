package com.example.aroundu_client;

import data.Normal;
import util.Helper;
import util.Server4Normal;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewNormalActivity extends Activity {

	Server4Normal server = new Server4Normal();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_normal);
		
		EditText content = (EditText) findViewById(R.id.norm_input);
		Button sendBtn = (Button) findViewById(R.id.norm_send_btn);
		sendBtn.setTag(content);
		
		sendBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				EditText tmp = (EditText) v.getTag();
				String msg = tmp.getText().toString();
				if(!msg.trim().isEmpty()){
					Normal normal = new Normal(msg,null);
					server.insert(normal,""+Helper.lat, ""+Helper.lng);
				
					Toast.makeText(getBaseContext(), "New moment sent!", Toast.LENGTH_SHORT).show();
		
					Intent intent = new Intent(NewNormalActivity.this, FeedsActivity.class);
					startActivity(intent);
				
					finish();
				}else{
					Toast.makeText(getBaseContext(), "Moment can't be empty!", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_normal, menu);
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
