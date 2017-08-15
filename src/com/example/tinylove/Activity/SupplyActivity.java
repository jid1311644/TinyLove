package com.example.tinylove.Activity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.example.tinylove.R;
import com.example.tinylove.Database.TinyAnni;
import com.example.tinylove.Database.TinyCheck;
import com.example.tinylove.Database.TinyUser;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class SupplyActivity extends Activity implements OnClickListener, OnCheckedChangeListener {
	
	private ImageView back;
	private TextView next;
	
	private ImageView head;
	private RadioGroup rgSex;
	private RadioButton rbMale;
	private RadioButton rbFemale;
	
	private EditText editName;
	
	public static String mainName="";
	public static String mainPsw="";
	public static int mainSex=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_supply);
		
		init();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		back=(ImageView)findViewById(R.id.supply_back);
		next=(TextView)findViewById(R.id.supply_next);
		head=(ImageView)findViewById(R.id.supply_change_head);
		rgSex=(RadioGroup)findViewById(R.id.supply_rg_sex);
		rbMale=(RadioButton)findViewById(R.id.supply_male);
		rbFemale=(RadioButton)findViewById(R.id.supply_female);
		editName=(EditText)findViewById(R.id.supply_name);
		back.setOnClickListener(this);
		next.setOnClickListener(this);
		//head.setOnClickListener(this);
		rgSex.setOnCheckedChangeListener(this);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		if(checkedId==R.id.supply_male){
			rbMale.setBackgroundResource(R.drawable.reg_male_hover);
			rbMale.setChecked(true);
			rbFemale.setBackgroundResource(R.drawable.reg_female);
			rbFemale.setChecked(false);
			head.setImageResource(R.drawable.user_head_male);
		}
		else if(checkedId==R.id.supply_female){
			rbMale.setBackgroundResource(R.drawable.reg_male);
			rbMale.setChecked(false);
			rbFemale.setBackgroundResource(R.drawable.reg_female_hover);
			rbFemale.setChecked(true);
			head.setImageResource(R.drawable.user_head_female);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.supply_back:
			finish();
			break;

		case R.id.supply_next:
			String n=editName.getText().toString();
			int sex=0;
			if(rbMale.isChecked()){
				sex=1;
			}
			if(rbFemale.isChecked()){
				sex=0;
			}
			if(n!=null&&!n.equals("")){
				TinyUser user=new TinyUser();
				user.userName=SupplyActivity.mainName;
				user.userPsw=SupplyActivity.mainPsw;
				user.userSex=SupplyActivity.mainSex;
				user.youName=n;
				user.youSex=sex;
				user.mainBackgroundPath="";
				
				if(user.signUp()){
					TinyAnni anni=new TinyAnni();
					anni.init(user.userName);
					MainActivity.currentUser=user.userName;
					HomepageFragment.DAY=0;
					
					TinyCheck check=new TinyCheck();
					check.init(user.userName);
					
					GuideActivity.activity.finish();
					Intent intentMain=new Intent(SupplyActivity.this, MainActivity.class);
					startActivity(intentMain);
					finish();
					SignUpActivity.activity.finish();
				}
				else{
					PromptDialog.title="Error";
					PromptDialog.content="不道道哪错了！";
					Intent iP=new Intent(SupplyActivity.this, PromptDialog.class);
					startActivity(iP);
				}
			}
			
			
			break;
			
		case R.id.supply_change_head:
			Intent intent=new Intent(SupplyActivity.this, GetPictureDialog.class);
			startActivity(intent);
			break;
			
		default:
			break;
		}
	}
	
	
}
