package com.example.tinylove.Activity;

import java.util.Calendar;

import com.example.tinylove.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;
import android.widget.Toast;

public class SelectDateDialog extends Activity {
	
	private DatePicker datePicker;
	private TextView ok;
	private TextView cancel;

	private int year;
	private int month;
	private int day;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_select_date);
		
		initView();
		setOnListeners();
		
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	private void initView() {
		// TODO Auto-generated method stub
		datePicker=(DatePicker)findViewById(R.id.add_anni_date_picker);
		
		ok=(TextView)findViewById(R.id.add_anni_select_date_ok);
		cancel=(TextView)findViewById(R.id.add_anni_select_date_cancel);
	}

	private void setOnListeners() {
		// TODO Auto-generated method stub
		Calendar c=Calendar.getInstance();
		year=c.get(Calendar.YEAR);
		month=c.get(Calendar.MONTH);
		day=c.get(Calendar.DAY_OF_MONTH);
		datePicker.init(year, month, day, new OnDateChangedListener() {
			
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				// TODO Auto-generated method stub
				SelectDateDialog.this.year=year;
				SelectDateDialog.this.month=monthOfYear;
				SelectDateDialog.this.day=dayOfMonth;
			}
		});
		
		ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AddAnniActivity.DATE_YEAR=SelectDateDialog.this.getYear()+"";
				AddAnniActivity.DATE_MONTH=(SelectDateDialog.this.getMonth()+1)+"";
				AddAnniActivity.DATE_DAY=SelectDateDialog.this.getDay()+"";
				AnniEditActivity.DATE_YEAR=SelectDateDialog.this.getYear()+"";
				AnniEditActivity.DATE_MONTH=(SelectDateDialog.this.getMonth()+1)+"";
				AnniEditActivity.DATE_DAY=SelectDateDialog.this.getDay()+"";
				
				SelectDateDialog.this.finish();
			}
		});
		
		
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AddAnniActivity.DATE_YEAR="";
				AddAnniActivity.DATE_MONTH="";
				AddAnniActivity.DATE_DAY="";
				AnniEditActivity.DATE_YEAR="";
				AnniEditActivity.DATE_MONTH="";
				AnniEditActivity.DATE_DAY="";
				
				SelectDateDialog.this.finish();
			}
		});
	}
	
}
