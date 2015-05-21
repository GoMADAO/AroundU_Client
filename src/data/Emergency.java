package data;

import org.json.JSONException;
import org.json.JSONObject;

import util.Helper;

public class Emergency extends EventMSG{
	public String abstr;
	public String reportNUM;
	public boolean mapOn;
	

	public Emergency(JSONObject json) throws JSONException {
		super(json);
		this.abstr = json.getString("abstract");
		this.reportNUM = json.getString("report");

		this.mapOn = (json.getString("map").equals("1"))?true:false;
	}
	
	public Emergency(String text, String abstr){
		super(text, null);
		this.abstr = abstr;
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return Helper.EMERGENCY;
	}
}
