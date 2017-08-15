package com.example.tinylove.Activity;

import com.example.tinylove.R;
import com.example.tinylove.Database.TinyWish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class WishAddDialog extends Activity {
	
	private EditText editCon;
	private TextView textOk;
	private TextView textCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_wish_add);
		
		editCon=(EditText)findViewById(R.id.wish_add_content);
		textOk=(TextView)findViewById(R.id.wish_add_ok);
		textCancel=(TextView)findViewById(R.id.wish_add_cancel);
		
		textOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String s=editCon.getText().toString();
				if(s.equals("")||s==null);
				else{
					TinyWish wish=new TinyWish();
					wish.addWish(s);
					wish.display();
					WishActivity.activity.finish();
					Intent intent=new Intent(WishAddDialog.this, WishActivity.class);
					startActivity(intent);
					finish();
				}
				
			}
		});
		
		textCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	}
	
	

}
