package com.example.aroundu_client;


import util.Helper;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class EmgDetailsActivity extends FragmentActivity implements OnMapReadyCallback {
	
	private String detail;
	private Double lat;
	private Double lng;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emg_details);
		MapFragment mapFragment = (MapFragment) getFragmentManager()
			    .findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
		Intent intent = getIntent();
		detail = intent.getStringExtra("emer_details");
		lat = Double.parseDouble(intent.getStringExtra("emer_lat"));
		lng = Double.parseDouble(intent.getStringExtra("emer_lng"));
		
		TextView text = (TextView) findViewById(R.id.emergency_detial_text);
		text.setText(detail);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.emg_details, menu);
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

	@Override
	public void onMapReady(GoogleMap googleMap) {
		// TODO Auto-generated method stub

		
		googleMap.moveCamera(CameraUpdateFactory
				.newLatLngZoom(
						new LatLng(Helper.lat, Helper.lng)
					, 15.0f
						));
		
		float[] results = new float[1];
		Location.distanceBetween(Helper.lat, Helper.lng, lat, lng, results);
		
		
		googleMap.addMarker(new MarkerOptions()
					.position(new LatLng
							(Helper.lat, Helper.lng))
					.title("Me")
					.alpha(0.7f)
					.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
					);
		Marker e_marker =
		googleMap.addMarker(new MarkerOptions()
					.position(new LatLng 
								(lat, lng)
							)
					.title("Marker")
					.snippet(results+"miles to you!")
					);
		e_marker.showInfoWindow();
	}
}
