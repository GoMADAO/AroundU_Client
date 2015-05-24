package com.example.aroundu_client;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ViewHolder1 {
	TextView text;
	TextView time;
	ImageButton report;
	
	public ViewHolder1(TextView text, TextView timeText, ImageButton report){
		this.text = text;
		this.time = timeText;
		this.report = report;
	}
}
