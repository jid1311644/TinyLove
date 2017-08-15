package com.example.tinylove.Activity;

import com.example.tinylove.R;
import com.example.tinylove.Database.TinyAnni;
import com.example.tinylove.Database.TinyUser;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	
	private EditText editName;
	private EditText editPsw;
	
	private Button btnLogin;
	
	public static String name="";
	private String psw="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		editName=(EditText)findViewById(R.id.login_name);
		editPsw=(EditText)findViewById(R.id.login_psw);
		btnLogin=(Button)findViewById(R.id.login_btn);
		
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				name=editName.getText().toString();
				psw=editPsw.getText().toString();
				
				TinyUser user=new TinyUser();
				if(user.login(name, psw)){
					MainActivity.currentUser=user.userName;
					TinyAnni anni=new TinyAnni();
					HomepageFragment.DAY=anni.getDays(MainActivity.currentUser);
					
					GuideActivity.activity.finish();
					Intent intent=new Intent(LoginActivity.this, MainActivity.class);
					startActivity(intent);
					finish();
				}
				else{
					PromptDialog.title="Error";
					PromptDialog.content="用户名或者密码不正确！";
					Intent intentError=new Intent(LoginActivity.this,PromptDialog.class);
					startActivity(intentError);
				}
			}
		});
		
	}

}
