package com.example.aroundu_client;

import android.widget.Button;
import android.widget.TextView;

public class ViewHolder2 {
	TextView text;
	TextView time;
	Button like;
	TextView likeCount;
	Button dislike;
	
	public ViewHolder2(TextView text, TextView time, Button like, TextView likecount, Button dislike){
		this.text = text;
		this.time = time;
		this.like = like;
		this.likeCount = likecount;
		this.dislike = dislike;
	}
}
