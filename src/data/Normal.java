package data;

import org.json.JSONException;
import org.json.JSONObject;

import util.Helper;

public class Normal extends EventMSG{
	public Normal(JSONObject json ) throws JSONException {
		super( json);
		
		
	}
	public String topic;
	public String likeNum;
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return Helper.NORMAL;
	}
	
}
