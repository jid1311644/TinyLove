package com.example.tinylove.Activity;

import java.util.Date;

import com.example.tinylove.R;
import com.example.tinylove.Database.MyDataBaseHelper;
import com.example.tinylove.Database.TinyAnni;
import com.example.tinylove.Database.TinyUser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class SplashActivity extends Activity {
	
	public static MyDataBaseHelper dbHelper;
	
	// —”≥Ÿ3√Î
	private static final long SPLASH_DELAY_MILLIS = 1000;
	
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1000:
				Intent intent1 = new Intent(SplashActivity.this, MainActivity.class);
				SplashActivity.this.startActivity(intent1);
				SplashActivity.this.finish();
				break;
			case 1001:
				Intent intent2 = new Intent(SplashActivity.this, GuideActivity.class);
				SplashActivity.this.startActivity(intent2);
				SplashActivity.this.finish();
				break;
			}
			super.handleMessage(msg);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		 
		SharedPreferences sharedPreferences = this.getSharedPreferences("share", MODE_PRIVATE); 
		boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true); 
		Editor editor = sharedPreferences.edit(); 
		if (isFirstRun) 
		{
			editor.putBoolean("isFirstRun", false); 
			editor.commit(); 
			System.out.println("isFirstRun");
		} else
		{ 
			System.out.println("notFirstRun");
		}
		dbHelper=new MyDataBaseHelper(this,"tintloveDB1.db3", 1);

		//Date test
		Date date=new Date();
		System.out.println("nochange"+date.getYear()+" "+date.getMonth()+" "+date.getDate()+" "+date.getDay());
		System.out.println("change"+(date.getYear()+1900)+" "+(date.getMonth()+1)+" "+date.getDate()+" "+date.getDay());
		date.setYear(115);
		date.setMonth(10);
		date.setDate(1);
		System.out.println("set_nochange"+date.getYear()+" "+date.getMonth()+" "+date.getDate()+" "+date.getDay());
		System.out.println("set_change"+(date.getYear()+1900)+" "+(date.getMonth()+1)+" "+date.getDate()+" "+date.getDay());
//		TinyUser user=new TinyUser();
//		user.display();
//		TinyAnni anni=new TinyAnni();
//		anni.display();
		
		
		mHandler.sendEmptyMessageDelayed(1001, SPLASH_DELAY_MILLIS);
		
	}
	
}
