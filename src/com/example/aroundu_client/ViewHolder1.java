package com.example.aroundu_client;

import android.widget.Button;
import android.widget.TextView;

public class ViewHolder1 {
	TextView text;
	TextView time;
	Button report;
	
	public ViewHolder1(TextView text, TextView timeText, Button report){
		this.text = text;
		this.time = timeText;
		this.report = report;
	}
}
