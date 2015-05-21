package com.example.aroundu_client;

import util.Helper;
import util.Server4Imp;
import data.Importance;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewImportanceActivity extends Activity {

	Server4Imp server = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_importance);
		
		EditText abstr = (EditText) findViewById(R.id.imp_abs_input);
		EditText detail = (EditText) findViewById(R.id.imp_text_input);
		Button send_btn = (Button) findViewById(R.id.imp_send_btn);
		
		EditText[] texts = {abstr, detail};
		
		send_btn.setTag(texts);
		send_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				EditText[] store = (EditText[])v.getTag();
				String abstr = store[0].getText().toString();
				String detail = store[1].getText().toString();
				if(!abstr.trim().isEmpty()&&!detail.trim().isEmpty()){
					Importance imp = new Importance(detail,abstr);
					server = new Server4Imp();
					server.insert(imp,""+Helper.lat,""+Helper.lng);
					Intent intent = new Intent(NewImportanceActivity.this, FeedsActivity.class);
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
		getMenuInflater().inflate(R.menu.new_importance, menu);
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
