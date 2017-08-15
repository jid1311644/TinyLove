package com.example.tinylove.Adapter;

import java.util.LinkedList;

import com.example.tinylove.R;
import com.example.tinylove.Interface.ItemClickListener;
import com.example.tinylove.View.CustomImageView;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.wifi.WifiEnterpriseConfig;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>{
	
	//添加纪念日时标签背景颜色和图片
	final int[] color={R.color.eventsColor1,R.color.eventsColor2,R.color.eventsColor3,
			R.color.eventsColor4,R.color.eventsColor5,R.color.eventsColor6,
			R.color.eventsColor7,R.drawable.anni_together_icon,R.drawable.anni_birthday_icon,
			R.drawable.anni_kiss_icon,R.drawable.anni_hug_icon,R.drawable.anni_marry_icon,
			R.drawable.anni_travel_icon};

	//纪念日list的相关参数
	LinkedList<String> dataAnniEvent;
	LinkedList<String> dataAnniDays;
	LinkedList<String> dataAnniYear;
	LinkedList<String> dataAnniMonth;
	LinkedList<String> dataAnniDay;
	LinkedList<Integer> dataAnniColor;

	
	ItemClickListener itemClickListener;
	
	public void setOnClickListener(ItemClickListener itemClickListener){
		this.itemClickListener=itemClickListener;
	}
	
	//纪念日list数据初始化
	public MyRecyclerAdapter(LinkedList<String> events,LinkedList<String> days,
			LinkedList<String> Y,LinkedList<String> M,LinkedList<String> D,LinkedList<Integer> back){
		this.dataAnniDays=days;
		this.dataAnniEvent=events;
		this.dataAnniYear=Y;
		this.dataAnniMonth=M;
		this.dataAnniDay=D;
		this.dataAnniColor=back;
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return dataAnniEvent.size();
	}

	@Override
	public void onBindViewHolder(MyRecyclerAdapter.ViewHolder viewHolder, int position) {
		// TODO Auto-generated method stub
		
		if(dataAnniColor.get(position)>6){
			viewHolder.textAnniYandM.setVisibility(View.GONE);
			viewHolder.textAnniD.setVisibility(View.GONE);
			viewHolder.imageBack.setVisibility(View.VISIBLE);
			
			viewHolder.textAnniDay.setVisibility(View.GONE);
			viewHolder.textAnniLong.setVisibility(View.GONE);
			viewHolder.imageAddEvent.setVisibility(View.VISIBLE);
				
			viewHolder.llAnniBack.setBackgroundColor(Color.TRANSPARENT);
			viewHolder.imageBack.setImageResource(color[dataAnniColor.get(position)]);
			
			viewHolder.textAnniEvent.setText(dataAnniEvent.get(position));
		}
		else{
			viewHolder.textAnniYandM.setVisibility(View.VISIBLE);
			viewHolder.textAnniD.setVisibility(View.VISIBLE);
			viewHolder.imageBack.setVisibility(View.GONE);
			
			viewHolder.textAnniDay.setVisibility(View.VISIBLE);
			viewHolder.textAnniLong.setVisibility(View.VISIBLE);
			viewHolder.imageAddEvent.setVisibility(View.GONE);
			
			viewHolder.textAnniYandM.setText(dataAnniYear.get(position)+" "+dataAnniMonth.get(position));
			viewHolder.textAnniD.setText(dataAnniDay.get(position));
			viewHolder.textAnniEvent.setText(dataAnniEvent.get(position));
			viewHolder.textAnniLong.setText(dataAnniDays.get(position));
			viewHolder.llAnniBack.setBackgroundResource(color[dataAnniColor.get(position)]);
		}	
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
		// TODO Auto-generated method stub
		View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.anni_recycler_item, viewGroup,false);
		return new ViewHolder(view, itemClickListener);
	}
	
	
	
	class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

		//纪念日list_item涉及到的控件
		LinearLayout llAnniBack;
		TextView textAnniYandM;
		TextView textAnniD;
		ImageView imageBack;
		
		TextView textAnniEvent;
		
		TextView textAnniLong;
		TextView textAnniDay;
		ImageView imageAddEvent;
		
		ItemClickListener listener;

		public ViewHolder(View itemView,ItemClickListener itemClickListener) {
			super(itemView);
			// TODO Auto-generated constructor stub
			//纪念日list_item涉及到的控件
			llAnniBack=(LinearLayout)itemView.findViewById(R.id.anni_recycler_item_picture);
			textAnniYandM=(TextView)itemView.findViewById(R.id.anni_recycler_item_text_yearmonth);
			textAnniD=(TextView)itemView.findViewById(R.id.anni_recycler_item_text_day);
			imageBack=(ImageView)itemView.findViewById(R.id.anni_recycler_item_back_picture);
			
			textAnniEvent=(TextView)itemView.findViewById(R.id.anni_recycler_item_content);
			
			textAnniLong=(TextView)itemView.findViewById(R.id.anni_recycler_item_days);
			textAnniDay=(TextView)itemView.findViewById(R.id.anni_recycler_item_day);
			imageAddEvent=(ImageView)itemView.findViewById(R.id.anni_recycler_item_add_event);

			
			itemView.setOnClickListener(this);
			
			this.listener=itemClickListener;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			listener.onItemClickListener(getPosition());
		}

	}
	
}
