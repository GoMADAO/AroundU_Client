package data;

import org.json.JSONException;
import org.json.JSONObject;

import util.Helper;

public class Importance extends EventMSG{

	public Importance(JSONObject json) throws JSONException {
		super(json);
		this.abstr = json.getString("abstract");
		this.reportNUM = json.getString("report");
	}
	public String abstr;
	public String reportNUM;
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return Helper.IMPORTANCE;
	}

}
