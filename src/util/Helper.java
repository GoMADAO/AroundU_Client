package util;



public final class Helper {
	
	public static String USERID;
	public static String USERNAME;
	public static String PATH;
	public static int range = 10;
	public static boolean isBLOCK = false;
	public static boolean isACTIVE = true;


	public static String IP="129.236.235.212";
	
	
	public static final int NORMAL = 0;
	public static final int EMERGENCY = 2;
	public static final int IMPORTANCE = 1;
	

	
	public static float getRange(){
		return (float)range/1000;
	}
}
