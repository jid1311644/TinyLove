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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class WishDetailDialog extends Activity {
	
	private TextView textWishDetailContent;
	private TextView textWishDetailTime;
	private CheckBox imageWishIsFinish;
	private TextView textWishConfirm;
	private TextView textWishDelete;
	
	public static String wishID="";
	public static String wishCon="";
	public static String wishTime="";
	public static int wishState=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_wish_detail);
		
		initView();
		setOnListeners();
		
	}

	private void initView() {
		// TODO Auto-generated method stub
		textWishDetailContent=(TextView)findViewById(R.id.wish_detail_content);
		textWishDetailTime=(TextView)findViewById(R.id.wish_detail_time);
		imageWishIsFinish=(CheckBox)findViewById(R.id.wish_detail_finish);
		textWishConfirm=(TextView)findViewById(R.id.wish_ok);
		textWishDelete=(TextView)findViewById(R.id.wish_delete);
		
		textWishDetailContent.setText(wishCon);
		textWishDetailTime.setText(wishTime);
		if(wishState==1){
			imageWishIsFinish.setChecked(true);
			imageWishIsFinish.setBackgroundResource(R.drawable.wish_finish);
		}
		else if(wishState==0){
			imageWishIsFinish.setChecked(false);
			imageWishIsFinish.setBackgroundResource(R.drawable.wish_no_finish);
		}
		
	}

	private void setOnListeners() {
		// TODO Auto-generated method stub
		imageWishIsFinish.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					imageWishIsFinish.setBackgroundResource(R.drawable.wish_finish);
					wishState=1;
				}
				else{
					imageWishIsFinish.setBackgroundResource(R.drawable.wish_no_finish);
					wishState=0;
				}
			}
		});
		
		textWishConfirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TinyWish wish=new TinyWish();
				wish.setState(WishDetailDialog.wishID, wishState);
				WishActivity.activity.finish();
				Intent intent=new Intent(WishDetailDialog.this, WishActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		textWishDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//delete
				TinyWish wish=new TinyWish();
				wish.delete(WishDetailDialog.wishID);
				WishActivity.activity.finish();
				Intent intent=new Intent(WishDetailDialog.this, WishActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

}
