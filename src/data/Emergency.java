package data;

import org.json.JSONException;
import org.json.JSONObject;

import util.Helper;

public class Emergency extends EventMSG{
	public String abstract_msg;
	public int report_times;
	public boolean mapOn;
	
	public Emergency(JSONObject json) throws JSONException {
		super(json);
		this.report_times = 0;
		this.abstract_msg = abstract_msg;
		this.mapOn = mapOn;
	}

	@Override
	protected int getType() {
		// TODO Auto-generated method stub
		return Helper.EMERGENCY;
	}
}
