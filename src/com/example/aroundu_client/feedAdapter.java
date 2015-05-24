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
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
				TextView sentIcon = (TextView) convertView.findViewById(R.id.sentiment_icon);
				TextView textView = (TextView) convertView.findViewById(R.id.text_normal);
				TextView timeText = (TextView) convertView.findViewById(R.id.text_time); 
				ImageButton button_like = (ImageButton) convertView.findViewById(R.id.button_like);
				TextView likeCount = (TextView) convertView.findViewById(R.id.likeCount);
				ImageButton button_dislike = (ImageButton) convertView.findViewById(R.id.button_dislike);
				
				button_like.setTag(normal);
				button_dislike.setTag(normal);
				viewholder2 = new ViewHolder2(sentIcon, textView, timeText, button_like, likeCount, button_dislike);
				convertView.setTag(viewholder2);
				
				break;
			case 1:
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.feed_list_layout_importance, null);
				textView = (TextView) convertView.findViewById(R.id.text_importance);
				timeText = (TextView) convertView.findViewById(R.id.text_time);
				ImageButton report = (ImageButton) convertView.findViewById(R.id.button_report);
				
				textView.setTag(importance);
				report.setTag(importance);
				
				viewholder1 = new ViewHolder1(textView, timeText, report);
				convertView.setTag(viewholder1);
				break;
			case 2:
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.feed_list_layout_emergency, null);
				textView = (TextView) convertView.findViewById(R.id.text_emergency);
				timeText = (TextView) convertView.findViewById(R.id.text_time);
				report = (ImageButton) convertView.findViewById(R.id.button_report);
				
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
			String sendTime;
			SimpleDateFormat df;
			String timePass = "";
			if (normal.isCached){
				viewholder2.like.setEnabled(false);
				viewholder2.dislike.setEnabled(false);
				timePass += "Processing...";
			}
			else{
				sendTime = normal.timestamp;
				System.out.println("original:"+sendTime);
				df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Date send = df.parse(sendTime);
					Date now = new Date();
					System.out.println("send:"+send);
					System.out.println("now:"+now);
					long diff = (now.getTime()-send.getTime())/(1000*60);
					timePass += diff+" mins ago";
				} catch (ParseException e) {
					e.printStackTrace();
				}
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
				if(normal.sentiment.equals("positive")){
					Drawable drawable = activity.getResources().getDrawable(R.drawable.happy);
					viewholder2.sentIcon.setBackground(drawable);
				}
				else if(normal.sentiment.equals("negative")){
//					viewholder2.colorbuck.setBackgroundColor(Color.parseColor("#80899a"));
				}
				else if(normal.sentiment.equals("neutral")){
//					viewholder2.colorbuck.setBackgroundColor(Color.parseColor("#ceb646"));
				}
			}
			viewholder2.text.setText(normal.text);
			viewholder2.time.setText(timePass);
			break;
		case 1:
			timePass = "";
			if(importance.isCached){
				viewholder1.report.setEnabled(false);
				timePass+="Processing...";
			}else{
				sendTime = importance.timestamp;
				df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Date send = df.parse(sendTime);
					Date now = new Date();
					long diff = (now.getTime()-send.getTime())/(1000*60);
					timePass += diff+" mins ago";
				} catch (ParseException e) {
					e.printStackTrace();
				}
				viewholder1.text.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v) {
						Importance tmp = (Importance) v.getTag();
						Intent intent = new Intent(activity, DetailActivity.class);
						intent.putExtra("imp_details", tmp.text);
						activity.startActivity(intent);
					}
				});
				viewholder1.report.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v) {
						Importance tmp = (Importance) v.getTag();
						server4imp.report(tmp.id);
						Toast.makeText(activity, "Report recorded!", Toast.LENGTH_SHORT).show();
						mClickListener.onBtnClick();
					}
				});
			}
			viewholder1.text.setText(importance.abstr);
			viewholder1.time.setText(timePass);
			break;
		case 2:
			timePass = "";
			if(emergency.isCached){
				viewholder1.report.setEnabled(false);
				timePass += "Processing...";
			}else{
				sendTime = emergency.timestamp;
				df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Date send = df.parse(sendTime);
					Date now = new Date();
					long diff = (now.getTime()-send.getTime())/(1000*60);
					timePass += diff+" mins ago";
				} catch (ParseException e) {
					e.printStackTrace();
				}
				viewholder1.text.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v) {
						Emergency tmp = (Emergency) v.getTag();
						//here to go to emergency detail page, putExtra of the details
						Intent intent = null;
						if (tmp.mapOn){
							intent = new Intent(activity, EmgDetailsActivity.class);
							intent.putExtra("emer_abstract", tmp.abstr);
							intent.putExtra("emer_details", tmp.text);
							intent.putExtra("emer_lat", tmp.lat);
							intent.putExtra("emer_lng", tmp.lng);
						}else{
							intent = new Intent(activity, DetailActivity.class);
							intent.putExtra("emer_details", tmp.text);
						}
						activity.startActivity(intent);
					}
				});
				viewholder1.report.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v) {
						Emergency tmp = (Emergency) v.getTag();
						server4imp.report(tmp.id);
						Toast.makeText(activity, "Report recorded!", Toast.LENGTH_SHORT).show();
						mClickListener.onBtnClick();
					}
				});
			}
			viewholder1.text.setText(emergency.abstr);
			viewholder1.time.setText(timePass);
			break;
		}
		return convertView;
	}
	
}
