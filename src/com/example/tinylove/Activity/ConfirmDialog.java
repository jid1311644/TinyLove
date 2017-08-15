package com.example.tinylove.Activity;

import com.example.tinylove.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmDialog extends Activity {
	
	public static String content="";
	
	private TextView textContent;
	private TextView btnOk;
	private TextView btnCancel;
	
	public static boolean OK;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_confirm);
		
		OK=false;
		
		textContent=(TextView)findViewById(R.id.confirm_content);
		btnOk=(TextView)findViewById(R.id.confirm_ok);
		btnCancel=(TextView)findViewById(R.id.confirm_cancel);
		
		textContent.setText(content);
		
		btnOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				OK=true;
				finish();
			}
		});
		
		btnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				OK=false;
				finish();
			}
		});
		
	}

}
