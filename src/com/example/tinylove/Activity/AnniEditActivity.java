package com.example.tinylove.Activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.tinylove.R;
import com.example.tinylove.Database.TinyAnni;
import com.example.tinylove.View.SwitchView;

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

public class AnniEditActivity extends Activity implements OnClickListener {
	
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
	
	private RelativeLayout rlDelete;
	
	private boolean isSystemAnni;
	
	private TinyAnni anni;

	public static String anniID="";
	public static String DATE_YEAR="";
	public static String DATE_MONTH="";
	public static String DATE_DAY="";
	public static String FREQUENT="";
	public static int BACKCOLOR=1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_anni_edit);
		
		anni=new TinyAnni();
		anni.getData(MainActivity.currentUser);
		int i=anni.getIds().indexOf(anniID);
		DATE_YEAR=anni.getY().get(i);
		DATE_MONTH=anni.getM().get(i);
		DATE_DAY=anni.getD().get(i);
		FREQUENT=anni.getFrequent(MainActivity.currentUser, anniID);
		BACKCOLOR=anni.getBack().get(i);
		
		initView();
		setOnListeners();
		
	}

	private void initView() {
		// TODO Auto-generated method stub
		back=(ImageView)findViewById(R.id.anni_edit_back);
		complete=(TextView)findViewById(R.id.anni_edit_next);
		
		imageSelectColor=(ImageView)findViewById(R.id.anni_edit_select_color);
		anniContent=(EditText)findViewById(R.id.anni_edit_event_name);
		anniContent.setEnabled(true);

		imageSelectEventColor1=(ImageView)findViewById(R.id.anni_edit_select_1);
		imageSelectEventColor2=(ImageView)findViewById(R.id.anni_edit_select_2);
		imageSelectEventColor3=(ImageView)findViewById(R.id.anni_edit_select_3);
		imageSelectEventColor4=(ImageView)findViewById(R.id.anni_edit_select_4);
		imageSelectEventColor5=(ImageView)findViewById(R.id.anni_edit_select_5);
		imageSelectEventColor6=(ImageView)findViewById(R.id.anni_edit_select_6);
		imageSelectEventColor7=(ImageView)findViewById(R.id.anni_edit_select_7);

		rlTime=(RelativeLayout)findViewById(R.id.anni_edit_rl_time);
		textEventTime=(TextView)findViewById(R.id.anni_edit_text_time);
		switchView=(SwitchView)findViewById(R.id.anni_edit_time_switch);
		switchView.setState(true);
		switchView.setVisibility(View.GONE);

		rlRemind=(RelativeLayout)findViewById(R.id.anni_edit_rl_remind);
		rlRemind.setEnabled(true);
		textRemind=(TextView)findViewById(R.id.anni_edit_remind_frequent);
		
		rlDelete=(RelativeLayout)findViewById(R.id.anni_edit_rl_delete);

		anni=new TinyAnni();
		String s=anni.getAnniDate(anniID);
		
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date d=sdf.parse(s);
			
			s=(d.getYear()+1900)+"年"+(d.getMonth()+1)+"月"+d.getDate()+"日";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		textEventTime.setText(s);
		if(AnniEditActivity.anniID.equals("0001")||AnniEditActivity.anniID.equals("0002")||AnniEditActivity.anniID.equals("0003")||
				AnniEditActivity.anniID.equals("0004")||AnniEditActivity.anniID.equals("0005")||AnniEditActivity.anniID.equals("0006")||
				AnniEditActivity.anniID.equals("0007")){
			anni=new TinyAnni();
			isSystemAnni=true;
			switch (Integer.valueOf(AnniEditActivity.anniID)) {
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
			anniContent.setText(anni.getAnniContent(anniID));
			textRemind.setText(anni.getFrequent(MainActivity.currentUser, anniID));
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
		
		rlDelete.setOnClickListener(this);
		
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
			AnniEditActivity.BACKCOLOR=0;
			imageSelectEventColor1.setImageResource(R.drawable.add_event_color_1);
			imageSelectColor.setImageResource(R.drawable.event_color_1);
			break;
		case 2:
			AnniEditActivity.BACKCOLOR=1;
			imageSelectEventColor2.setImageResource(R.drawable.add_event_color_2);
			imageSelectColor.setImageResource(R.drawable.event_color_2);
			break;
		case 3:
			AnniEditActivity.BACKCOLOR=2;
			imageSelectEventColor3.setImageResource(R.drawable.add_event_color_3);
			imageSelectColor.setImageResource(R.drawable.event_color_3);
			break;
		case 4:
			AnniEditActivity.BACKCOLOR=3;
			imageSelectEventColor4.setImageResource(R.drawable.add_event_color_4);
			imageSelectColor.setImageResource(R.drawable.event_color_4);
			break;
		case 5:
			AnniEditActivity.BACKCOLOR=4;
			imageSelectEventColor5.setImageResource(R.drawable.add_event_color_5);
			imageSelectColor.setImageResource(R.drawable.event_color_5);
			break;
		case 6:
			AnniEditActivity.BACKCOLOR=5;
			imageSelectEventColor6.setImageResource(R.drawable.add_event_color_6);
			imageSelectColor.setImageResource(R.drawable.event_color_6);
			break;
		case 7:
			AnniEditActivity.BACKCOLOR=6;
			imageSelectEventColor7.setImageResource(R.drawable.add_event_color_7);
			imageSelectColor.setImageResource(R.drawable.event_color_7);
			break;
		default:
			break;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case R.id.anni_edit_back:
			Intent i=new Intent(AnniEditActivity.this, AnniDetailActivity.class);
			startActivity(i);
			finish();
			break;
			
		case R.id.anni_edit_next:
			String year=AnniEditActivity.DATE_YEAR;
			String month=AnniEditActivity.DATE_MONTH;
			String day=AnniEditActivity.DATE_DAY;
			String color=AnniEditActivity.BACKCOLOR+"";
			if(isSystemAnni){
				if(year.equals("")||month.equals("")||day.equals("")){
					PromptDialog.title="Error";
					PromptDialog.content="还有信息未填写！";
					Intent di=new Intent(AnniEditActivity.this, PromptDialog.class);
					startActivity(di);
				}
				else{
					anni=new TinyAnni();
					anni.userName=MainActivity.currentUser;
					anni.anniID=AnniEditActivity.anniID;
					anni.anniYear=year;
					anni.anniMonth=month;
					anni.anniDay=day;
					anni.anniColor=color;
					anni.updateSystemAnni();
					AnniEditActivity.DATE_YEAR="";
					AnniEditActivity.DATE_MONTH="";
					AnniEditActivity.DATE_DAY="";
					AnniEditActivity.BACKCOLOR=1;
					Intent i0=new Intent(AnniEditActivity.this, AnniDetailActivity.class);
					startActivity(i0);
					finish();
				}
			}
			else{
				String event=anniContent.getText().toString();
				String frequent=textRemind.getText().toString();
				if(year.equals("")||month.equals("")||day.equals("")||event==null||event.equals("")){
					PromptDialog.title="Error";
					PromptDialog.content="还有信息未填写！";
					Intent di=new Intent(AnniEditActivity.this, PromptDialog.class);
					startActivity(di);
				}
				else{
					anni=new TinyAnni();
					anni.userName=MainActivity.currentUser;
					anni.anniID=AnniEditActivity.anniID;
					anni.anniYear=year;
					anni.anniMonth=month;
					anni.anniDay=day;
					anni.anniColor=color;
					anni.anniFrequent=frequent;
					anni.anniContent=event;
					anni.updateAnni();
					AnniEditActivity.DATE_YEAR="";
					AnniEditActivity.DATE_MONTH="";
					AnniEditActivity.DATE_DAY="";
					AnniEditActivity.BACKCOLOR=1;
					Intent i0=new Intent(AnniEditActivity.this, AnniDetailActivity.class);
					startActivity(i0);
					finish();
				}
			}
			
			break;
			
		case R.id.anni_edit_select_1:
			setSelectColor(1);
			break;
			
		case R.id.anni_edit_select_2:
			setSelectColor(2);
			break;
			
		case R.id.anni_edit_select_3:
			setSelectColor(3);
			break;
			
		case R.id.anni_edit_select_4:
			setSelectColor(4);
			break;
			
		case R.id.anni_edit_select_5:
			setSelectColor(5);
			break;
			
		case R.id.anni_edit_select_6:
			setSelectColor(6);
			break;
			
		case R.id.anni_edit_select_7:
			setSelectColor(7);
			break;
			
		case R.id.anni_edit_rl_time:
			Intent intentSelectDate=new Intent(AnniEditActivity.this, SelectDateDialog.class);
			startActivityForResult(intentSelectDate,2);
			break;
			
		case R.id.anni_edit_rl_remind:
			Intent intentSelectFrequent=new Intent(AnniEditActivity.this, SelectFrequentDialog.class);
			startActivityForResult(intentSelectFrequent,3);
			break;
			
		case R.id.anni_edit_rl_delete:
			if(isSystemAnni){
				anni.deleteSystemAnni(anniID);
			}
			else{
				anni.deleteAnni(anniID);
			}
			Intent intentList=new Intent(AnniEditActivity.this, AnniActivity.class);
			startActivity(intentList);
			finish();
			break;
			
		default:
			break;
		}
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==2&&resultCode==0){
			
			if(AddAnniActivity.DATE_YEAR.equals("")||AddAnniActivity.DATE_MONTH.equals("")||AddAnniActivity.DATE_DAY.equals("")){
				textEventTime.setText("点击这里选择日期");
			}
			else{
				textEventTime.setText(AnniEditActivity.DATE_YEAR+"年"+AnniEditActivity.DATE_MONTH+"月"+AnniEditActivity.DATE_DAY+"日");
			}
		}
		if(requestCode==3&&resultCode==0){
			
			textRemind.setText(AddAnniActivity.FREQUENT);
		}
		
	}

	
	
}
