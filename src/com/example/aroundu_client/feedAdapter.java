package com.example.aroundu_client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import util.Server;
import util.Server4Emer;
import util.Server4Imp;
import util.Server4Normal;
import data.Emergency;
import data.EventMSG;
import data.Importance;
import data.Normal;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class feedAdapter extends ArrayAdapter{
	ArrayList<EventMSG> events = new ArrayList<EventMSG>();
	private BtnClickListener mClickListener = null;
	int pos;
	Context activity;
	
	@Override
    public int getViewTypeCount() {
        return 3;
    }
	
	@Override
    public int getItemViewType(int position) {
		return events.get(position).getType();
	}
	
	public feedAdapter(Context context, int resource, ArrayList<EventMSG> objects, BtnClickListener listener){
		super(context, resource, objects);
		this.events = objects;
		activity = context;
		this.mClickListener = listener;
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
		pos = position;
		int type = events.get(position).getType();
		ViewHolder2 viewholder2 = null;
		ViewHolder1 viewholder1 = null;
		Normal normal = null;
		Importance importance = null;
		Emergency emergency = null;
		final Server4Normal server4norm = new Server4Normal();
		final Server4Imp server4imp = new Server4Imp();
		final Server4Emer server4emer = new Server4Emer();
		
		switch (type) {
		case 0:
			normal = (Normal)events.get(position);
			System.out.println("init: "+normal.likeNum);
			break;
		case 1:
			importance = (Importance)events.get(position);
			break;
		case 2:
			emergency = (Emergency)events.get(position);
			break;
		}
		
		if (convertView == null){
			switch (type) {
			case 0:
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.feed_list_layout_normal, null);
				TextView textView = (TextView) convertView.findViewById(R.id.text_normal);
				TextView timeText = (TextView) convertView.findViewById(R.id.text_time); 
				Button button_like = (Button) convertView.findViewById(R.id.button_like);
				TextView likeCount = (TextView) convertView.findViewById(R.id.likeCount);
				Button button_dislike = (Button) convertView.findViewById(R.id.button_dislike);
				
				button_like.setTag(normal);
				button_dislike.setTag(normal);
				viewholder2 = new ViewHolder2(textView, timeText, button_like, likeCount, button_dislike);
				convertView.setTag(viewholder2);
				
				break;
			case 1:
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.feed_list_layout_importance, null);
				textView = (TextView) convertView.findViewById(R.id.text_importance);
				timeText = (TextView) convertView.findViewById(R.id.text_time);
				Button report = (Button) convertView.findViewById(R.id.button_report);
				
				report.setTag(importance);
				
				viewholder1 = new ViewHolder1(textView, timeText, report);
				convertView.setTag(viewholder1);
				break;
			case 2:
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.feed_list_layout_emergency, null);
				textView = (TextView) convertView.findViewById(R.id.text_emergency);
				timeText = (TextView) convertView.findViewById(R.id.text_time);
				report = (Button) convertView.findViewById(R.id.button_report);
				
				textView.setTag(emergency);
				report.setTag(emergency);
				
				viewholder1 = new ViewHolder1(textView, timeText, report);
				convertView.setTag(viewholder1);
				break;
			}
		}else{
			System.out.println("already have item!");
			switch (type){
			case 0:
				viewholder2 = (ViewHolder2) convertView.getTag();
				System.out.println(viewholder2.likeCount.getText().toString());
				break;
			default:
				viewholder1 = (ViewHolder1) convertView.getTag();
				break;
			}
		}
		switch (type){
		case 0:
			String sendTime = normal.timestamp;
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String timePass = "";
			try {
				Date send = df.parse(sendTime);
				Date now = new Date();
				long diff = (now.getTime()-send.getTime())/(1000*60);
				timePass += diff+" mins ago";
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			viewholder2.text.setText(normal.text);
			viewholder2.time.setText(timePass);
			viewholder2.likeCount.setText(normal.likeNum);
			viewholder2.like.setTag(normal);
			viewholder2.like.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					Normal tmp = (Normal) v.getTag();
					server4norm.like(""+tmp.id);
					tmp.likeNum = ""+(Integer.parseInt(tmp.likeNum)+1);
					mClickListener.onBtnClick();
				}		
			});
			viewholder2.dislike.setTag(normal);
			viewholder2.dislike.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					Normal tmp = (Normal) v.getTag();
					server4norm.dislike(""+tmp.id);
					tmp.likeNum = ""+(Integer.parseInt(tmp.likeNum)-1);
					mClickListener.onBtnClick();
				}		
			});
			break;
		case 1:
			sendTime = importance.timestamp;
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			timePass = "";
			try {
				Date send = df.parse(sendTime);
				Date now = new Date();
				long diff = (now.getTime()-send.getTime())/(1000*60);
				timePass += diff+" mins ago";
			} catch (ParseException e) {
				e.printStackTrace();
			}
			viewholder1.text.setText(importance.abstr);
			viewholder1.time.setText(timePass);
			viewholder1.report.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					Importance tmp = (Importance) v.getTag();
					server4imp.report(tmp.id);
					mClickListener.onBtnClick();
				}
			});
			break;
		case 2:
			sendTime = emergency.timestamp;
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			timePass = "";
			try {
				Date send = df.parse(sendTime);
				Date now = new Date();
				long diff = (now.getTime()-send.getTime())/(1000*60);
				timePass += diff+" mins ago";
			} catch (ParseException e) {
				e.printStackTrace();
			}
			viewholder1.text.setText(emergency.abstr);
			viewholder1.time.setText(timePass);
			viewholder1.text.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					Emergency tmp = (Emergency) v.getTag();
					//here to go to emergency detail page, putExtra of the details
					Intent intent = new Intent(activity, EmgDetailsActivity.class);
					intent.putExtra("emer_details", tmp.text);
					intent.putExtra("emer_mapon", tmp.mapOn);
					intent.putExtra("emer_lat", tmp.lat);
					intent.putExtra("emer_lng", tmp.lng);
					activity.startActivity(intent);
				}
			});
			viewholder1.report.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					Emergency tmp = (Emergency) v.getTag();
					server4imp.report(tmp.id);
					mClickListener.onBtnClick();
				}
			});
			break;
		}
		return convertView;
	}
	
}
