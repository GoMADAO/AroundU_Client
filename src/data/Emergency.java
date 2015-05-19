package data;

import util.Helper;

public class Emergency extends EventMSG{
	public String abstr;
	public String reportNUM;
	public boolean mapOn;
	
	public Emergency(String id, String detail, String lat, String lng, String timestamp, boolean mapOn, String abstr, String reportNUM) {
		super(id,detail,lat,lng,timestamp);
		this.abstr = abstr;
		this.reportNUM = reportNUM;
		this.mapOn = mapOn;
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return Helper.EMERGENCY;
	}
}
