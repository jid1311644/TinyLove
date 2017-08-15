package com.example.tinylove.Activity;

import com.example.tinylove.R;
import com.example.tinylove.Database.TinyUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class UserEditActivity extends Activity {
	
	private ImageView back;
	private TextView complete;
	
	private EditText editMyPsw;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_edit);
		
		back=(ImageView)findViewById(R.id.user_edit_back);
		complete=(TextView)findViewById(R.id.user_edit_next);
		
		editMyPsw=(EditText)findViewById(R.id.user_edit_psw);
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		complete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String p=editMyPsw.getText().toString();
				
				if(p==null||p.equals("")){
					PromptDialog.title="Error";
					PromptDialog.content="请检查信息是否填写完整！";
					Intent ip=new Intent(UserEditActivity.this, PromptDialog.class);
					startActivity(ip);
				}
				else{
					TinyUser user=new TinyUser();
					user.changePsw(MainActivity.currentUser, p);
					ConfirmDialog.content="密码修改成功！是否重新登录？";
					Intent ic=new Intent(UserEditActivity.this, ConfirmDialog.class);
					startActivityForResult(ic,17);
				}
				
			}
		});
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==17&&resultCode==0){
			if(ConfirmDialog.OK){
				Intent ig=new Intent(UserEditActivity.this, GuideActivity.class);
				startActivity(ig);
				finish();
			}
		}
		
	}
	
	

}
