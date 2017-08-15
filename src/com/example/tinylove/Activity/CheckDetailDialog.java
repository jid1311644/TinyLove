package com.example.tinylove.Activity;

import com.example.tinylove.R;
import com.example.tinylove.Database.TinyCheck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CheckDetailDialog extends Activity {
	
	final static int[] icon={R.drawable.checkin_morning,R.drawable.checkin_night,R.drawable.checkin_phone,R.drawable.checkin_travel,
			R.drawable.checkin_eat,R.drawable.checkin_movie,R.drawable.checkin_sport,R.drawable.checkin_shop};
	final static String[] content={"每天说早安","每天说晚安","煲一次电话粥","一起去旅行","一起去吃好吃的","一起去看电影","一起去健身","一起去购物"};
	
	private ImageView imageHead;
	private TextView textHead;
	
	private ImageView imageCheckState;
	private TextView textCheckState;
	
	private TextView textDays;
	
	public static String checkID="";
	public static int checkId=0;
	public static boolean isChecked=false;
	public static int checkDays=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_check_detail);
		CheckActivity.detailIsChangeCheck=false;
		
		initView();
		setOnListeners();
		
	}

	private void initView() {
		// TODO Auto-generated method stub
		imageHead=(ImageView)findViewById(R.id.check_detail_image);
		textHead=(TextView)findViewById(R.id.check_detail_text);
		imageHead.setImageResource(icon[checkId]);
		textHead.setText(content[checkId]);
		
		imageCheckState=(ImageView)findViewById(R.id.check_detail_image_check);
		textCheckState=(TextView)findViewById(R.id.check_detail_text_state);
		
		textDays=(TextView)findViewById(R.id.checkin_detail_text_days);
		
		if(isChecked){
			imageCheckState.setImageResource(R.drawable.checkin_detail_state_done_icon);
			textCheckState.setText("今日已打卡");
		}
		else{
			imageCheckState.setImageResource(R.drawable.checkin_detail_state_not_icon);
			textCheckState.setText("点击打卡");
		}
		
		textDays.setText(checkDays+"");
		
		
	}

	private void setOnListeners() {
		// TODO Auto-generated method stub
		imageCheckState.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!isChecked){
					TinyCheck check=new TinyCheck();
					if(check.check(checkID)){
						imageCheckState.setImageResource(R.drawable.checkin_detail_state_done_icon);
						textCheckState.setText("今日已打卡");
						textDays.setText((checkDays+1)+"");
						CheckActivity.detailIsChangeCheck=true;
					}
					else{
						Toast.makeText(CheckDetailDialog.this, "有问题！", Toast.LENGTH_SHORT).show();
					}
				}
				
			}
		});
	}
}
