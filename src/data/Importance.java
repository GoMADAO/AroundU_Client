package data;

import util.Helper;

public class Importance extends EventMSG{

	public Importance(String id, String text, String lat, String lng,
			String timestamp, String abstr, String reportNUM) {
		super(id, text, lat, lng, timestamp);
		this.abstr = abstr;
		this.reportNUM = reportNUM;
	}
	public String abstr;
	public String reportNUM;
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return Helper.IMPORTANCE;
	}

}
