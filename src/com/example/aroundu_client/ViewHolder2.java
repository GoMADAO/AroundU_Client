package com.example.aroundu_client;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewHolder2 {
	TextView sentIcon;
	TextView text;
	TextView time;
	ImageButton like;
	TextView likeCount;
	ImageButton dislike;
	
	public ViewHolder2(TextView sentIcon, TextView text, TextView time, ImageButton like, TextView likecount, ImageButton dislike){
		this.sentIcon = sentIcon;
		this.text = text;
		this.time = time;
		this.like = like;
		this.likeCount = likecount;
		this.dislike = dislike;
	}
}
