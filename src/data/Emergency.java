package data;

public class Emergency extends EventMSG{
	public String abstract_msg;
	public int report_times;
	public boolean mapOn;
	
	public Emergency(String id, String detail, String lat, String lng, String timestamp, boolean mapOn, String abstract_msg) {
		super(id,detail,lat,lng,timestamp);
		this.report_times = 0;
		this.abstract_msg = abstract_msg;
		this.mapOn = mapOn;
	}
}
