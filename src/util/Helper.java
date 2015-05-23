package util;

import java.util.ArrayList;

import data.Emergency;
import data.Importance;
import data.Normal;



public final class Helper {
	
	public static String USERID="";
	public static String USERNAME="";
	public static String PATH = "";
	
	//contents sent by the current user that has not been sent out yet.
	public static ArrayList<Normal> cacheNormal = new ArrayList<Normal>();
	public static ArrayList<Importance> cacheImportance = new ArrayList<Importance>();
	public static ArrayList<Emergency> cacheEmergency = new ArrayList<Emergency>();
	
	public static int range = 10;
	public static boolean isBLOCK = false;
	public static boolean isACTIVE = true;

	public static final String IP="http://160.39.175.252:8080/finalproj";
	
	public static final int NORMAL = 0;
	public static final int EMERGENCY = 2;
	public static final int IMPORTANCE = 1;
	
	public static double lat = 0.0;
	public static double lng = 0.0;
	
	public static float getRange(){
		return (float)range/1000;
	}
}
