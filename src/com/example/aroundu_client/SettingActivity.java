package com.example.aroundu_client;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import util.Helper;
import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

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
	private ImageView userPhoto;
	private Bitmap userPic;
	private TextView username;
	private Switch myblock;
	private SeekBar myrange;
	private TextView rangeText;
	
	private boolean creation = true;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		
		
		username = (TextView) findViewById(R.id.user_name);
		username.setText(Helper.USERNAME);
		
		userPhoto = (ImageView) findViewById(R.id.user_photo);
		userPic = getPhotoFromURL(Helper.PATH);
		userPhoto.setImageBitmap(userPic);
		
		myblock = (Switch)findViewById(R.id.block_switch);
		myblock.setChecked(Helper.isBLOCK);
		myblock.setOnCheckedChangeListener(
			new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView,
					     boolean isChecked) {

					if(isChecked){
					     System.out.println("Switch is currently ON");
					     Helper.isBLOCK=true;
					     Toast.makeText(getApplicationContext()
									, "Show importance and emergency"
									, Toast.LENGTH_SHORT).show();
					}else{
					    	System.out.println("Switch is currently OFF");
					    	Helper.isBLOCK=false;
					    	Toast.makeText(getApplicationContext()
									, "Block importance and emergency"
									, Toast.LENGTH_SHORT).show();
					}

				}
			});
		myblock.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		myrange = (SeekBar) findViewById(R.id.range_seekbar);
		myrange.setProgress(Helper.range);
		rangeText = (TextView) findViewById(R.id.range_text);
		rangeText.setText(myrange.getProgress()+"/"+myrange.getMax());
		myrange.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			int progressChanged = 0;

			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
				progressChanged = progress;
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				if (progressChanged ==0){
					Toast.makeText(getApplicationContext()
							, "You don't want see anything? T_T"
							, Toast.LENGTH_SHORT).show();
					progressChanged = Helper.range;
					seekBar.setProgress(progressChanged);
					rangeText.setText(Helper.range+"/"+myrange.getMax());
					
				}else{
					rangeText.setText(progressChanged+"/"+seekBar.getMax());
					Helper.range = progressChanged;
				}
				
				
			}
		});
		
		
	}

	private Bitmap getPhotoFromURL(String url){
//		Bitmap mIcon11 = null;
//		try {
//			InputStream in = new java.net.URL(url).openStream();
//			mIcon11 = BitmapFactory.decodeStream(in);
//		} catch (Exception e) {
//			Log.e("Error", e.getMessage());
//			e.printStackTrace();
//		}
//		return mIcon11;
		Bitmap bm = null;
		HttpURLConnection con = null;
		try {
			URL link = new URL(url);
			con = (HttpURLConnection) link.openConnection();
			con.setDoInput(true);
			InputStream is = con.getInputStream();
			bm = BitmapFactory.decodeStream(is);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			con.disconnect();
		}
		return bm;
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
				i = new Intent(this, FeedsActivity.class);
				startActivity(i);
				finish();
				break;
			case 1:
				System.out.println("Got 1");
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
							if (which==0){
								intent = new Intent(SettingActivity.this,NewNormalActivity.class);
							}
							startActivity(intent);
						}
				})
				.show();  
				break;
			}
		}
		
		System.out.println("gmf:"+position);
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
			getMenuInflater().inflate(R.menu.setting, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_setting,
					container, false);
			
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((SettingActivity) activity).onSectionAttached(getArguments()
					.getInt(ARG_SECTION_NUMBER));
		}
	}

}
