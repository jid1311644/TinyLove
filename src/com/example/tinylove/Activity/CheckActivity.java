package com.example.tinylove.Activity;

import java.util.LinkedList;

import com.example.tinylove.R;
import com.example.tinylove.Database.TinyCheck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CheckActivity extends Activity implements OnClickListener {
	
	private ImageView back;
	private CheckItem[] items=new CheckItem[8];
	
	public LinkedList<String> checkIDs;
	public LinkedList<Integer> checkStates;
	public LinkedList<Integer> checkTimess;
	public LinkedList<String> checkLastDays;
	
	private TinyCheck check;
	
	public static Activity activity;
	public static boolean detailIsChangeCheck;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check);
		activity=this;
		
		get();
		
		initView();
		setOnListeners();
		
	}
	
	private void get(){
		check=new TinyCheck();
		check.getData(MainActivity.currentUser);
		checkIDs=check.getCids();
		checkStates=check.getCss();
		checkTimess=check.getCtss();
		checkLastDays=check.getClds();
		check.display();
	}

	private void initView() {
		// TODO Auto-generated method stub
		
		back=(ImageView)findViewById(R.id.check_back);
		
		for(int i=0;i<8;i++){
			int id=CheckItem.dataItemId[i];
			int id1=CheckItem.dataCheckOutId[i];
			int id2=CheckItem.dataCheckInImageId[i];
			int id3=CheckItem.dataCheckInSpace[i];
			int id4=CheckItem.dataCheckInNum[i];
			int id5=CheckItem.dataCheckInDay[i];
			items[i]=new CheckItem(id, id1, id2, id3, id4, id5);
			items[i].item=(RelativeLayout)findViewById(id);
			items[i].textCheckOut=(TextView)findViewById(id1);
			items[i].imageCheckIn=(ImageView)findViewById(id2);
			items[i].viewCheckInSpace=(View)findViewById(id3);
			items[i].textCheckInNum=(TextView)findViewById(id4);
			items[i].textCheckInDay=(TextView)findViewById(id5);
			
			if(check.isChecked(checkIDs.get(i))){
				items[i].textCheckOut.setVisibility(View.GONE);
				items[i].imageCheckIn.setVisibility(View.VISIBLE);
				items[i].viewCheckInSpace.setVisibility(View.VISIBLE);
				items[i].textCheckInNum.setVisibility(View.VISIBLE);
				items[i].textCheckInDay.setVisibility(View.VISIBLE);
				items[i].textCheckInNum.setText(checkTimess.get(i)+"");
			}
			else{
				items[i].textCheckOut.setVisibility(View.VISIBLE);
				items[i].imageCheckIn.setVisibility(View.GONE);
				items[i].viewCheckInSpace.setVisibility(View.GONE);
				items[i].textCheckInNum.setVisibility(View.GONE);
				items[i].textCheckInDay.setVisibility(View.GONE);
			}
		}
		
	}

	private void setOnListeners() {
		// TODO Auto-generated method stub
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		for(int i=0;i<8;i++){
			items[i].item.setOnClickListener(this);
			items[i].textCheckOut.setOnClickListener(this);
		}
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		for(int i=0;i<8;i++){
			if(v.getId()==items[i].id){
				CheckDetailDialog.checkID=checkIDs.get(i);
				CheckDetailDialog.checkId=i;
				if(check.isChecked(checkIDs.get(i))){
					CheckDetailDialog.isChecked=true;
				}
				else{
					CheckDetailDialog.isChecked=false;
				}
				CheckDetailDialog.checkDays=checkTimess.get(i);
				Intent intent=new Intent(CheckActivity.this, CheckDetailDialog.class);
				startActivityForResult(intent,5);
			}
			if(v.getId()==items[i].checkOutId){
				if(check.check(checkIDs.get(i))){
					items[i].textCheckOut.setVisibility(View.GONE);
					items[i].imageCheckIn.setVisibility(View.VISIBLE);
					items[i].viewCheckInSpace.setVisibility(View.VISIBLE);
					items[i].textCheckInNum.setVisibility(View.VISIBLE);
					items[i].textCheckInDay.setVisibility(View.VISIBLE);
					get();
					items[i].textCheckInNum.setText(checkTimess.get(i)+"");
				}
				else{
					PromptDialog.title="Error";
					PromptDialog.content="今日已打卡";
					Intent ip=new Intent(CheckActivity.this, PromptDialog.class);
					startActivity(ip);
				}
				
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode==5&&resultCode==0){
			if(detailIsChangeCheck){
				Intent i=new Intent(CheckActivity.this, CheckActivity.class);
				startActivity(i);
				finish();
			}
		}
	}
	
	
	
}

class CheckItem{
	
	final static int[] dataItemId={R.id.check_morning,R.id.check_evening,R.id.check_phone,R.id.check_travel,
			R.id.check_food,R.id.check_movie,R.id.check_sport,R.id.check_shop};
	final static int[] dataCheckOutId={R.id.check_morning_out,R.id.check_evening_out,R.id.check_phone_out,
			R.id.check_travel_out,R.id.check_food_out,R.id.check_movie_out,R.id.check_sport_out,R.id.check_shop_out};
	final static int[] dataCheckInImageId={R.id.check_morning_in_image,R.id.check_evening_in_image,R.id.check_phone_in_image,
			R.id.check_travel_in_image,R.id.check_food_in_image,R.id.check_movie_in_image,R.id.check_sport_in_image,R.id.check_shop_in_image};
	final static int[] dataCheckInSpace={R.id.check_morning_in_space,R.id.check_evening_in_space,R.id.check_phone_in_space,
			R.id.check_travel_in_space,R.id.check_food_in_space,R.id.check_movie_in_space,R.id.check_sport_in_space,R.id.check_shop_in_space};
	final static int[] dataCheckInNum={R.id.check_morning_in_num,R.id.check_evening_in_num,R.id.check_phone_in_num,
			R.id.check_travel_in_num,R.id.check_food_in_num,R.id.check_movie_in_num,R.id.check_sport_in_num,R.id.check_shop_in_num};
	final static int[] dataCheckInDay={R.id.check_morning_in_day,R.id.check_evening_in_day,R.id.check_phone_in_day,
			R.id.check_travel_in_day,R.id.check_food_in_day,R.id.check_movie_in_day,R.id.check_sport_in_day,R.id.check_shop_in_day};

	final static int MORNING=0;
	final static int EVENING=1;
	final static int PHONE=2;
	final static int TRAVEL=3;
	final static int FOOD=4;
	final static int MOVIE=5;
	final static int SPORT=6;
	final static int SHOP=7;
	
	RelativeLayout item;
	int id;
	
	TextView textCheckOut;
	int checkOutId;
	
	ImageView imageCheckIn;
	int checkInImageId;
	
	View viewCheckInSpace;
	int checkInViewId;
	
	TextView textCheckInNum;
	int checkInTextNumId;
	
	TextView textCheckInDay;
	int checkInTextDayId;
	
	public CheckItem(int id,int id1,int id2,int id3,int id4,int id5){
		this.id=id;
		this.checkOutId=id1;
		this.checkInImageId=id2;
		this.checkInViewId=id3;
		this.checkInTextNumId=id4;
		this.checkInTextDayId=id5;
	}
	
}

