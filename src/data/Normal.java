package data;

import org.json.JSONException;
import org.json.JSONObject;

import util.Helper;

public class Normal extends EventMSG{
	public Normal(JSONObject json ) throws JSONException {
		super(json);
		this.topic = json.getString("topic");
		this.likeNum = Integer.parseInt(json.getString("likes"))
				-Integer.parseInt(json.getString("dislikes"))+"";
		
	}
	public String topic;
	public String likeNum;
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return Helper.NORMAL;
	}
	
}
