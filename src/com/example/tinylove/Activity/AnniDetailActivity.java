package com.example.tinylove.Activity;

import com.example.tinylove.R;
import com.example.tinylove.Database.TinyAnni;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AnniDetailActivity extends Activity implements OnClickListener {
	
	final int[] backgroundBig={R.drawable.anni_bg_large_1,R.drawable.anni_bg_large_2,R.drawable.anni_bg_large_3,R.drawable.anni_bg_large_4,R.drawable.anni_bg_large_5,
			R.drawable.anni_bg_large_6,R.drawable.anni_bg_large_7,R.drawable.anni_bg_large_8,R.drawable.anni_bg_large_9,R.drawable.anni_bg_large_10,
			R.drawable.anni_bg_large_11,R.drawable.anni_bg_large_12,R.drawable.anni_bg_large_13,R.drawable.anni_bg_large_14,R.drawable.anni_bg_large_15,
			R.drawable.anni_bg_large_16,R.drawable.anni_bg_large_17,R.drawable.anni_bg_large_18,R.drawable.anni_bg_large_19,R.drawable.anni_bg_large_20};
	
	public static String anniID="";
	
	private RelativeLayout rlBack;
	
	private ImageView imageBack;
	private ImageView imageEdit;
	
	private TextView textContent;
	private TextView textDays;
	private TextView textTarget;
	
	private ImageView imageChangeBackground;
	
	public static int IMAGE=0;
	
	private TinyAnni anni;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_anni);
		
		initView();
		
		setOnListeners();
	}

	private void initView() {
		// TODO Auto-generated method stub
		rlBack=(RelativeLayout)findViewById(R.id.check_anni_background);
		
		imageBack=(ImageView)findViewById(R.id.check_anni_back);
		imageEdit=(ImageView)findViewById(R.id.check_anni_edit);
		
		textContent=(TextView)findViewById(R.id.check_anni_text1);
		textDays=(TextView)findViewById(R.id.check_anni_text2);
		textTarget=(TextView)findViewById(R.id.check_anni_text3);
		
		anni=new TinyAnni();
		rlBack.setBackgroundResource(backgroundBig[anni.getBackground(anniID)]);
		String fre=anni.getFrequent(MainActivity.currentUser, anniID);
		if(fre.equals("无")){
			textContent.setText(anni.getAnniContent(anniID)+"已经");
		}
		else{
			textContent.setText(anni.getAnniContent(anniID)+"还有");
		}
		String date=anni.getAnniDate(anniID);
		textDays.setText(anni.getDays(date, fre)+"");
		textTarget.setText("目标日："+anni.getTargetDate(date, fre));
		imageChangeBackground=(ImageView)findViewById(R.id.check_anni_change_background);
		
	}
	

	private void setOnListeners() {
		// TODO Auto-generated method stub
		imageBack.setOnClickListener(this);
		imageEdit.setOnClickListener(this);
		imageChangeBackground.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.check_anni_back:
			Intent intentList=new Intent(AnniDetailActivity.this, AnniActivity.class);
			startActivity(intentList);
			finish();
			break;
			
		case R.id.check_anni_edit:
			AnniEditActivity.anniID=anniID;
			Intent intentEdit=new Intent(AnniDetailActivity.this, AnniEditActivity.class);
			startActivity(intentEdit);
			finish();
			break;
			
		case R.id.check_anni_change_background:
			SelectBackgroundActivity.anniID=AnniDetailActivity.anniID;
			SelectBackgroundActivity.IMAGE=AnniDetailActivity.IMAGE;
			SelectBackgroundActivity.isDetail=true;
			Intent intentSelectBack=new Intent(AnniDetailActivity.this, SelectBackgroundActivity.class);
			startActivity(intentSelectBack);
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
		if(requestCode==0&&resultCode==0){
			anni=new TinyAnni();
			rlBack.setBackgroundResource(backgroundBig[anni.getBackground(anniID)]);
		}
		
	}

}
