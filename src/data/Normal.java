package data;

import org.json.JSONException;
import org.json.JSONObject;

import util.Helper;

public class Normal extends EventMSG{
	public String topic;
	public String likeNum;
	
	
	public Normal(JSONObject json ) throws JSONException {
		super(json);
		this.topic = json.getString("topic");
		this.likeNum = Integer.parseInt(json.getString("likes"))
				-Integer.parseInt(json.getString("dislikes"))+"";
		
	}
	public Normal(String text, String abstr) {
		super(text, null);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return Helper.NORMAL;
	}
	
}
