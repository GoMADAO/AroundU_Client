package data;

import org.json.JSONException;
import org.json.JSONObject;

import util.Helper;

public class Importance extends EventMSG{

	public Importance(JSONObject json) throws JSONException {
		super(json);
		this.abstr = abstr;
		this.reportNUM = reportNUM;
	}
	public String abstr;
	public String reportNUM;
	@Override
	protected int getType() {
		// TODO Auto-generated method stub
		return Helper.IMPORTANCE;
	}

}
