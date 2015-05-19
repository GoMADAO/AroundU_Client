package com.example.aroundu_client;

import android.widget.Button;
import android.widget.TextView;

public class ViewHolder2 {
	TextView text;
	Button like;
	TextView likeCount;
	Button dislike;
	
	public ViewHolder2(TextView text, Button like, TextView likecount, Button dislike){
		this.text = text;
		this.like = like;
		this.likeCount = likecount;
		this.dislike = dislike;
	}
}
