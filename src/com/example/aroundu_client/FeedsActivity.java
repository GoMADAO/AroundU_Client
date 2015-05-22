package com.example.aroundu_client;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import data.Emergency;
import data.Importance;
import data.Normal;
import util.Helper;
import util.Server;
import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;




public class FeedsActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {
	
	public static ListView listview;
	public Spinner dropdown;
	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	private List<data.Emergency> emrList=null;
	private List<data.Importance> impList=null;
	private List<data.Normal> norList=null;
	private List<String> dropdownList=null;
	private ArrayList<data.EventMSG> events=null;
	Server server =null;
	
	private boolean creation = true;
	
	private void save2List(JSONObject json) throws JSONException{
		JSONArray nors = json.getJSONArray("normal");
		JSONArray imps = json.getJSONArray("importance");
		JSONArray emes = json.getJSONArray("emergency");
		JSONArray tops = json.getJSONArray("topiclist");
		emrList = new ArrayList<data.Emergency>();
		impList = new ArrayList<data.Importance>();
		norList = new ArrayList<data.Normal>();
		
		dropdownList = new ArrayList<String>();
		dropdownList.add("All Topics");
		
		if(nors.length()!=0){
			for (int i=0;i<nors.length();i++)
				norList.add(new Normal(nors.getJSONObject(i)));
		}
		
		if(imps.length()!=0){
			for (int i=0;i<imps.length();i++)
				impList.add(new Importance(imps.getJSONObject(i)));
			
		}
		
		if(emes.length()!=0){
			for (int i=0;i<emes.length();i++)
				emrList.add(new Emergency(emes.getJSONObject(i)));
		}
		
		System.out.println("gmf:"+tops.length());
		if(tops.length()!=0){
			for(int i=0;i<tops.length();i++){
				System.out.println("gmf:"+tops.getString(i).toString());
				dropdownList.add(tops.getString(i).toString());
			}
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feeds);
		
		Intent intent = getIntent();
		
		String user_id = intent.getStringExtra("user_id");
		String user_name = intent.getStringExtra("user_name");
		String photo_url = intent.getStringExtra("photo_url");
		
		Helper.USERID = user_id;
		Helper.USERNAME = user_name;
		Helper.PATH = photo_url;
		
		Toast.makeText(getBaseContext(), "id: "+user_id+" name: "+user_name+" photo: "+photo_url, Toast.LENGTH_LONG).show();
		
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		
		server = new Server();
		
		//server.select("40.8438597", "-73.9365103,14");
		try {
			JSONObject json = new JSONObject(server.select(""+Helper.lat, ""+Helper.lng));
			System.out.println(json.toString());
			save2List(json);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		events = new ArrayList<data.EventMSG>();
		
		for(Emergency emr:emrList){
			if (emr!=null) events.add(emr);
		}
		for(Importance imp:impList){
			if (imp!=null) events.add(imp);
		}
		
		listview = (ListView) findViewById(R.id.feedList);
		dropdown = (Spinner) findViewById(R.id.topic_dropdown);
		
		ArrayAdapter<String> dropdownAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dropdownList);
		dropdown.setAdapter(dropdownAdapter);
		dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				String curTopic = parent.getItemAtPosition(position).toString();
				if(listview.getAdapter()!=null){
					events.clear();
					for(Emergency emr:emrList){
						if (emr!=null) events.add(emr);
					}
					for(Importance imp:impList){
						if (imp!=null) events.add(imp);
					}
				}
				if (curTopic.equals("All Topics")){
					for(Normal norm:norList){
						if (norm!=null) events.add(norm);
					}
				}
				else{
					for(Normal norm:norList){
						if (norm!=null && norm.topic.equals(curTopic)) events.add(norm);
					}
				}
				if(listview.getAdapter()!=null) ((ArrayAdapter) listview.getAdapter()).notifyDataSetChanged();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		
		feedAdapter feedadapter = new feedAdapter(this, R.id.row, events, new BtnClickListener(){
			@Override
			public void onBtnClick() {
				((ArrayAdapter) listview.getAdapter()).notifyDataSetChanged();
			}
		});
		
		listview.setAdapter(feedadapter);
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						PlaceholderFragment.newInstance(position + 1)).commit();
		
		
		if(creation){
			creation = false;
		}else{
			Intent i ;
			switch(position){
			case 0:
				System.out.println("Got 0");
				break;
			case 1:
				System.out.println("Got 1");
				i = new Intent(this, SettingActivity.class);
				startActivity(i);
				finish();
				break;
			case 2:
				final String[] btnlist = new  String[] {"Moment", "Announcement", "Emergency" };
				System.out.println("Got 2");
				new  AlertDialog.Builder(this)  
				.setTitle("New..." )  
				.setItems(btnlist, new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialogue, int which) {
							Intent intent = null;
//							Toast.makeText(getBaseContext(), ""+which, Toast.LENGTH_LONG).show();
							if (which==0){
								intent = new Intent(FeedsActivity.this,NewNormalActivity.class);
							}
							else if(which==1){
								intent = new Intent(FeedsActivity.this,NewImportanceActivity.class);
							}
							else if (which==2){
								intent = new Intent(FeedsActivity.this,NewEmergencyActivity.class);
							}
							startActivity(intent);
						}
				})
				.show();  
				break;
			}
		}
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.feeds, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_feeds,
					container, false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((FeedsActivity) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
	}

}
