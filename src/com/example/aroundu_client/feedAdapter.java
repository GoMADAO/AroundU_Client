package com.example.aroundu_client;

import java.util.ArrayList;

import util.Server;
import util.Server4Normal;
import data.Emergency;
import data.EventMSG;
import data.Importance;
import data.Normal;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class feedAdapter extends ArrayAdapter{
	ArrayList<EventMSG> events = new ArrayList<EventMSG>();
	
	@Override
    public int getViewTypeCount() {
        return 3;
    }
	
	@Override
    public int getItemViewType(int position) {
		return events.get(position).getType();
	}
	
	public feedAdapter(Context context, int resource, ArrayList<EventMSG> objects){
		super(context, resource, objects);
		this.events = objects;
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
		int type = events.get(position).getType();
		ViewHolder2 viewholder2 = null;
		ViewHolder1 viewholder1 = null;
		Normal normal = null;
		Importance importance = null;
		Emergency emergency = null;
		final Server4Normal server = new Server4Normal();
		
		switch (type) {
		case 0:
			normal = (Normal)events.get(position);
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
				Button button_like = (Button) convertView.findViewById(R.id.button_like);
				TextView likeCount = (TextView) convertView.findViewById(R.id.likeCount);
				Button button_dislike = (Button) convertView.findViewById(R.id.button_dislike);
				button_dislike.setTag(normal.id);
				button_dislike.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v) {
						server.dislike(""+v.getTag());
					}
				});
				viewholder2 = new ViewHolder2(textView, button_like, likeCount, button_dislike);
				convertView.setTag(viewholder2);
				break;
			case 1:
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.feed_list_layout_importance, null);
				textView = (TextView) convertView.findViewById(R.id.text_importance);
				Button report = (Button) convertView.findViewById(R.id.button_report);
				viewholder1 = new ViewHolder1(textView, report);
				convertView.setTag(viewholder1);
				break;
			case 2:
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.feed_list_layout_emergency, null);
				textView = (TextView) convertView.findViewById(R.id.text_emergency);
				report = (Button) convertView.findViewById(R.id.button_report);
				viewholder1 = new ViewHolder1(textView, report);
				convertView.setTag(viewholder1);
				break;
			}
		}else{
			switch (type){
			case 0:
				viewholder2 = (ViewHolder2) convertView.getTag();
				break;
			default:
				viewholder1 = (ViewHolder1) convertView.getTag();
				break;
			}
		}
		switch (type){
		case 0:
			viewholder2.text.setText(normal.text);
			viewholder2.likeCount.setText(normal.likeNum);
			break;
		case 1:
			viewholder1.text.setText(importance.abstr);
			break;
		case 2:
			viewholder1.text.setText(emergency.abstr);
			break;
		}
		return convertView;
	}
	
}
