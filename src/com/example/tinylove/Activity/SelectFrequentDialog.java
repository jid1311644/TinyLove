package com.example.tinylove.Activity;

import com.example.tinylove.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class SelectFrequentDialog extends Activity implements OnClickListener {

	private TextView btnNone;
	private TextView btnWeek;
	private TextView btnMonth;
	private TextView btnYear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_select_frequent);
		
		initView();
		setOnListerners();
		
	}

	private void initView() {
		// TODO Auto-generated method stub
		btnNone=(TextView)findViewById(R.id.add_anni_remind_select_frequent_none);
		btnWeek=(TextView)findViewById(R.id.add_anni_remind_select_frequent_week);
		btnMonth=(TextView)findViewById(R.id.add_anni_remind_select_frequent_month);
		btnYear=(TextView)findViewById(R.id.add_anni_remind_select_frequent_year);
	}
	
	private void setOnListerners() {
		// TODO Auto-generated method stub
		btnNone.setOnClickListener(this);
		btnWeek.setOnClickListener(this);
		btnMonth.setOnClickListener(this);
		btnYear.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){
		
		case R.id.add_anni_remind_select_frequent_none:
			AddAnniActivity.FREQUENT="无";
			AnniEditActivity.FREQUENT="无";
			SelectFrequentDialog.this.finish();
			break;
			
		case R.id.add_anni_remind_select_frequent_week:
			AddAnniActivity.FREQUENT="每周";
			AnniEditActivity.FREQUENT="每周";
			SelectFrequentDialog.this.finish();
			break;
			
		case R.id.add_anni_remind_select_frequent_month:
			AddAnniActivity.FREQUENT="每月";
			AnniEditActivity.FREQUENT="每月";
			SelectFrequentDialog.this.finish();
			break;
	
		case R.id.add_anni_remind_select_frequent_year:
			AddAnniActivity.FREQUENT="每年";
			AnniEditActivity.FREQUENT="每年";
			SelectFrequentDialog.this.finish();
			break;
	
	
		}
		
		
	}
	
}
