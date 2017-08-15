package com.example.tinylove.Activity;

import com.example.tinylove.R;
import com.example.tinylove.Database.TinyAnni;
import com.example.tinylove.View.SwitchView;
import com.example.tinylove.View.SwitchView.OnStateChangedListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AddAnniActivity extends Activity implements OnClickListener, OnStateChangedListener {
	
	private ImageView back;
	private TextView complete;
	
	private ImageView imageSelectColor;
	private EditText anniContent;

	private ImageView imageSelectEventColor1;
	private ImageView imageSelectEventColor2;
	private ImageView imageSelectEventColor3;
	private ImageView imageSelectEventColor4;
	private ImageView imageSelectEventColor5;
	private ImageView imageSelectEventColor6;
	private ImageView imageSelectEventColor7;
	
	private RelativeLayout rlTime;
	private TextView textEventTime;
	private SwitchView switchView;
	
	private RelativeLayout rlRemind;
	private TextView textRemind;
	
	private RelativeLayout rlBackground;
	
	private TinyAnni anni;
	
	public static String anniID="";
	private boolean isSystemAnni;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_anni);
		
		initView();
		
		setOnListeners();
		
	}

	private void initView() {
		// TODO Auto-generated method stub
		back=(ImageView)findViewById(R.id.add_anni_back);
		complete=(TextView)findViewById(R.id.add_anni_next);
		
		imageSelectColor=(ImageView)findViewById(R.id.add_anni_select_color);
		anniContent=(EditText)findViewById(R.id.add_anni_event_name);
		anniContent.setEnabled(true);

		imageSelectEventColor1=(ImageView)findViewById(R.id.add_anni_select_1);
		imageSelectEventColor2=(ImageView)findViewById(R.id.add_anni_select_2);
		imageSelectEventColor3=(ImageView)findViewById(R.id.add_anni_select_3);
		imageSelectEventColor4=(ImageView)findViewById(R.id.add_anni_select_4);
		imageSelectEventColor5=(ImageView)findViewById(R.id.add_anni_select_5);
		imageSelectEventColor6=(ImageView)findViewById(R.id.add_anni_select_6);
		imageSelectEventColor7=(ImageView)findViewById(R.id.add_anni_select_7);

		rlTime=(RelativeLayout)findViewById(R.id.add_anni_rl_time);
		textEventTime=(TextView)findViewById(R.id.add_anni_text_time);
		switchView=(SwitchView)findViewById(R.id.add_anni_time_switch);
		switchView.setState(true);
		switchView.setVisibility(View.GONE);

		rlRemind=(RelativeLayout)findViewById(R.id.add_anni_rl_remind);
		rlRemind.setEnabled(true);
		textRemind=(TextView)findViewById(R.id.add_anni_remind_frequent);
		
		rlBackground=(RelativeLayout)findViewById(R.id.add_anni_rl_background);
		
		if(AddAnniActivity.anniID.equals("0001")||AddAnniActivity.anniID.equals("0002")||AddAnniActivity.anniID.equals("0003")||
				AddAnniActivity.anniID.equals("0004")||AddAnniActivity.anniID.equals("0005")||AddAnniActivity.anniID.equals("0006")||
				AddAnniActivity.anniID.equals("0007")){
			anni=new TinyAnni();
			isSystemAnni=true;
			switch (Integer.valueOf(AddAnniActivity.anniID)) {
			case 1:
				anniContent.setText("我们在一起啦");
				anniContent.setEnabled(false);
				rlRemind.setEnabled(false);
				rlRemind.setBackgroundColor(Color.WHITE);
				textRemind.setText(anni.getFrequent(MainActivity.currentUser, "0001"));
				break;
				
			case 2:
				anniContent.setText("TA的生日");
				anniContent.setEnabled(false);
				rlRemind.setEnabled(false);
				rlRemind.setBackgroundColor(Color.WHITE);
				textRemind.setText(anni.getFrequent(MainActivity.currentUser, "0002"));
				break;
				
			case 3:
				anniContent.setText("我的生日");
				anniContent.setEnabled(false);
				rlRemind.setEnabled(false);
				rlRemind.setBackgroundColor(Color.WHITE);
				textRemind.setText(anni.getFrequent(MainActivity.currentUser, "0003"));
				break;
				
			case 4:
				anniContent.setText("第一次拥抱的日子");
				anniContent.setEnabled(false);
				rlRemind.setEnabled(false);
				rlRemind.setBackgroundColor(Color.WHITE);
				textRemind.setText(anni.getFrequent(MainActivity.currentUser, "0004"));
				break;
				
			case 5:
				anniContent.setText("第一次接吻的日子");
				anniContent.setEnabled(false);
				rlRemind.setEnabled(false);
				rlRemind.setBackgroundColor(Color.WHITE);
				textRemind.setText(anni.getFrequent(MainActivity.currentUser, "0005"));
				break;
				
			case 6:
				anniContent.setText("第一次一起去旅行的日子");
				anniContent.setEnabled(false);
				rlRemind.setEnabled(false);
				rlRemind.setBackgroundColor(Color.WHITE);
				textRemind.setText(anni.getFrequent(MainActivity.currentUser, "0006"));
				break;
				
			case 7:
				anniContent.setText("我们结婚啦");
				anniContent.setEnabled(false);
				rlRemind.setEnabled(false);
				rlRemind.setBackgroundColor(Color.WHITE);
				textRemind.setText(anni.getFrequent(MainActivity.currentUser, "0007"));
				break;
				
			default:
				break;
			}
		}
		else{
			isSystemAnni=false;
			anniContent.setEnabled(true);
			rlRemind.setEnabled(true);
		}
			
		
	}

	private void setOnListeners() {
		// TODO Auto-generated method stub
		back.setOnClickListener(this);
		complete.setOnClickListener(this);
		
		imageSelectEventColor1.setOnClickListener(this);
		imageSelectEventColor2.setOnClickListener(this);
		imageSelectEventColor3.setOnClickListener(this);
		imageSelectEventColor4.setOnClickListener(this);
		imageSelectEventColor5.setOnClickListener(this);
		imageSelectEventColor6.setOnClickListener(this);
		imageSelectEventColor7.setOnClickListener(this);
		
		rlTime.setOnClickListener(this);
		//switchView.setOnStateChangedListener(this);
		
		rlRemind.setOnClickListener(this);
		
		rlBackground.setOnClickListener(this);
		
	}
	
	private void setSelectColor(int id){
		imageSelectEventColor1.setImageResource(R.drawable.event_color_1);
		imageSelectEventColor2.setImageResource(R.drawable.event_color_2);
		imageSelectEventColor3.setImageResource(R.drawable.event_color_3);
		imageSelectEventColor4.setImageResource(R.drawable.event_color_4);
		imageSelectEventColor5.setImageResource(R.drawable.event_color_5);
		imageSelectEventColor6.setImageResource(R.drawable.event_color_6);
		imageSelectEventColor7.setImageResource(R.drawable.event_color_7);
		switch (id) {
		case 1:
			AddAnniActivity.BACKCOLOR=0;
			imageSelectEventColor1.setImageResource(R.drawable.add_event_color_1);
			imageSelectColor.setImageResource(R.drawable.event_color_1);
			break;
		case 2:
			AddAnniActivity.BACKCOLOR=1;
			imageSelectEventColor2.setImageResource(R.drawable.add_event_color_2);
			imageSelectColor.setImageResource(R.drawable.event_color_2);
			break;
		case 3:
			AddAnniActivity.BACKCOLOR=2;
			imageSelectEventColor3.setImageResource(R.drawable.add_event_color_3);
			imageSelectColor.setImageResource(R.drawable.event_color_3);
			break;
		case 4:
			AddAnniActivity.BACKCOLOR=3;
			imageSelectEventColor4.setImageResource(R.drawable.add_event_color_4);
			imageSelectColor.setImageResource(R.drawable.event_color_4);
			break;
		case 5:
			AddAnniActivity.BACKCOLOR=4;
			imageSelectEventColor5.setImageResource(R.drawable.add_event_color_5);
			imageSelectColor.setImageResource(R.drawable.event_color_5);
			break;
		case 6:
			AddAnniActivity.BACKCOLOR=5;
			imageSelectEventColor6.setImageResource(R.drawable.add_event_color_6);
			imageSelectColor.setImageResource(R.drawable.event_color_6);
			break;
		case 7:
			AddAnniActivity.BACKCOLOR=6;
			imageSelectEventColor7.setImageResource(R.drawable.add_event_color_7);
			imageSelectColor.setImageResource(R.drawable.event_color_7);
			break;
		default:
			break;
		}
	}

	public static String DATE_YEAR="";
	public static String DATE_MONTH="";
	public static String DATE_DAY="";
	public static String FREQUENT="";
	public static int IMAGE=0;
	public static int BACKCOLOR=1;
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case R.id.add_anni_back:
			Intent i=new Intent(AddAnniActivity.this, AnniActivity.class);
			startActivity(i);
			finish();
			break;
			
		case R.id.add_anni_next:
			String year=AddAnniActivity.DATE_YEAR;
			String month=AddAnniActivity.DATE_MONTH;
			String day=AddAnniActivity.DATE_DAY;
			String color=AddAnniActivity.BACKCOLOR+"";
			String image=AddAnniActivity.IMAGE+"";
			if(isSystemAnni){
				if(year.equals("")||month.equals("")||day.equals("")){
					PromptDialog.title="Error";
					PromptDialog.content="还有信息未填写！";
					Intent di=new Intent(AddAnniActivity.this, PromptDialog.class);
					startActivity(di);
				}
				else{
					anni=new TinyAnni();
					anni.userName=MainActivity.currentUser;
					anni.anniID=AddAnniActivity.anniID;
					anni.anniYear=year;
					anni.anniMonth=month;
					anni.anniDay=day;
					anni.anniColor=color;
					anni.anniBackground=image;
					anni.addSystemAnni();
					AddAnniActivity.DATE_YEAR="";
					AddAnniActivity.DATE_MONTH="";
					AddAnniActivity.DATE_DAY="";
					AddAnniActivity.BACKCOLOR=1;
					AddAnniActivity.IMAGE=0;
					Intent i1=new Intent(AddAnniActivity.this, AnniActivity.class);
					startActivity(i1);
					finish();
				}
			}
			else{
				String event=anniContent.getText().toString();
				String frequent=textRemind.getText().toString();
				if(year.equals("")||month.equals("")||day.equals("")||event==null||event.equals("")){
					PromptDialog.title="Error";
					PromptDialog.content="还有信息未填写！";
					Intent di=new Intent(AddAnniActivity.this, PromptDialog.class);
					startActivity(di);
				}
				else{
					anni=new TinyAnni();
					anni.userName=MainActivity.currentUser;
					anni.anniID=AddAnniActivity.anniID;
					anni.anniYear=year;
					anni.anniMonth=month;
					anni.anniDay=day;
					anni.anniColor=color;
					anni.anniBackground=image;
					anni.anniFrequent=frequent;
					anni.anniContent=event;
					anni.addAnni();
					AddAnniActivity.DATE_YEAR="";
					AddAnniActivity.DATE_MONTH="";
					AddAnniActivity.DATE_DAY="";
					AddAnniActivity.BACKCOLOR=1;
					AddAnniActivity.IMAGE=0;
					Intent i1=new Intent(AddAnniActivity.this, AnniActivity.class);
					startActivity(i1);
					finish();
				}
			}
			
			break;
			
		case R.id.add_anni_select_1:
			setSelectColor(1);
			break;
			
		case R.id.add_anni_select_2:
			setSelectColor(2);
			break;
			
		case R.id.add_anni_select_3:
			setSelectColor(3);
			break;
			
		case R.id.add_anni_select_4:
			setSelectColor(4);
			break;
			
		case R.id.add_anni_select_5:
			setSelectColor(5);
			break;
			
		case R.id.add_anni_select_6:
			setSelectColor(6);
			break;
			
		case R.id.add_anni_select_7:
			setSelectColor(7);
			break;
			
		case R.id.add_anni_rl_time:
			Intent intentSelectDate=new Intent(AddAnniActivity.this, SelectDateDialog.class);
			startActivityForResult(intentSelectDate,0);
			break;
			
		case R.id.add_anni_rl_remind:
			Intent intentSelectFrequent=new Intent(AddAnniActivity.this, SelectFrequentDialog.class);
			startActivityForResult(intentSelectFrequent,1);
			break;
			
		case R.id.add_anni_rl_background:
			SelectBackgroundActivity.anniID=AddAnniActivity.anniID;
			SelectBackgroundActivity.IMAGE=AddAnniActivity.IMAGE;
			Intent intentSelectBack=new Intent(AddAnniActivity.this, SelectBackgroundActivity.class);
			startActivityForResult(intentSelectBack,2);
			break;
			
		default:
			break;
		}
	}
	
	


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==0&&resultCode==0){
			if(AddAnniActivity.DATE_YEAR.equals("")||AddAnniActivity.DATE_MONTH.equals("")||AddAnniActivity.DATE_DAY.equals(""));
			else{
				textEventTime.setText(AddAnniActivity.DATE_YEAR+"年"+AddAnniActivity.DATE_MONTH+"月"+AddAnniActivity.DATE_DAY+"日");
			}
		}
		else if(requestCode==1&&resultCode==0){
			textRemind.setText(AddAnniActivity.FREQUENT);
		}
		else if(requestCode==2&&resultCode==2){
			
		}
		
	}

	@Override
	public void toggleToOn() {
		// TODO Auto-generated method stub
		switchView.setState(true);
		Toast.makeText(AddAnniActivity.this, "选中公历", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void toggleToOff() {
		// TODO Auto-generated method stub
		switchView.setState(false);
		Toast.makeText(AddAnniActivity.this, "选中农历", Toast.LENGTH_SHORT).show();
	}
	
}
