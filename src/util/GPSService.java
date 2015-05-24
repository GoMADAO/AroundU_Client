package util;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;


public class GPSService extends Service{

	
	WakeLock wakeLock;
	private LocationManager locationManager;
	
	private LocationListener listener = new LocationListener() {

	    @Override
	    public void onLocationChanged(Location location) {
	        // TODO Auto-generated method stub
	    	
	        Log.e("Google", "Location Changed");
	        if (location == null)
	            return;
	        Helper.lat=location.getLatitude();
	        Helper.lng=location.getLongitude();
	        Log.i("Location lat:",""+Helper.lat);
	        Log.i("Location lng:",""+Helper.lng);
	    }

	    @Override
	    public void onProviderDisabled(String provider) {
	        // TODO Auto-generated method stub
	    	Log.e("GOOGLE", "provider disabled");
	    }

	    @Override
	    public void onProviderEnabled(String provider) {
	        // TODO Auto-generated method stub
	    	
	    }

	    @Override
	    public void onStatusChanged(String provider, int status, Bundle extras) {
	        // TODO Auto-generated method stub

	    }
	};
	
	
	
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
	    // TODO Auto-generated method stub
	    super.onCreate();

	    PowerManager pm = (PowerManager) getSystemService(this.POWER_SERVICE);

	    wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "DoNotSleep");

	    // Toast.makeText(getApplicationContext(), "Service Created",
	    // Toast.LENGTH_SHORT).show();

	    Log.e("Google", "Service Created");

	}
	
	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
	    // TODO Auto-generated method stub
	    super.onStart(intent, startId);

	    Log.e("Google", "Service Started");
	    
	    locationManager = (LocationManager) getApplicationContext()
	            .getSystemService(Context.LOCATION_SERVICE);
	    
	    
	   //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
	    
//	    Criteria criteria = new Criteria(); 
//	    String provider = locationManager.getBestProvider(criteria, true); 
	    //Location devicelocation = locationManager.getLastKnownLocation(provider);
	    Helper.lat = 40.8093797;
	    Helper.lng = -73.9599676;
	    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
	            1000, 0.1f, listener);
	    

	}
	
	@Override
	public void onDestroy() {
	    // TODO Auto-generated method stub
	    super.onDestroy();

	    wakeLock.release();

	}
	
	
}
